package com.seacroak.plushables.block;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class RaptorBlock extends BasePlushable {
  public RaptorBlock() {
    super();
  }
  @Override
  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.3125, 0, 0.078125, 0.6875, 0.734375, 0.59375));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.421875, 0.59375, -0.0625, 0.59375, 0.703125, 0.078125));
    return shape;
  }
}
