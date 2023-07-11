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

public class CluckyTileEntity extends BlockEntity implements GeoBlockEntity {

	public boolean shouldLook;
	public AnimationController<?> lookController;

	public CluckyTileEntity(BlockPos pos, BlockState state) {
		super(TileRegistry.CLUCKY_TILE, pos, state);
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
		controller.setAnimation(RawAnimation.begin().thenPlay("animation.chicken.wiggle"));
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
			lookController.setAnimation(RawAnimation.begin().thenPlay("animation.chicken.look"));
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
				new AnimationController<CluckyTileEntity>(this, "controller", 0, this::cluckyIdlePredicate));

		// data.addAnimationController(
		// new AnimationController<CluckyTileEntity>(this, "cube_spin_controller", 0,
		// this::cubePredicateSpin));
		controllers.add(
				new AnimationController<CluckyTileEntity>(this, "clucky_look_controller", 0,
						this::cluckyLookPredicate));
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.instanceCache;
	}

}