package com.khazoda.plushables.block.plushable;

import com.khazoda.plushables.block.BasePlushable;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlushableRibbitBlock extends BasePlushable {
  public static final MapCodec<PlushableRibbitBlock> CODEC = simpleCodec(PlushableRibbitBlock::new);

  public PlushableRibbitBlock() {
    this(BasePlushable.defaultSettings);
  }

  public PlushableRibbitBlock(Properties settings) {
    super(settings);
  }

  @Override
  public VoxelShape getShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.join(shape, Shapes.box(0.25, 0.125, 0.25, 0.75, 0.625, 0.6875), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.5625, 0.5, 0.375, 0.8125, 0.75, 0.5625), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.1875, 0.5, 0.375, 0.4375, 0.75, 0.5625), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.5625, 0, 0.40625, 0.6875, 0.125, 0.53125), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.3125, 0, 0.40625, 0.4375, 0.125, 0.53125), BooleanOp.OR);

    return shape;
  }

  @Override
  protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
    return CODEC;
  }
}