package com.seacroak.plushables.block;

import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class ShrumpBlock extends BasePlushable {
  public ShrumpBlock() {
    super();
  }

  @Override
  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.375, 0, 0.375, 0.625, 0.375, 0.625));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.359375, 0, 0.359375, 0.640625, 0.171875, 0.640625));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.3125, 0.375, 0.3125, 0.6875, 0.6875, 0.6875));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0.5625, 0.25, 0.75, 0.875, 0.75));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.3125, 0.875, 0.3125, 0.6875, 0.9375, 0.6875));

    return shape;
  }
}