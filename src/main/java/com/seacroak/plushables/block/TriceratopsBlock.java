package com.seacroak.plushables.block;

import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TriceratopsBlock extends SimplePlushable {
  public TriceratopsBlock() {
    super();
  }
  @Override
  public VoxelShape buildShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.join(shape, Shapes.box(0.0625, 0, 0.0625, 0.9375, 0.1875, 0.65625), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.25, 0.1875, 0.0625, 0.75, 0.9375, 0.65625), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.40625, 0, 0.625, 0.59375, 0.1875, 0.84375), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.65625, 0.8125, 0.3125, 0.78125, 1.125, 0.46875), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.21875, 0.8125, 0.3125, 0.34375, 1.125, 0.46875), BooleanOp.OR);

    return shape;
  }
}
