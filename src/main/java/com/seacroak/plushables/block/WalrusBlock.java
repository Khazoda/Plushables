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
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.315625, 0.0625, 0.40625, 0.684375, 0.46875, 0.71875));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.30625, 0.40625, 0.0625, 0.69375, 0.78125, 0.375));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.40625, 0.46875, 0.03125, 0.59375, 0.65625, 0.21875));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.309375, 0.125, 0.15625, 0.690625, 0.59375, 0.53125));
    return shape;
  }
}
