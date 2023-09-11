package com.seacroak.plushables.block;

import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class MammothBlock extends BasePlushable {
  public MammothBlock() {
    super();
  }

  @Override
  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.3125, 0.125, 0.25, 0.6875, 0.5625, 0.5625));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.3125, 0.125, 0.5625, 0.6875, 0.5, 0.75));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.59375, 0, 0.25, 0.71875, 0.25, 0.375));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.59375, 0, 0.625, 0.71875, 0.25, 0.75));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.28125, 0, 0.25, 0.40625, 0.25, 0.375));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.28125, 0, 0.625, 0.40625, 0.25, 0.75));

    return shape;
  }
}
