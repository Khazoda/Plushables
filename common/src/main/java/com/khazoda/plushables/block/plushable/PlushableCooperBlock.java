package com.khazoda.plushables.block.plushable;

import com.khazoda.plushables.block.BasePlushable;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlushableCooperBlock extends BasePlushable {
  public static final MapCodec<PlushableCooperBlock> CODEC = simpleCodec(PlushableCooperBlock::new);

  public PlushableCooperBlock() {
    this(BasePlushable.defaultSettings);
  }

  public PlushableCooperBlock(Properties settings) {
    super(settings);
  }

  @Override
  public VoxelShape getShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.or(shape, Shapes.create(0.296875, 0, 0.125, 0.6875, 0.625, 0.875));
    shape = Shapes.or(shape, Shapes.create(0.4375, 0.3640625, 0.05, 0.55, 0.4765625, 0.125));
    return shape;
  }

  @Override
  protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
    return CODEC;
  }
}