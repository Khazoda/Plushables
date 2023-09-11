package com.seacroak.plushables.block;

import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class HampterBlock extends BasePlushable {
  public HampterBlock() {
    super();
  }

  @Override
  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0.1875, 0.3125, 0.75, 0.625, 0.75));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.3125, 0.4375, 0.1875, 0.6875, 0.8125, 0.5625));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0, 0.25, 0.75, 0.1875, 0.75));

    return shape;
  }
}