package com.seacroak.plushables.block;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class UnicornBlock extends BasePlushable {
  public UnicornBlock() {
    super();
  }
  @Override
  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.359375, 0, 0.3125, 0.609375, 0.3125, 0.625));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.359375, 0.3125, 0.1875, 0.609375, 0.5625, 0.5));

    return shape;
  }
}
