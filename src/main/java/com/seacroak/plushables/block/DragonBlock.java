package com.seacroak.plushables.block;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class DragonBlock extends SimplePlushable {
  public DragonBlock() {
    super();
  }
  @Override
  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.359375, 0, 0.28125, 0.640625, 0.125, 0.675));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.4125, 0.125, 0.33125, 0.59375, 0.19375, 0.5625));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.353125, 0.125, 0.5625, 0.64375, 0.19375, 0.6875));

    return shape;
  }
}
