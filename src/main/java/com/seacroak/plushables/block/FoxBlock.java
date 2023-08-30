package com.seacroak.plushables.block;

import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class FoxBlock extends BasePlushable {
  public FoxBlock() {
    super();
  }

  @Override
  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.34375, 0, 0.3125, 0.65625, 0.3125, 0.625));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.34375, 0.3125, 0.375, 0.46875, 0.375, 0.4375));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.53125, 0.3125, 0.375, 0.65625, 0.375, 0.4375));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.40625, 0.125, 0.25, 0.59375, 0.1875, 0.3125));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.28125, 0, 0.3125, 0.34375, 0.0625, 0.4375));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.65625, 0, 0.3125, 0.71875, 0.0625, 0.4375));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.28125, 0, 0.5625, 0.34375, 0.0625, 0.6875));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.65625, 0, 0.5625, 0.71875, 0.0625, 0.6875));

    return shape;
  }

}