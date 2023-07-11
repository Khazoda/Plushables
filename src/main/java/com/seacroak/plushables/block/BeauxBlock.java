package com.seacroak.plushables.block;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BeauxBlock extends SimplePlushable {
  public BeauxBlock() {
    super();
  }
  @Override
  public VoxelShape buildShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.join(shape, Shapes.box(0.40625, 0, 0.07812, 0.59375, 0.0625, 0.14062), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.3125, 0, 0.125, 0.6875, 0.28125, 0.375), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.34375, 0, 0.375, 0.65625, 0.21875, 0.875), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.4375, 0.0625, 0.07812, 0.5625, 0.125, 0.14062), BooleanOp.OR);

    return shape;
  }
}
