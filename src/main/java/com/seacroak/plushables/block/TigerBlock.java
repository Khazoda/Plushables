package com.seacroak.plushables.block;

import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class TigerBlock extends BasePlushable {
  public TigerBlock() {
    super();
  }

  @Override
  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.359375, 0, 0.28125, 0.65625, 0.3125, 0.71875));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.40625, 0.3125, 0.28125, 0.59375, 0.5, 0.5));
  return shape;
  }

}