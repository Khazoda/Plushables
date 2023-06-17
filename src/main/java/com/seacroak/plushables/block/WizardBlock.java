package com.seacroak.plushables.block;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class WizardBlock extends SimplePlushable {
  public WizardBlock() {
    super();
  }
  @Override
  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0, 0.25, 0.75, 0.6406, 0.625));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.375, 0.9375, 0.1562, 0.625, 1, 0.5));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.3125, 0.625, 0.1562, 0.6875, 0.9375, 0.5625));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0, 0.125, 0.375, 0.0937, 0.3125));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.4218, 0.2968, 0.2187, 0.5781, 0.4375, 0.25));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.625, 0, 0.125, 0.75, 0.0937, 0.3125));

    return shape;
  }
}
