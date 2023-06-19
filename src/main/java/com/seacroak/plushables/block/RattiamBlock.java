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
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.375, 0, 0.25, 0.6875, 0.75, 0.8125));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.375, 0.5625, 0.0625, 0.6875, 0.8125, 0.375));
    return shape;
  }
}
