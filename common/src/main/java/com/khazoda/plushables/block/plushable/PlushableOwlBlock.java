package com.khazoda.plushables.block.plushable;

import com.khazoda.plushables.block.BasePlushable;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlushableOwlBlock extends BasePlushable {
  public static final MapCodec<com.khazoda.plushables.block.plushable.PlushablePenguinBlock> CODEC = simpleCodec(com.khazoda.plushables.block.plushable.PlushablePenguinBlock::new);

  public PlushableOwlBlock() {
    this(BasePlushable.defaultSettings);
  }

  public PlushableOwlBlock(Properties settings) {
    super(settings);
  }

  @Override
  public VoxelShape getShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.or(shape, Shapes.create(0.1875, 0.1875, 0.1875, 0.8125, 0.625, 0.8125));
    shape = Shapes.or(shape, Shapes.create(0.125, 0.125, 0.25, 0.25, 0.6875, 0.75));
    shape = Shapes.or(shape, Shapes.create(0.75, 0.125, 0.25, 0.875, 0.6875, 0.75));
    shape = Shapes.or(shape, Shapes.create(0.375, 0, 0.3125, 0.625, 0.1875, 0.6875));
    return shape;
  }

  @Override
  protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
    return CODEC;
  }
}