package com.seacroak.plushables.block;

import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class CapybaraBlock extends BasePlushable {
  public CapybaraBlock() {
    super();
  }

  @Override
  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.34375, 0, 0.234375, 0.65625, 0.28125, 0.828125));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.396875, 0.1875, 0.046875, 0.6, 0.390625, 0.3125));

    return shape;
  }
}