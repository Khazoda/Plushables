package com.seacroak.plushables.block;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class UnicornBlock extends SimplePlushable {
  public UnicornBlock() {
    super();
  }
  @Override
  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.578125, 0.375, 0.03125, 0.703125, 0.5, 0.1875));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.53125, 0, 0.25, 0.75, 0.25, 0.5625));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.53125, 0.25, 0.25, 0.75, 0.375, 0.4375));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.546875, 0.375, 0.1875, 0.734375, 0.5, 0.34375));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.546875, 0.5, 0.15625, 0.734375, 0.609375, 0.3125));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.578125, 0.5, 0.0625, 0.703125, 0.546875, 0.15625));


    return shape;
  }
}
