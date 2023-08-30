package com.seacroak.plushables.block;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class WhelplingBlock extends BasePlushable {
  public WhelplingBlock() {
    super();
  }
  @Override
  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.3375, 0, 0.271875, 0.6703125, 0.1890625, 0.690625));

    return shape;
  }
}
