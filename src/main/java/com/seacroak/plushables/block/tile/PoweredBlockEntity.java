package com.seacroak.plushables.block.tile;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class PoweredBlockEntity extends BlockEntity implements GeoBlockEntity {
  public boolean shouldAnimate;
  public AnimationController<?> interactionController;
  public String animationName;
  private final AnimatableInstanceCache instanceCache = GeckoLibUtil.createInstanceCache(this);

  public PoweredBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
    super(type, pos, state);
    this.shouldAnimate = false;
    this.animationName = "interaction";
  }

  public void shouldAnimate(boolean val) {
    this.shouldAnimate = val;
  }

  public boolean shouldAnimate() {
    return this.shouldAnimate;
  }

  protected <E extends BlockEntity & GeoAnimatable> PlayState interactionPredicate(AnimationState<E> e) {
    interactionController = e.getController();
    if (this.shouldAnimate) {
      interactionController.setAnimation(RawAnimation.begin().thenPlay(animationName));
      if (interactionController.getAnimationState() == AnimationController.State.STOPPED) {
        this.shouldAnimate = false;
      }
    } else {
      interactionController.forceAnimationReset();
    }
    return PlayState.CONTINUE;
  }

  protected <E extends BlockEntity & GeoAnimatable> PlayState idlePredicate(AnimationState<E> e) {
    e.getController().setAnimation(RawAnimation.begin().thenPlay("idle"));
    return PlayState.CONTINUE;
  }

  @Override
  public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
    controllers.add(
        new AnimationController<PoweredBlockEntity>(this, "interaction_controller", 0, this::interactionPredicate));
    controllers.add(
        new AnimationController<PoweredBlockEntity>(this, "idle_controller", 0, this::idlePredicate));
  }

  @Override
  public AnimatableInstanceCache getAnimatableInstanceCache() {
    return this.instanceCache;
  }
}
