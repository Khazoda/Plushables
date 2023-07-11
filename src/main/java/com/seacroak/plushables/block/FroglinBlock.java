package com.seacroak.plushables.block;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class FroglinBlock extends SimplePlushable {
  public FroglinBlock() {
    super();
  }
  @Override
  public VoxelShape buildShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.join(shape, Shapes.box(0.5, 0.3125, 0.5625, 0.625, 0.4375, 0.6875), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.5, 0, 0.5625, 0.8125, 0.3125, 0.875), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.6875, 0.3125, 0.5625, 0.8125, 0.4375, 0.6875), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.5, 0, 0.5, 0.5625, 0.0625, 0.5625), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.75, 0, 0.5, 0.8125, 0.0625, 0.5625), BooleanOp.OR);
    return shape;
  }
}