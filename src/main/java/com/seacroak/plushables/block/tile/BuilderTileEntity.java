package com.seacroak.plushables.block.tile;

import com.seacroak.plushables.gui.BuilderInventory;
import com.seacroak.plushables.gui.BuilderScreenHandler;
import com.seacroak.plushables.recipe.BuilderRecipe;
import com.seacroak.plushables.registry.MainRegistry;
import com.seacroak.plushables.registry.SoundRegistry;
import com.seacroak.plushables.registry.TileRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.SingleThreadedRandomSource;
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
import java.util.Random;

public class BuilderTileEntity extends BlockEntity
    implements GeoBlockEntity, MenuProvider, BuilderInventory {

  static SingleThreadedRandomSource rand;
  private static boolean shouldHop;

  // Recipe / Inventory Code
  private final NonNullList<ItemStack> inventory = NonNullList.withSize(4, ItemStack.EMPTY);
  protected final ContainerData propertyDelegate;
  private int progress = 0;
  private int maxProgress = 63;

  public BuilderTileEntity(BlockPos pos, BlockState state) {

    super(TileRegistry.BUILDER_TILE.get(), pos, state);
    shouldHop = false;
    rand = new SingleThreadedRandomSource(100);
    this.propertyDelegate = new ContainerData() {
      public int get(int index) {
        switch (index) {
          case 0:
            return BuilderTileEntity.this.progress;
          case 1:
            return BuilderTileEntity.this.maxProgress;
          default:
            return 0;
        }
      }

      public void set(int index, int value) {
        switch (index) {
          case 0:
            BuilderTileEntity.this.progress = value;
            break;
          case 1:
            BuilderTileEntity.this.maxProgress = value;
            break;
        }
      }

      @Override
      public int getCount() {
        return 0;
      }

    };
  }

  @Override
  public Component getDisplayName() {
    return this.getBlockState().getBlock().getName();
  }


  @Nullable
  @Override
  public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
    return new BuilderScreenHandler(pContainerId, pPlayerInventory, this, this.propertyDelegate);
  }


  @Override
  public NonNullList<ItemStack> getItems() {
    return inventory;
  }

  @Override
  public void load(CompoundTag nbt) {
    super.load(nbt);
    ContainerHelper.loadAllItems(nbt, inventory);
  }

  @Override
  protected void saveAdditional(CompoundTag nbt) {
    ContainerHelper.saveAllItems(nbt, inventory);
    return;
  }


  public static void tick(Level level, BlockPos pos, BlockState state, BuilderTileEntity entity) {
    if (hasRecipe(entity)) {
      entity.progress++;
      if (entity.progress > entity.maxProgress) {
        craftItem(entity);
      }
    } else {
      entity.resetProgress();
    }
  }

  private static boolean hasRecipe(BuilderTileEntity entity) {
    Level level = entity.level;
    SimpleContainer inventory = new SimpleContainer(entity.inventory.size());
    for (int i = 0; i < entity.inventory.size(); i++) {
      inventory.setItem(i, entity.getItem(i));

    }

    Optional<BuilderRecipe> match = level.getRecipeManager().getRecipeFor(BuilderRecipe.Type.INSTANCE, inventory, level);

    return match.isPresent()
        && canInsertAmountIntoOutputSlot(inventory)
        && canInsertItemIntoOutputSlot(inventory, match.get().getResultItem(entity.level.registryAccess()));
  }

  private static void craftItem(BuilderTileEntity entity) {
    Level level = entity.level;
    SimpleContainer inventory = new SimpleContainer(entity.inventory.size());
    for (int i = 0; i < entity.inventory.size(); i++) {
      inventory.setItem(i, entity.getItem(i));
    }

    Optional<BuilderRecipe> match = level.getRecipeManager()
        .getRecipeFor(BuilderRecipe.Type.INSTANCE, inventory, level);

    if (match.isPresent()) {
      entity.removeItem(0, 1);
      entity.removeItem(1, 1);
      entity.removeItem(3, 1);
      entity.setItem(2, new ItemStack(match.get().getResultItem(entity.level.registryAccess()).getItem(),
          entity.getItem(2).getCount() + 1));

      if (!level.isClientSide()) {
        // ! TODO implement networked solution for syncing hop animation across clients
        // ! (currently works in SP but not MP as code is only ran on server)
        shouldHop = true;
        // System.out.println(rand.nextBetween(0, 20));
        // One in 20 chance to spawn an Allay
        if (rand.nextIntBetweenInclusive(0, 20) == 13) {
          EntityType.FIREWORK_ROCKET
              .spawn((ServerLevel) level, null, (Player) null, entity.getBlockPos().above(2),
                  MobSpawnType.TRIGGERED, true, true);

          EntityType.ALLAY
              .spawn((ServerLevel) level, null, (Player) null, entity.getBlockPos().above(2),
                  MobSpawnType.TRIGGERED, true, true)
              .setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(MainRegistry.FROGLIN_PLUSHABLE.get()));
        } else {
          level.playSound(null, entity.getBlockPos(), SoundRegistry.BUILDER_DING.get(),
              SoundSource.BLOCKS, 100f, 1f);
          level.playSound(null, entity.getBlockPos(), SoundEvents.MOSS_BREAK,
              SoundSource.BLOCKS, 0.7f, 1f);
        }
      }
      entity.resetProgress();
    }
  }

  private void resetProgress() {
    this.progress = 0;
  }

  private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack output) {
    return inventory.getItem(2).getItem() == output.getItem() || inventory.getItem(2).isEmpty();
  }

  private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
    return inventory.getItem(2).getMaxStackSize() > inventory.getItem(2).getCount();
  }

  // Animation Code
  private final AnimatableInstanceCache instanceCache = GeckoLibUtil.createInstanceCache(this);

  private <E extends BlockEntity & GeoAnimatable> PlayState builderIdlePredicate(AnimationState<E> event) {
    AnimationController<?> controller = event.getController();
    controller.setAnimation(RawAnimation.begin().thenPlay("animation.builder.idle"));
    return PlayState.CONTINUE;
  }

  private <E extends BlockEntity & GeoAnimatable> PlayState cubePredicateSpin(AnimationState<E> event) {
    AnimationController<?> controller = event.getController();
    controller.transitionLength(0);

    controller.setAnimation(RawAnimation.begin().thenPlay("animation.builder.cube_spin"));
    // controller.markNeedsReload();
    // .addAnimation("Botarium.anim.idle", true));

    return PlayState.CONTINUE;
  }

  private <E extends BlockEntity & GeoAnimatable> PlayState cubePredicateHop(AnimationState<E> event) {
    AnimationController<?> controller = event.getController();
    // controller.transitionLengthTicks = 0;

    if (shouldHop) {
      controller.setAnimation(RawAnimation.begin().thenPlay("animation.builder.edgecubes_jump"));
      if (controller.getAnimationState() == AnimationController.State.STOPPED) {
        shouldHop = false;
      }
      // .addAnimation("fertilizer.animation.idle", true));
    } else {
      // controller.setAnimation(new AnimationBuilder());
      controller.forceAnimationReset();
      // .addAnimation("Botarium.anim.idle", true));
    }
    return PlayState.CONTINUE;
  }

  @Override
  public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
    controllers.add(
        new AnimationController<BuilderTileEntity>(this, "controller", 0, this::builderIdlePredicate));

    controllers.add(
        new AnimationController<BuilderTileEntity>(this, "cube_spin_controller", 0,
            this::cubePredicateSpin));
    controllers.add(
        new AnimationController<BuilderTileEntity>(this, "cube__hop_controller", 0,
            this::cubePredicateHop));
  }

  @Override
  public AnimatableInstanceCache getAnimatableInstanceCache() {
    return this.instanceCache;
  }


}
