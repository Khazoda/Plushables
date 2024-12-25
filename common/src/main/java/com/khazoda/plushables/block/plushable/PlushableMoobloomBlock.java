package com.khazoda.plushables.block.plushable;

import com.khazoda.plushables.block.BasePlushable;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlushableMoobloomBlock extends BasePlushable {
  public static final MapCodec<PlushableMoobloomBlock> CODEC = simpleCodec(PlushableMoobloomBlock::new);

  public PlushableMoobloomBlock() {
    this(BasePlushable.defaultSettings);
  }

  public PlushableMoobloomBlock(Properties settings) {
    super(settings);
  }

  @Override
  public VoxelShape getShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.or(shape, Shapes.create(0.3125, 0.1875, 0.125, 0.6875, 0.5, 0.4375));
    shape = Shapes.or(shape, Shapes.create(0.375, 0.0625, 0.3125, 0.625, 0.375, 0.8125));
    shape = Shapes.or(shape, Shapes.create(0.4375, 0, 0.59375, 0.5625, 0.0625, 0.71875));
    return shape;
  }

  @Override
  protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
    return CODEC;
  }
}