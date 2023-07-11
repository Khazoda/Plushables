package com.seacroak.plushables.block;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class RattiamBlock extends SimplePlushable {
  public RattiamBlock() {
    super();
  }
  @Override
  public VoxelShape buildShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.join(shape, Shapes.box(0.375, 0, 0.25, 0.6875, 0.75, 0.8125), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.375, 0.5625, 0.0625, 0.6875, 0.8125, 0.375), BooleanOp.OR);
    return shape;
  }
}
