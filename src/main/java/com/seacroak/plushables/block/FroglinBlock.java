package com.seacroak.plushables.block;

import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class FroglinBlock extends BasePlushable {
  public FroglinBlock() {
    super();
  }

  @Override
  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.34375, 0, 0.375, 0.65625, 0.3125, 0.6875));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.34375, 0.3125, 0.375, 0.46875, 0.4375, 0.5));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.53125, 0.3125, 0.375, 0.65625, 0.4375, 0.5));
    return shape;
  }
}