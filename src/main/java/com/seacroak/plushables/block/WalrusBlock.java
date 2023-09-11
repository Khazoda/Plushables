package com.seacroak.plushables.block;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class WalrusBlock extends BasePlushable {
  public WalrusBlock() {
    super();
  }
  @Override
  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0, 0.0625, 0.75, 0.46875, 0.75));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.296875, 0.46875, 0.0625, 0.703125, 0.78125, 0.390625));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.296875, 0.46875, 0.390625, 0.703125, 0.59375, 0.5625));
    return shape;
  }
}
