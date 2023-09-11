package com.seacroak.plushables.block;

import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class MoobloomBlock extends BasePlushable {
  public MoobloomBlock() {
    super();
  }

  @Override
  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.3125, 0.1875, 0.125, 0.6875, 0.5, 0.4375));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.375, 0.0625, 0.3125, 0.625, 0.375, 0.8125));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.4375, 0, 0.59375, 0.5625, 0.0625, 0.71875));
    return shape;
  }
}