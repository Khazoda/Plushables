package com.seacroak.plushables.block;

import com.seacroak.plushables.util.VoxelShapeUtils;

import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class FroglinBlock extends SimplePlushable {
	public FroglinBlock() {
		super();
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
		shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.5, 0.3125, 0.5625, 0.625, 0.4375, 0.6875));
		shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.5, 0, 0.5625, 0.8125, 0.3125, 0.875));
		shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.6875, 0.3125, 0.5625, 0.8125, 0.4375, 0.6875));
		shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.5, 0, 0.5, 0.5625, 0.0625, 0.5625));
		shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.75, 0, 0.5, 0.8125, 0.0625, 0.5625));
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