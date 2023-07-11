package com.seacroak.plushables.block;


import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class FoxBlock extends SimplePlushable {
  public FoxBlock() {
    super();
  }

  @Override
  public VoxelShape buildShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.join(shape, Shapes.box(0.125, 0, 0.1875, 0.1875, 0.0625, 0.3125), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.1875, 0, 0.1875, 0.5, 0.3125, 0.5), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.1875, 0.3125, 0.25, 0.3125, 0.375, 0.3125), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.375, 0.3125, 0.25, 0.5, 0.375, 0.3125), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.25, 0.125, 0.125, 0.4375, 0.1875, 0.1875), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.5, 0, 0.1875, 0.5625, 0.0625, 0.3125), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.125, 0, 0.4375, 0.1875, 0.0625, 0.5625), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.5, 0, 0.4375, 0.5625, 0.0625, 0.5625), BooleanOp.OR);

    return shape;
  }

}