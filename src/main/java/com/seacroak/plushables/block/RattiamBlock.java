package com.seacroak.plushables.block;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
public class RattiamBlock extends SimplePlushable {
  public RattiamBlock() {
    super();
  }
  @Override
  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.375, 0, 0.3875, 0.4375, 0.0625, 0.6375));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.625, 0, 0.3875, 0.6875, 0.0625, 0.6375));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.34375, 0.0625, 0.5125, 0.46875, 0.25, 0.7));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.59375, 0.0625, 0.5125, 0.71875, 0.25, 0.7));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.375, 0.125, 0.3875, 0.6875, 0.5625, 0.7));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.375, 0.5625, 0.184375, 0.6875, 0.78125, 0.3875));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.4375, 0.5625, 0.059375, 0.625, 0.71875, 0.184375));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.5, 0.65625, -0.003125, 0.5625, 0.71875, 0.059375));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.375, 0.5625, 0.3875, 0.6875, 0.65625, 0.590625));

    return shape;
  }
}
