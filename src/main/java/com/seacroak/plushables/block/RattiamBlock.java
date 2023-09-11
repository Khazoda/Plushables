package com.seacroak.plushables.block;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
public class RattiamBlock extends BasePlushable {
  public RattiamBlock() {
    super();
  }
  @Override
  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.359375, 0.59375, 0.0625, 0.640625, 0.78125, 0.25));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.40625, 0.59375, -0.0625, 0.59375, 0.71875, 0.0625));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.46875, 0.65625, -0.125, 0.53125, 0.71875, -0.0625));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.3125, 0, 0.25, 0.6875, 0.78125, 0.5625));

    return shape;
  }
}
