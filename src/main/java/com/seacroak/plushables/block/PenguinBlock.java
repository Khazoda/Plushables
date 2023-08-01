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
		super();
	}
  @Override
	public VoxelShape getShape() {
		VoxelShape shape = VoxelShapes.empty();
		shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.125, 0, 0.25, 0.875, 0.875, 0.625));
		shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.75, 0.6875, 0.1875, 0.875, 0.8125, 0.25));
		shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.125, 0.6875, 0.1875, 0.25, 0.8125, 0.25));
		shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.375, 0.625, 0.125, 0.625, 0.75, 0.25));
		shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0.0625, 0.625, 0.8125, 0.8125, 0.6875));
		return shape;
	}
}