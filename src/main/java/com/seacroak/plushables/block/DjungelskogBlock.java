package com.seacroak.plushables.block;

import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class DjungelskogBlock extends BasePlushable {
  public DjungelskogBlock() {
    super();
  }

  @Override
  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0, 0.0625, 0.3125, 0.125, 0.25));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.6875, 0, 0.0625, 0.8125, 0.125, 0.25));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0, 0.25, 0.8125, 0.5, 0.75));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.3125, 0.09375, 0.203125, 0.6875, 0.484375, 0.28125));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.21875, 0.5, 0.3125, 0.78125, 0.75, 0.75));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.3125, 0.75, 0.25, 0.6875, 1.03125, 0.6875));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.4375, 0.75, 0.125, 0.5625, 0.875, 0.25));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0, 0.75, 0.75, 0.5625, 0.875));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.4375, 0, 0.875, 0.5625, 0.125, 0.9375));
    return shape;
  }
}
