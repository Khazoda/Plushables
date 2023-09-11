package com.seacroak.plushables.block;

import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;


public class PigBlock extends BasePlushable {

  public PigBlock() {
    super();
  }

  @Override
  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0.003125, 0.21875, 0.75, 0.315625, 0.875));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.5625, 0, 0.875, 0.75, 0.1875, 1));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0, 0.875, 0.4375, 0.1875, 1));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.34375, 0.003125, 0.03125, 0.65625, 0.315625, 0.21875));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.421875, 0.0625, -0.03125, 0.578125, 0.15625, 0.03125));

    return shape;
  }
}