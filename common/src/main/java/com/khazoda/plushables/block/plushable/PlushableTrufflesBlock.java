package com.khazoda.plushables.block.plushable;

import com.khazoda.plushables.block.BasePlushable;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlushableTrufflesBlock extends BasePlushable {
  public static final MapCodec<PlushableTrufflesBlock> CODEC = simpleCodec(PlushableTrufflesBlock::new);

  public PlushableTrufflesBlock() {
    this(BasePlushable.defaultSettings);
  }

  public PlushableTrufflesBlock(Properties settings) {
    super(settings);
  }

  @Override
  public VoxelShape useShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.or(shape, Shapes.create(0.1875, 0, 0.25, 0.8125, 0.5, 0.75));
    shape = Shapes.or(shape, Shapes.create(0.25, 0, 0.0625, 0.3125, 0.0625, 0.125));
    shape = Shapes.or(shape, Shapes.create(0.6875, 0, 0.0625, 0.75, 0.0625, 0.125));
    shape = Shapes.or(shape, Shapes.create(0.25, 0.46875, 0.125, 0.375, 0.59375, 0.125));
    shape = Shapes.or(shape, Shapes.create(0.375, 0.125, 0.0625, 0.625, 0.25, 0.125));
    shape = Shapes.or(shape, Shapes.create(0.625, 0.46875, 0.125, 0.75, 0.59375, 0.125));
    shape = Shapes.or(shape, Shapes.create(0.4375, 0.25, 0.890625, 0.5625, 0.375, 0.890625));
    shape = Shapes.or(shape, Shapes.create(0.21875, 0, 0.75, 0.78125, 0.46875, 0.875));
    shape = Shapes.or(shape, Shapes.create(0.71875, 0, 0.875, 0.78125, 0.0625, 0.9375));
    shape = Shapes.or(shape, Shapes.create(0.21875, 0, 0.875, 0.28125, 0.0625, 0.9375));
    shape = Shapes.or(shape, Shapes.create(0.21875, 0, 0.125, 0.78125, 0.46875, 0.25));
    return shape;
  }

  @Override
  protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
    return CODEC;
  }
}