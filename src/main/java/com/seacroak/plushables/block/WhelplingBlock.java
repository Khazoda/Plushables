package com.seacroak.plushables.block;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WhelplingBlock extends SimplePlushable {
  public WhelplingBlock() {
    super();
  }
  @Override
  public VoxelShape buildShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.join(shape, Shapes.box(0.359375, 0, 0.28125, 0.640625, 0.125, 0.675), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.4125, 0.125, 0.33125, 0.59375, 0.19375, 0.5625), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.353125, 0.125, 0.5625, 0.64375, 0.19375, 0.6875), BooleanOp.OR);

    return shape;
  }
}
