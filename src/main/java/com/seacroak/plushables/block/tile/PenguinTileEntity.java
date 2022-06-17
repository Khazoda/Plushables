package com.seacroak.plushables.block.tile;

import com.seacroak.plushables.registry.TileRegistry;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class PenguinTileEntity extends BlockEntity implements IAnimatable {
	private final AnimationFactory factory = new AnimationFactory(this);

	public PenguinTileEntity(BlockPos pos, BlockState state) {
		super(TileRegistry.PENGUIN_TILE, pos, state);
	}

	private <E extends BlockEntity & IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		AnimationController<?> controller = event.getController();
		controller.setAnimation(new AnimationBuilder().addAnimation("animation.penguin_baby.idle", true));
		return PlayState.CONTINUE;
	}

	@Override
	public void registerControllers(AnimationData data) {
		data.addAnimationController(new AnimationController<PenguinTileEntity>(this, "controller", 0, this::predicate));
	}

	@Override
	public AnimationFactory getFactory() {
		return this.factory;
	}
}
