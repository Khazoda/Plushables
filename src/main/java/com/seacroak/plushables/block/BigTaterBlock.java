package com.seacroak.plushables.block;

import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class BigTaterBlock extends BasePlushable {
  public BigTaterBlock() {
    super();
  }

  @Override
  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.125, 0.1875, 0.1875, 0.875, 0.25, 0.8125));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.125, 0.25, 0.125, 0.875, 1, 0.875));
    return shape;
  }

}