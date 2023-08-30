package com.seacroak.plushables.block;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class SnailBlock extends BasePlushable {
  public SnailBlock() {
    super();
  }
  @Override
  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.3125, 0, 0.0625, 0.6875, 0.125, 0.9375));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.5625, 0.125, 0.125, 0.625, 0.375, 0.25));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.3125, 0.125, 0.3125, 0.6875, 0.6875, 0.875));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.375, 0.125, 0.125, 0.4375, 0.375, 0.25));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.5625, 0.375, 0, 0.625, 0.5, 0.25));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.375, 0.375, 0, 0.4375, 0.5, 0.25));

    return shape;
  }
}
