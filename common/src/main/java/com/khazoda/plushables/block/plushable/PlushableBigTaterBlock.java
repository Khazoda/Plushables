package com.khazoda.plushables.block.plushable;

import com.khazoda.plushables.block.BasePlushable;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlushableBigTaterBlock extends BasePlushable {
  public static final MapCodec<PlushableBigTaterBlock> CODEC = simpleCodec(PlushableBigTaterBlock::new);

  public PlushableBigTaterBlock() {
    this(BasePlushable.defaultSettings);
  }

  public PlushableBigTaterBlock(Properties settings) {
    super(settings);
  }

  @Override
  public VoxelShape getShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.or(shape, Shapes.create(0.125, 0.1875, 0.1875, 0.875, 0.25, 0.8125));
    shape = Shapes.or(shape, Shapes.create(0.125, 0.25, 0.125, 0.875, 1, 0.875));
    return shape;
  }

  @Override
  protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
    return CODEC;
  }
}