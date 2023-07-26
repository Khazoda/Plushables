package com.seacroak.plushables.block.tile;

import com.seacroak.plushables.block.screen.BuilderScreenHandler;
import com.seacroak.plushables.recipe.BuilderRecipe;
import com.seacroak.plushables.registry.MainRegistry;
import com.seacroak.plushables.registry.SoundRegistry;
import com.seacroak.plushables.registry.TileRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
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

import java.util.Arrays;
import java.util.Optional;

public class BuilderTileEntity extends BlockEntity implements MenuProvider, GeoBlockEntity {
  private boolean animationIsCrafting = false;
  private final NonNullList<ItemStack> outputItems;
  private boolean shouldReRender;

  private final ItemStackHandler itemHandler = new ItemStackHandler(4) {
    @Override
    protected void onContentsChanged(int slot) {
      setChanged();
//      rerenderBuilder();
    }
  };
  private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

  protected final ContainerData data;
  private int progress = 0;
  private int maxProgress = 78;

  public BuilderTileEntity(BlockPos pos, BlockState state) {
    super(TileRegistry.BUILDER_TILE.get(), pos, state);
    this.outputItems = NonNullList.withSize(4, ItemStack.EMPTY);
    this.shouldReRender = true;

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


  public static void tick(@NotNull Level level, BlockPos pos, BlockState state, BuilderTileEntity entity) {
    if (hasRecipe(entity)) {
      /* Clientside in-block output plushable rendering*/
      if (entity.level.isClientSide()) {
        craftItemRender(entity);
        return;
      }
      /* Serverside recipe handling */
      entity.animationIsCrafting = true;
      entity.progress++;
      setChanged(level, pos, state);
      if (entity.progress >= entity.maxProgress) {
        craftItem(entity);
      }
    } else {
      craftItemRenderReset(entity);
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
        .getRecipeFor(BuilderRecipe.Type.INSTANCE, inventory, level);

    if (hasRecipe(entity)) {
      /* Handle item manipulation to complete the recipe */
      entity.itemHandler.extractItem(0, 1, false);
      entity.itemHandler.extractItem(1, 1, false);
      entity.itemHandler.extractItem(2, 1, false);
      entity.itemHandler.setStackInSlot(3, new ItemStack(recipe.get().getResultItem(RegistryAccess.EMPTY).getItem()
          , entity.itemHandler.getStackInSlot(3).getCount() + 1));
      entity.rerenderBuilder();
      entity.outputItems.set(3,new ItemStack(recipe.get().getResultItem(RegistryAccess.EMPTY).getItem()));

      entity.resetProgress();
    }
    setChanged(entity.level, entity.getBlockPos(), entity.getBlockState());
  }

  /* Mini Plushable Rendering Methods*/
  private static void craftItemRender(BuilderTileEntity entity) {
    /* Terminate method if flag not set */
//    if (!entity.shouldReRender) return;
    /* Populate recipe slot data */
    ItemStack input_item = entity.itemHandler.getStackInSlot(0);
    ItemStack input_wool = entity.itemHandler.getStackInSlot(1);
    ItemStack input_heart = entity.itemHandler.getStackInSlot(2);
    ItemStack output_plush = entity.itemHandler.getStackInSlot(3);
    /* Idle crafting state */
    entity.outputItems.set(0, input_item);
    entity.outputItems.set(1, input_wool);
    entity.outputItems.set(2, input_heart);
    entity.outputItems.set(3, output_plush);

    entity.shouldReRender = false;
  }

  private static void craftItemRenderReset(BuilderTileEntity entity) {
    entity.outputItems.set(0, ItemStack.EMPTY);
    entity.outputItems.set(1, ItemStack.EMPTY);
    entity.outputItems.set(2, ItemStack.EMPTY);
    entity.outputItems.set(3, ItemStack.EMPTY);
  }

  public ClientboundBlockEntityDataPacket getUpdatePacket() {
    return ClientboundBlockEntityDataPacket.create(this);
  }

  public NonNullList<ItemStack> getOutput() {
    return this.outputItems;
  }

  private static boolean hasRecipe(BuilderTileEntity entity) {
    Level level = entity.level;
    SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
    for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
      inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
    }

    Optional<BuilderRecipe> recipe = level.getRecipeManager()
        .getRecipeFor(BuilderRecipe.Type.INSTANCE, inventory, level);

    return recipe.isPresent() && canInsertAmountIntoOutputSlot(inventory)
        && canInsertItemIntoOutputSlot(inventory, new ItemStack(recipe.get()
        .getResultItem(RegistryAccess.EMPTY).getItem(), 1));
  }

  private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack itemStack) {
    return inventory.getItem(3).getItem() == itemStack.getItem() || inventory.getItem(3).isEmpty();
  }

  private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
    return inventory.getItem(3).getMaxStackSize() > inventory.getItem(3).getCount();
  }

  public void rerenderBuilder() {
    this.setChanged();
    this.getLevel().sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
    this.shouldReRender = true;
  }


  /* GeckoLib */
  private final AnimatableInstanceCache instanceCache = GeckoLibUtil.createInstanceCache(this);

  private <E extends BlockEntity & GeoAnimatable> PlayState builderIdlePredicate(AnimationState<E> event) {
    AnimationController<?> controller = event.getController();
    controller.setAnimation(RawAnimation.begin().thenPlay("animation.builder.idle"));
    return PlayState.CONTINUE;
  }

//  private <E extends BlockEntity & GeoAnimatable> PlayState builderCraftingPredicate(AnimationState<E> event) {
//    AnimationController<?> controller = event.getController();
//    if (this.progress > 0 && this.progress < this.maxProgress) {
//      controller.setAnimation(RawAnimation.begin().thenPlay("animation.builder.building"));
//    }
//    return PlayState.CONTINUE;
//  }

  @Override
  public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
    controllers.add(
        new AnimationController<BuilderTileEntity>(this, "controller", 0, this::builderIdlePredicate));
//    controllers.add(
//        new AnimationController<BuilderTileEntity>(this, "building_controller", 0,
//            this::builderCraftingPredicate));
  }

  @Override
  public AnimatableInstanceCache getAnimatableInstanceCache() {
    return this.instanceCache;
  }

}
