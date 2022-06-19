package com.seacroak.plushables.block;

import com.seacroak.plushables.util.VoxelShapeUtils;

import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class PenguinBlock extends SimplePlushable {
	public PenguinBlock() {
	}

	static final VoxelShape blockShape = getShape();
	static final VoxelShape[] blockShapes = { blockShape,
			VoxelShapeUtils.rotateShape(Direction.NORTH, Direction.EAST, blockShape),
			VoxelShapeUtils.rotateShape(Direction.NORTH, Direction.SOUTH, blockShape),
			VoxelShapeUtils.rotateShape(Direction.NORTH, Direction.WEST, blockShape) };

	public static VoxelShape getShape() {
		VoxelShape shape = VoxelShapes.empty();
		shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.125, 0, 0.25, 0.875, 0.875, 0.625));
		shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.6875, 0, 0.125, 0.875, 0.0625, 0.25));
		shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.125, 0, 0.125, 0.3125, 0.0625, 0.25));
		shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.6875, 0.625, 0.1875, 0.9375, 0.875, 0.25));
		shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.0625, 0.625, 0.1875, 0.3125, 0.875, 0.25));
		shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.375, 0.625, 0.1875, 0.625, 0.75, 0.25));
		shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0.875, 0.3125, 0.75, 0.9375, 0.5625));
		shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.125, 0.875, 0.1875, 0.1875, 0.9375, 0.4375));
		shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.8125, 0.875, 0.1875, 0.875, 0.9375, 0.4375));
		shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0.0625, 0.625, 0.8125, 0.8125, 0.6875));
		shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0.125, 0.6875, 0.8125, 0.5, 0.75));
		shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0.5625, 0.6875, 0.8125, 0.8125, 0.75));
		shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.375, 0.125, 0.75, 0.625, 0.25, 0.8125));
		shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.3125, 0.9375, 0.3125, 0.6875, 1, 0.5625));

		return shape;
	}

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
}