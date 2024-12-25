package com.khazoda.plushables.block.plushable;

import com.khazoda.plushables.block.BasePlushable;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlushableConductorBlock extends BasePlushable {
  public static final MapCodec<PlushableConductorBlock> CODEC = simpleCodec(PlushableConductorBlock::new);

  public PlushableConductorBlock() {
    this(BasePlushable.defaultSettings);
  }

  public PlushableConductorBlock(Properties settings) {
    super(settings);
  }

  @Override
  public VoxelShape getShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.or(shape, Shapes.create(0.25, 0.1875, 0.3125, 0.75, 0.5, 0.6875));
    shape = Shapes.or(shape, Shapes.create(0.75, 0.015625, 0.375, 0.9375, 0.578125, 0.625));
    shape = Shapes.or(shape, Shapes.create(0.0625, 0.015625, 0.375, 0.25, 0.578125, 0.625));
    shape = Shapes.or(shape, Shapes.create(0.3125, 0, 0.375, 0.6875, 0.25, 0.625));
    shape = Shapes.or(shape, Shapes.create(0.25, 0.5, 0.25, 0.75, 1, 0.75));
    shape = Shapes.or(shape, Shapes.create(0.1875, 0, 0.125, 0.375, 0.25, 0.4375));
    shape = Shapes.or(shape, Shapes.create(0.625, 0, 0.125, 0.8125, 0.25, 0.4375));
    return shape;
  }

  @Override
  protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
    return CODEC;
  }
}