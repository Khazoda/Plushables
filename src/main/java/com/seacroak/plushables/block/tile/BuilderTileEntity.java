package com.seacroak.plushables.block.tile;

import com.seacroak.plushables.block.screen.BuilderScreenHandler;
import com.seacroak.plushables.recipe.BuilderRecipe;
import com.seacroak.plushables.registry.MainRegistry;
import com.seacroak.plushables.registry.TileRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Optional;

public class BuilderTileEntity extends BlockEntity implements MenuProvider, GeoBlockEntity {
  private boolean animationIsCrafting = false;

  private final ItemStackHandler itemHandler = new ItemStackHandler(4) {
    @Override
    protected void onContentsChanged(int slot) {
      setChanged();
    }
  };
  private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

  protected final ContainerData data;
  private int progress = 0;
  private int maxProgress = 78;

  public BuilderTileEntity(BlockPos pos, BlockState state) {
    super(TileRegistry.BUILDER_TILE.get(), pos, state);
    this.data = new ContainerData() {
      @Override
      public int get(int index) {
        return switch (index) {
          case 0 -> BuilderTileEntity.this.progress;
          case 1 -> BuilderTileEntity.this.maxProgress;
          default -> 0;
        };
      }

      @Override
      public void set(int index, int value) {
        switch (index) {
          case 0 -> BuilderTileEntity.this.progress = value;
          case 1 -> BuilderTileEntity.this.maxProgress = value;
        }
      }

      @Override
      public int getCount() {
        return 2;
      }
    };
  }

  @Override
  public Component getDisplayName() {
    return Component.literal("Plushable Builder");
  }

  @Nullable
  @Override
  public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
    return new BuilderScreenHandler(id, inventory, this, this.data);
  }

  @Override
  public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
    if (cap == ForgeCapabilities.ITEM_HANDLER) {
      return lazyItemHandler.cast();
    }
    return super.getCapability(cap, side);
  }

  @Override
  public void onLoad() {
    super.onLoad();
    lazyItemHandler = LazyOptional.of(() -> itemHandler);
  }

  @Override
  public void invalidateCaps() {
    super.invalidateCaps();
    lazyItemHandler.invalidate();
  }

  @Override
  protected void saveAdditional(CompoundTag nbt) {
    nbt.put("inventory", itemHandler.serializeNBT());
    nbt.putInt("builder.progress", this.progress);
    super.saveAdditional(nbt);
  }

  @Override
  public void load(CompoundTag nbt) {
    super.load(nbt);
    itemHandler.deserializeNBT(nbt.getCompound("inventory"));
    progress = nbt.getInt("builder.progress");
  }

  /* Drop Inventory Contents */
  public void drops() {
    SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
    for (int i = 0; i < itemHandler.getSlots(); i++) {
      inventory.setItem(i, itemHandler.getStackInSlot(i));
    }
    Containers.dropContents(this.level, this.worldPosition, inventory);
  }

  public static void tick(Level level, BlockPos pos, BlockState state, BuilderTileEntity entity) {
    if (level.isClientSide()) {
      return;
    }
    if (hasRecipe(entity)) {
      entity.animationIsCrafting = true;
      entity.progress++;
      setChanged(level, pos, state);
      if (entity.progress >= entity.maxProgress) {
        craftItem(entity);
      }
    } else {
      entity.animationIsCrafting = false;
      entity.resetProgress();
      setChanged(level, pos, state);
    }
  }

  private void resetProgress() {
    this.progress = 0;
  }

  private static void craftItem(BuilderTileEntity entity) {
    Level level = entity.level;
    SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
    for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
      inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
    }

    Optional<BuilderRecipe> recipe = level.getRecipeManager()
        .getRecipeFor(BuilderRecipe.Type.INSTANCE,inventory,level);

    if (hasRecipe(entity)) {
      entity.itemHandler.extractItem(0, 1, false);
      entity.itemHandler.extractItem(1, 1, false);
      entity.itemHandler.extractItem(2, 1, false);
      entity.itemHandler.setStackInSlot(3, new ItemStack(recipe.get().getResultItem(RegistryAccess.EMPTY).getItem(), entity.itemHandler.getStackInSlot(3).getCount() + 1));
      entity.resetProgress();
    }
  }

  private static boolean hasRecipe(BuilderTileEntity entity) {
    Level level = entity.level;
    SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
    for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
      inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
    }

    Optional<BuilderRecipe> recipe = level.getRecipeManager()
        .getRecipeFor(BuilderRecipe.Type.INSTANCE,inventory,level);

    return recipe.isPresent() && canInsertAmountIntoOutputSlot(inventory)
        && canInsertItemIntoOutputSlot(inventory,new ItemStack(recipe.get()
        .getResultItem(RegistryAccess.EMPTY).getItem(), 1));
  }

  private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack itemStack) {
    return inventory.getItem(3).getItem() == itemStack.getItem() || inventory.getItem(3).isEmpty();
  }

  private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
    return inventory.getItem(3).getMaxStackSize() > inventory.getItem(3).getCount();
  }

  /* GeckoLib */
  private final AnimatableInstanceCache instanceCache = GeckoLibUtil.createInstanceCache(this);

  private <E extends BlockEntity & GeoAnimatable> PlayState builderIdlePredicate(AnimationState<E> event) {
    AnimationController<?> controller = event.getController();
    controller.transitionLength(0);
    controller.setAnimation(RawAnimation.begin().thenPlay("animation.builder.idle"));
    return PlayState.CONTINUE;
  }

  private <E extends BlockEntity & GeoAnimatable> PlayState builderCraftingPredicate(AnimationState<E> event) {
    AnimationController<?> controller = event.getController();
    if (this.progress > 0 && this.progress < this.maxProgress) {
      controller.setAnimation(RawAnimation.begin().thenPlay("animation.builder.building"));
    }
    return PlayState.CONTINUE;
  }

  @Override
  public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
    controllers.add(
        new AnimationController<BuilderTileEntity>(this, "controller", 0, this::builderIdlePredicate));
    controllers.add(
        new AnimationController<BuilderTileEntity>(this, "building_controller", 0,
            this::builderCraftingPredicate));
  }

  @Override
  public AnimatableInstanceCache getAnimatableInstanceCache() {
    return this.instanceCache;
  }

}
