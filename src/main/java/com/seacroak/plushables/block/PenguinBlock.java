package com.seacroak.plushables.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PenguinBlock extends SimplePlushable {
  public PenguinBlock() {
    super();
  }

  @Override
  public VoxelShape buildShape() {
    super.buildShape();
    VoxelShape shape = Shapes.empty();
    shape = Shapes.join(shape, Shapes.box(0.125, 0, 0.25, 0.875, 0.875, 0.625), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.6875, 0, 0.125, 0.875, 0.0625, 0.25), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.125, 0, 0.125, 0.3125, 0.0625, 0.25), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.6875, 0.625, 0.1875, 0.9375, 0.875, 0.25), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.0625, 0.625, 0.1875, 0.3125, 0.875, 0.25), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.375, 0.625, 0.1875, 0.625, 0.75, 0.25), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.25, 0.875, 0.3125, 0.75, 0.9375, 0.5625), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.125, 0.875, 0.1875, 0.1875, 0.9375, 0.4375), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.8125, 0.875, 0.1875, 0.875, 0.9375, 0.4375), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.1875, 0.0625, 0.625, 0.8125, 0.8125, 0.6875), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.1875, 0.125, 0.6875, 0.8125, 0.5, 0.75), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.1875, 0.5625, 0.6875, 0.8125, 0.8125, 0.75), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.375, 0.125, 0.75, 0.625, 0.25, 0.8125), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.3125, 0.9375, 0.3125, 0.6875, 1, 0.5625), BooleanOp.OR);
    return shape;
  }
}
