package com.seacroak.plushables.block;

import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class ConductorBlock extends BasePlushable {
  public ConductorBlock() {
    super();
  }

  @Override
  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0.1875, 0.3125, 0.75, 0.5, 0.6875));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.75, 0.015625, 0.375, 0.9375, 0.578125, 0.625));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.0625, 0.015625, 0.375, 0.25, 0.578125, 0.625));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.3125, 0, 0.375, 0.6875, 0.25, 0.625));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0.5, 0.25, 0.75, 1, 0.75));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0, 0.125, 0.375, 0.25, 0.4375));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.625, 0, 0.125, 0.8125, 0.25, 0.4375));

    return shape;
  }
}