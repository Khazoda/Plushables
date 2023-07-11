package com.seacroak.plushables.block;

import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class DjungelskogBlock extends SimplePlushable {
  public DjungelskogBlock() {
    super();
  }

  @Override
  public VoxelShape buildShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.join(shape, Shapes.box(0.1875, 0, 0.0625, 0.3125, 0.125, 0.25), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.6875, 0, 0.0625, 0.8125, 0.125, 0.25), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.1875, 0, 0.25, 0.8125, 0.5, 0.75), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.3125, 0.09375, 0.203125, 0.6875, 0.484375, 0.28125), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.21875, 0.5, 0.3125, 0.78125, 0.75, 0.75), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.3125, 0.75, 0.25, 0.6875, 1.03125, 0.6875), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.4375, 0.75, 0.125, 0.5625, 0.875, 0.25), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.25, 0, 0.75, 0.75, 0.5625, 0.875), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.4375, 0, 0.875, 0.5625, 0.125, 0.9375), BooleanOp.OR);
    return shape;
  }
}
