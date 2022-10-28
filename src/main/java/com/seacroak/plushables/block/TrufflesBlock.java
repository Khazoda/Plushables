package com.seacroak.plushables.block;

import com.seacroak.plushables.registry.SoundRegistry;
import com.seacroak.plushables.util.VoxelShapeUtils;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class TrufflesBlock extends SimplePlushable {
	public TrufflesBlock() {
		super();
	}

	// Shift Right Click pickup code
	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos,
			PlayerEntity player, Hand hand, BlockHitResult hit) {

		// Serverside code
		if (!world.isClient) {
			if (player.isSneaking()) {
				ItemScatterer.spawn(world, pos, DefaultedList.ofSize(1, new ItemStack(this)));
				world.updateComparators(pos, this);
				world.removeBlock(pos, false);
				return ActionResult.CONSUME;
			}
		}
		if (world.isClient) {
			if (player.isSneaking()) {
				world.playSound(player, pos, SoundRegistry.PLUSHABLE_POP, SoundCategory.BLOCKS, 0.5f, 1f);
				world.playSound(player, pos, SoundEvents.BLOCK_WOOL_HIT, SoundCategory.BLOCKS, 0.5f, 1f);

				// Custom breaking particle code
				for (int i = 0; i < 5; i++) {
					world.addParticle(ParticleTypes.POOF, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
							rand.nextFloat(-0.05f, 0.05f), rand.nextFloat(-0.05f, 0.05f),
							rand.nextFloat(-0.05f, 0.05f));
					world.addParticle(ParticleTypes.GLOW, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
							rand.nextFloat(-0.05f, 0.05f), rand.nextFloat(-0.05f, 0.05f),
							rand.nextFloat(-0.05f, 0.05f));
				}
			}

		}
		return ActionResult.PASS;
	}

	// Bounding / Collision Box code
	static final VoxelShape blockShape = getShape();
	static final VoxelShape[] blockShapes = {
			blockShape, // NORTH Direction VoxelShape
			VoxelShapeUtils.rotateShape(Direction.NORTH, Direction.EAST, blockShape), // EAST Direction VoxelShape
			VoxelShapeUtils.rotateShape(Direction.NORTH, Direction.SOUTH, blockShape), // SOUTH Direction VoxelShape
			VoxelShapeUtils.rotateShape(Direction.NORTH, Direction.WEST, blockShape) }; // WEST Direction VoxelShape

	// NORTH (Default) facing VoxelShape
	static public VoxelShape getShape() {
		VoxelShape shape = VoxelShapes.empty();
		shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0, 0.125, 0.8125, 0.5, 0.75));
		shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0, 0.0625, 0.25, 0.0625, 0.125));
		shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.75, 0, 0.0625, 0.8125, 0.0625, 0.125));
		shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.75, 0, 0.75, 0.8125, 0.0625, 0.875));
		shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0.5, 0.125, 0.3125, 0.625, 0.125));
		shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.375, 0.125, 0.0625, 0.625, 0.25, 0.125));
		shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.6875, 0.5, 0.125, 0.8125, 0.625, 0.125));
		shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.4375, 0.25, 0.875, 0.5625, 0.375, 0.9375));
		shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0, 0.75, 0.75, 0.4375, 0.875));
		shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0, 0.75, 0.25, 0.0625, 0.875));

		return shape;
	}

	// Set the model's bounding box based on which direction it's facing
	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		Direction direction = (Direction) state.get(FACING);

		switch (direction) {
			case NORTH: {
				return blockShapes[0];
			}
			case EAST: {
				return blockShapes[1];
			}
			case SOUTH: {
				return blockShapes[2];

			}
			case WEST: {
				return blockShapes[3];

			}
			default:
				return blockShape;
		}
	}
	// @Override
	// public void appendTooltip(ItemStack stack, @Nullable BlockView world,
	// List<Text> tooltip, TooltipContext options) {
	// tooltip.add(Text.translatable("Plushables").formatted(Formatting.ITALIC).formatted(Formatting.GREEN));
	// super.appendTooltip(stack, world, tooltip, options);
	// }

}