package com.seacroak.plushables.block;

import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class AnimatronicBlock extends BasePlushable {
  public AnimatronicBlock() {
    super();
  }

  @Override
  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.34375, 0.390625, 0.375, 0.65625, 0.703125, 0.6875));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.40625, 0, 0.46875, 0.59375, 0.421875, 0.59375));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.40625, 0, 0.4375, 0.59375, 0.03125, 0.625));

    return shape;
  }
}