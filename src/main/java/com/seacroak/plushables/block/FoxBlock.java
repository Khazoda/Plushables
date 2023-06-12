package com.seacroak.plushables.block;

import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class FoxBlock extends SimplePlushable {
  public FoxBlock() {
    super();
  }

  @Override
  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.125, 0, 0.1875, 0.1875, 0.0625, 0.3125));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0, 0.1875, 0.5, 0.3125, 0.5));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0.3125, 0.25, 0.3125, 0.375, 0.3125));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.375, 0.3125, 0.25, 0.5, 0.375, 0.3125));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0.125, 0.125, 0.4375, 0.1875, 0.1875));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.5, 0, 0.1875, 0.5625, 0.0625, 0.3125));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.125, 0, 0.4375, 0.1875, 0.0625, 0.5625));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.5, 0, 0.4375, 0.5625, 0.0625, 0.5625));

    return shape;
  }

}