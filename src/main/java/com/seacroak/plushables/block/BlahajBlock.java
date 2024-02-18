package com.seacroak.plushables.block;

import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class BlahajBlock extends BasePlushable {
  public BlahajBlock() {
    super();
  }

  @Override
  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.375, 0, 0, 0.625, 0.25, 0.5625));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.4375, 0.0625, 0.5625, 0.5625, 0.1875, 1));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.40625, 0.03125, 0.5625, 0.59375, 0.21875, 0.6875));

    return shape;
  }
}