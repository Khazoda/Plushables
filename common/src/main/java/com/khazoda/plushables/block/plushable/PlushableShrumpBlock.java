package com.khazoda.plushables.block.plushable;

import com.khazoda.plushables.block.BasePlushable;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlushableShrumpBlock extends BasePlushable {
  public static final MapCodec<PlushableShrumpBlock> CODEC = simpleCodec(PlushableShrumpBlock::new);

  public PlushableShrumpBlock() {
    this(BasePlushable.defaultSettings);
  }

  public PlushableShrumpBlock(Properties settings) {
    super(settings);
  }

  @Override
  public VoxelShape getShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.or(shape, Shapes.create(0.375, 0, 0.375, 0.625, 0.375, 0.625));
    shape = Shapes.or(shape, Shapes.create(0.359375, 0, 0.359375, 0.640625, 0.171875, 0.640625));
    shape = Shapes.or(shape, Shapes.create(0.3125, 0.375, 0.3125, 0.6875, 0.6875, 0.6875));
    shape = Shapes.or(shape, Shapes.create(0.25, 0.5625, 0.25, 0.75, 0.875, 0.75));
    shape = Shapes.or(shape, Shapes.create(0.3125, 0.875, 0.3125, 0.6875, 0.9375, 0.6875));
    return shape;
  }

  @Override
  protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
    return CODEC;
  }
}