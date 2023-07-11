package com.seacroak.plushables.block;

import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class GoblinBlock extends SimplePlushable {
  public GoblinBlock() {
    super();
  }
  @Override
  public VoxelShape buildShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.join(shape, Shapes.box(0.375, 0, 0.375, 0.625, 0.375, 0.625), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.3125, 0.375, 0.3125, 0.6875, 0.6875, 0.6875),BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(-0.0625, 0.5625, 0.5, 0.3125, 0.625, 0.5625),BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.6875, 0.5625, 0.5, 1.0625, 0.625, 0.5625),BooleanOp.OR);

    return shape;
  }
}
