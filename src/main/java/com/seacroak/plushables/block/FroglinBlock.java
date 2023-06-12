package com.seacroak.plushables.block;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
public class FroglinBlock extends SimplePlushable {
  public FroglinBlock() {
    super();
  }
  @Override
  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.5, 0.3125, 0.5625, 0.625, 0.4375, 0.6875));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.5, 0, 0.5625, 0.8125, 0.3125, 0.875));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.6875, 0.3125, 0.5625, 0.8125, 0.4375, 0.6875));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.5, 0, 0.5, 0.5625, 0.0625, 0.5625));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.75, 0, 0.5, 0.8125, 0.0625, 0.5625));
    return shape;
  }
}