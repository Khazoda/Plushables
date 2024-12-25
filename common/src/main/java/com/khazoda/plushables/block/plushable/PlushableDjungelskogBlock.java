package com.khazoda.plushables.block.plushable;

import com.khazoda.plushables.block.BasePlushable;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlushableDjungelskogBlock extends BasePlushable {
  public static final MapCodec<PlushableDjungelskogBlock> CODEC = simpleCodec(PlushableDjungelskogBlock::new);

  public PlushableDjungelskogBlock() {
    this(BasePlushable.defaultSettings);
  }

  public PlushableDjungelskogBlock(Properties settings) {
    super(settings);
  }

  @Override
  public VoxelShape getShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.or(shape, Shapes.create(0.1875, 0, 0.0625, 0.3125, 0.125, 0.25));
    shape = Shapes.or(shape, Shapes.create(0.6875, 0, 0.0625, 0.8125, 0.125, 0.25));
    shape = Shapes.or(shape, Shapes.create(0.1875, 0, 0.25, 0.8125, 0.5, 0.75));
    shape = Shapes.or(shape, Shapes.create(0.3125, 0.09375, 0.203125, 0.6875, 0.484375, 0.28125));
    shape = Shapes.or(shape, Shapes.create(0.21875, 0.5, 0.3125, 0.78125, 0.75, 0.75));
    shape = Shapes.or(shape, Shapes.create(0.3125, 0.75, 0.25, 0.6875, 1.03125, 0.6875));
    shape = Shapes.or(shape, Shapes.create(0.4375, 0.75, 0.125, 0.5625, 0.875, 0.25));
    shape = Shapes.or(shape, Shapes.create(0.25, 0, 0.75, 0.75, 0.5625, 0.875));
    shape = Shapes.or(shape, Shapes.create(0.4375, 0, 0.875, 0.5625, 0.125, 0.9375));
    return shape;
  }

  @Override
  protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
    return CODEC;
  }
}