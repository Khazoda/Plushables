package com.seacroak.plushables.block;

import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class DormouseBlock extends BasePlushable {
  public DormouseBlock() {
    super();
  }

  @Override
  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.3125, 0.125, 0.25, 0.6875, 0.4375, 0.8125));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.375, 0.1875, 0.1875, 0.625, 0.3125, 0.25));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0.0625, 0.28125, 0.3125, 0.1875, 0.40625));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0.0625, 0.59375, 0.3125, 0.25, 0.78125));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.6875, 0.0625, 0.28125, 0.75, 0.1875, 0.40625));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.6875, 0.0625, 0.59375, 0.75, 0.25, 0.78125));
    return shape;
  }
}
