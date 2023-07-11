package com.seacroak.plushables.block.tile;

import com.seacroak.plushables.registry.TileRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class RupertTileEntity extends BlockEntity implements GeoBlockEntity {

	public boolean shouldLook;
	public AnimationController<?> lookController;

	public RupertTileEntity(BlockPos pos, BlockState state) {
		super(TileRegistry.RUPERT_TILE.get(), pos, state);
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

	private <E extends BlockEntity & GeoAnimatable> PlayState rupertLookPredicate(AnimationState<E> event) {
		lookController = event.getController();
		if (shouldLook) {
			lookController.setAnimation(RawAnimation.begin().thenPlay("animation.rupert.lick"));
			if (lookController.getAnimationState() == AnimationController.State.STOPPED) {
				shouldLook = false;
			}
		} else {
			lookController.forceAnimationReset();
		}
		return PlayState.CONTINUE;
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(
				new AnimationController<RupertTileEntity>(this, "rupert_look_controller", 0,
						this::rupertLookPredicate));
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.instanceCache;
	}

}
