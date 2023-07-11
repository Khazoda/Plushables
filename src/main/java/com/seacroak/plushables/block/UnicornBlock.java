package com.seacroak.plushables.block;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class UnicornBlock extends SimplePlushable {
  public UnicornBlock() {
    super();
  }
  @Override
  public VoxelShape buildShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.join(shape, Shapes.box(0.578125, 0.375, 0.03125, 0.703125, 0.5, 0.1875), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.53125, 0, 0.25, 0.75, 0.25, 0.5625), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.53125, 0.25, 0.25, 0.75, 0.375, 0.4375), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.546875, 0.375, 0.1875, 0.734375, 0.5, 0.34375), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.546875, 0.5, 0.15625, 0.734375, 0.609375, 0.3125), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.578125, 0.5, 0.0625, 0.703125, 0.546875, 0.15625), BooleanOp.OR);


    return shape;
  }
}
