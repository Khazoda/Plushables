package com.seacroak.plushables.block.tile;

import com.seacroak.plushables.registry.TileRegistry;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class CluckyTileEntity extends BlockEntity
		implements IAnimatable {

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
	private final AnimationFactory factory = new AnimationFactory(this);

	private <E extends BlockEntity & IAnimatable> PlayState cluckyIdlePredicate(AnimationEvent<E> event) {
		AnimationController<?> controller = event.getController();
		controller.setAnimation(new AnimationBuilder().addAnimation("animation.chicken.wiggle", true));
		return PlayState.CONTINUE;
	}

	// private <E extends BlockEntity & IAnimatable> PlayState
	// cubePredicateSpin(AnimationEvent<E> event) {
	// AnimationController<?> controller = event.getController();
	// controller.transitionLengthTicks = 0;

	// controller.setAnimation(new
	// AnimationBuilder().addAnimation("animation.chicken.look"));
	// // controller.markNeedsReload();
	// // .addAnimation("Botarium.anim.idle", true));

	// return PlayState.CONTINUE;
	// }

	private <E extends BlockEntity & IAnimatable> PlayState cluckyLookPredicate(AnimationEvent<E> event) {
		lookController = event.getController();
		// controller.transitionLengthTicks = 0;

		if (shouldLook) {
			lookController.setAnimation(new AnimationBuilder().addAnimation("animation.chicken.look"));
			if (lookController.getAnimationState() == AnimationState.Stopped) {
				shouldLook = false;
			}
			// .addAnimation("fertilizer.animation.idle", true));
		} else {
			// lookController.setAnimation(new AnimationBuilder());
			lookController.markNeedsReload();
			// .addAnimation("Botarium.anim.idle", true));
		}
		return PlayState.CONTINUE;
	}

	@Override
	public void registerControllers(AnimationData data) {
		data.addAnimationController(
				new AnimationController<CluckyTileEntity>(this, "controller", 0, this::cluckyIdlePredicate));

		// data.addAnimationController(
		// new AnimationController<CluckyTileEntity>(this, "cube_spin_controller", 0,
		// this::cubePredicateSpin));
		data.addAnimationController(
				new AnimationController<CluckyTileEntity>(this, "clucky_look_controller", 0,
						this::cluckyLookPredicate));
	}

	@Override
	public AnimationFactory getFactory() {
		return this.factory;
	}

}
