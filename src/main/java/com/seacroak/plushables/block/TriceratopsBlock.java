package com.seacroak.plushables.block;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class TriceratopsBlock extends SimplePlushable {
  public TriceratopsBlock() {
    super();
  }
  @Override
  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.0625, 0, 0.0625, 0.9375, 0.1875, 0.65625));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0.1875, 0.0625, 0.75, 0.9375, 0.65625));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.65625, 0.8125, 0.3125, 0.78125, 1.1875, 0.46875));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.21875, 0.8125, 0.3125, 0.34375, 1.1875, 0.46875));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.40625, 0, 0.625, 0.59375, 0.1875, 0.84375));

    return shape;
  }
}
