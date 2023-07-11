package com.seacroak.plushables.block;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class RaptorBlock extends SimplePlushable {
  public RaptorBlock() {
    super();
  }
  @Override
  public VoxelShape buildShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.join(shape, Shapes.box(0.25, 0, 0.25, 0.40625, 0.25, 0.5), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.25, 0.25, 0.25, 0.40625, 0.375, 0.375), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.2343, 0.3125, 0.1093, 0.4218, 0.5, 0.3125), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.2656, 0.3437, 0.0312, 0.375, 0.4687, 0.1093), BooleanOp.OR);

    return shape;
  }
}
