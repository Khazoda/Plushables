package com.seacroak.plushables.block;

import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class OctoplushBlock extends BasePlushable {
  public OctoplushBlock() {
    super();
  }

  @Override
  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.28125, 0.015625, 0.28125, 0.71875, 0.46875, 0.71875));
    return shape;
  }
}