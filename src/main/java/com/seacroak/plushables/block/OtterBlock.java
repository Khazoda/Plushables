package com.seacroak.plushables.block;

import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class OtterBlock extends BasePlushable {
  public OtterBlock() {
    super();
  }

  @Override
  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.4375, 0.15625, 0, 0.5625, 0.25, 0.0625));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.375, 0.15625, 0.0625, 0.625, 0.40625, 0.3125));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.390625, 0.03125, 0.15625, 0.609375, 0.28125, 0.65625));
    return shape;
  }
}