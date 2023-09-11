package com.seacroak.plushables.block;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class BeauxBlock extends BasePlushable {
  public BeauxBlock() {
    super();
  }
  @Override
  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.40625, 0, 0.07812, 0.59375, 0.0625, 0.14062));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.3125, 0, 0.125, 0.6875, 0.28125, 0.375));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.34375, 0, 0.375, 0.65625, 0.21875, 0.875));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.4375, 0.0625, 0.07812, 0.5625, 0.125, 0.14062));

    return shape;
  }
}
