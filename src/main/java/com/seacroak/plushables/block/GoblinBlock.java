package com.seacroak.plushables.block;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class GoblinBlock extends BasePlushable {
  public GoblinBlock() {
    super();
  }
  @Override
  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.375, 0, 0.375, 0.625, 0.375, 0.625));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.3125, 0.375, 0.3125, 0.6875, 0.6875, 0.6875));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(-0.0625, 0.5625, 0.5, 0.3125, 0.625, 0.5625));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.6875, 0.5625, 0.5, 1.0625, 0.625, 0.5625));

    return shape;
  }
}
