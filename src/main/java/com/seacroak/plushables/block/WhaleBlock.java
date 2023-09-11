package com.seacroak.plushables.block;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class WhaleBlock extends BasePlushable {
  public WhaleBlock() {
    super();
  }
  @Override
  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0.0625, 0.1875, 0.8125, 0.6875, 0.8125));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0.6875, 0.25, 0.75, 0.75, 0.75));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0, 0.25, 0.75, 0.0625, 0.75));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.8125, 0.125, 0.25, 0.875, 0.625, 0.75));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.125, 0.125, 0.25, 0.1875, 0.625, 0.75));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0.125, 0.125, 0.75, 0.625, 0.1875));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0.125, 0.8125, 0.75, 0.625, 0.875));

    return shape;
  }
}
