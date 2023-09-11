package com.seacroak.plushables.block;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class TriceratopsBlock extends BasePlushable {
  public TriceratopsBlock() {
    super();
  }
  @Override
  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0, 0.25, 0.75, 0.875, 0.625));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.0625, 0, 0.09375, 0.25, 0.1875, 0.40625));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.75, 0, 0.0625, 0.9375, 0.1875, 0.375));

    return shape;
  }
}
