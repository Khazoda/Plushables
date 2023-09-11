package com.seacroak.plushables.block;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class TrufflesBlock extends BasePlushable {
  public TrufflesBlock() {
    super();
  }
  @Override
  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0, 0.25, 0.8125, 0.5, 0.75));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0, 0.0625, 0.3125, 0.0625, 0.125));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.6875, 0, 0.0625, 0.75, 0.0625, 0.125));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0.46875, 0.125, 0.375, 0.59375, 0.125));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.375, 0.125, 0.0625, 0.625, 0.25, 0.125));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.625, 0.46875, 0.125, 0.75, 0.59375, 0.125));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.4375, 0.25, 0.890625, 0.5625, 0.375, 0.890625));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.21875, 0, 0.75, 0.78125, 0.46875, 0.875));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.71875, 0, 0.875, 0.78125, 0.0625, 0.9375));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.21875, 0, 0.875, 0.28125, 0.0625, 0.9375));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.21875, 0, 0.125, 0.78125, 0.46875, 0.25));

    return shape;
  }
}