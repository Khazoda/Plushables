package com.seacroak.plushables.block;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class RaptorBlock extends SimplePlushable {
  public RaptorBlock() {
    super();
  }
  @Override
  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0, 0.25, 0.40625, 0.25, 0.5));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0.25, 0.25, 0.40625, 0.375, 0.375));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.2343, 0.3125, 0.1093, 0.4218, 0.5, 0.3125));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.2656, 0.3437, 0.0312, 0.375, 0.4687, 0.1093));

    return shape;
  }
}
