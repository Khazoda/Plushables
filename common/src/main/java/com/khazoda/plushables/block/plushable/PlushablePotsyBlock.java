package com.khazoda.plushables.block.plushable;

import com.khazoda.plushables.block.BasePlushable;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlushablePotsyBlock extends BasePlushable {
  public static final MapCodec<PlushablePotsyBlock> CODEC = simpleCodec(PlushablePotsyBlock::new);

  public PlushablePotsyBlock() {
    this(BasePlushable.defaultSettings);
  }

  public PlushablePotsyBlock(Properties settings) {
    super(settings);
  }

  @Override
  public VoxelShape getShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.or(shape, Shapes.create(0.125, 0, 0.125, 0.875, 0.6875, 0.875));
    shape = Shapes.or(shape, Shapes.create(0.1875, 0.6875, 0.1875, 0.8125, 0.75, 0.8125));

    return shape;
  }

  @Override
  protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
    return CODEC;
  }
}