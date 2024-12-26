package com.khazoda.plushables.block.plushable;

import com.khazoda.plushables.block.BasePlushable;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlushableTigerBlock extends BasePlushable {
  public static final MapCodec<PlushableTigerBlock> CODEC = simpleCodec(PlushableTigerBlock::new);

  public PlushableTigerBlock() {
    this(BasePlushable.defaultSettings);
  }

  public PlushableTigerBlock(Properties settings) {
    super(settings);
  }

  @Override
  public VoxelShape useShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.or(shape, Shapes.create(0.359375, 0, 0.28125, 0.65625, 0.3125, 0.71875));
    shape = Shapes.or(shape, Shapes.create(0.40625, 0.3125, 0.28125, 0.59375, 0.5, 0.5));
    return shape;
  }

  @Override
  protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
    return CODEC;
  }
}