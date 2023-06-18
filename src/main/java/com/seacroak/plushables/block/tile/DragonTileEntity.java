package com.seacroak.plushables.block.tile;

import com.seacroak.plushables.registry.TileRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
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

public class DragonTileEntity extends BlockEntity implements GeoBlockEntity {

	public boolean shouldLook;
	public AnimationController<?> lookController;

	public DragonTileEntity(BlockPos pos, BlockState state) {
		super(TileRegistry.DRAGON_TILE, pos, state);
		shouldLook = false;
		lookController = null;
	}

	public void setShouldLook(boolean val) {
		this.shouldLook = val;
	}

	public boolean getShouldLook() {
		return this.shouldLook;
	}

	// Animation Code
	private final AnimatableInstanceCache instanceCache = GeckoLibUtil.createInstanceCache(this);

	private <E extends BlockEntity & GeoAnimatable> PlayState cluckyIdlePredicate(AnimationState<E> event) {
		AnimationController<?> controller = event.getController();
		controller.setAnimation(RawAnimation.begin().thenPlay("animation.dragon.flap"));
		return PlayState.CONTINUE;
	}

	// private <E extends BlockEntity & GeoAnimatable> PlayState
	// cubePredicateSpin(AnimationEvent<E> event) {
	// AnimationController<?> controller = event.getController();
	// controller.transitionLengthTicks = 0;

	// controller.setAnimation(new
	// AnimationBuilder().addAnimation("animation.chicken.look"));
	// // controller.markNeedsReload();
	// // .addAnimation("Botarium.anim.idle", true));

	// return PlayState.CONTINUE;
	// }

	private <E extends BlockEntity & GeoAnimatable> PlayState cluckyLookPredicate(AnimationState<E> event) {
		lookController = event.getController();
		// controller.transitionLengthTicks = 0;

		if (shouldLook) {
			lookController.setAnimation(RawAnimation.begin().thenPlay("animation.dragon.scratch"));
			if (lookController.getAnimationState() == AnimationController.State.STOPPED) {
				shouldLook = false;
			}
			// .addAnimation("fertilizer.animation.idle", true));
		} else {
			// lookController.setAnimation(new AnimationBuilder());
			lookController.forceAnimationReset();
			// .addAnimation("Botarium.anim.idle", true));
		}
		return PlayState.CONTINUE;
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(
				new AnimationController<DragonTileEntity>(this, "controller", 0, this::cluckyIdlePredicate));
		controllers.add(
				new AnimationController<DragonTileEntity>(this, "clucky_look_controller", 0,
						this::cluckyLookPredicate));
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.instanceCache;
	}

}
