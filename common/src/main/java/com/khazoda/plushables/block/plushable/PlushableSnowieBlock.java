package com.khazoda.plushables.block.plushable;

import com.khazoda.plushables.block.BasePlushable;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlushableSnowieBlock extends BasePlushable {
  public static final MapCodec<PlushableSnowieBlock> CODEC = simpleCodec(PlushableSnowieBlock::new);

  public PlushableSnowieBlock() {
    this(BasePlushable.defaultSettings);
  }

  public PlushableSnowieBlock(Properties settings) {
    super(settings);
  }

  @Override
  public VoxelShape getShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.join(shape, Shapes.box(0.234375, 0, 0.234375, 0.765625, 0.375, 0.765625), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.296875, 0.375, 0.296875, 0.703125, 0.765625, 0.703125), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.21875, 0.375, 0.21875, 0.78125, 0.5, 0.78125), BooleanOp.OR);

    return shape;
  }

  @Override
  protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
    return CODEC;
  }
}