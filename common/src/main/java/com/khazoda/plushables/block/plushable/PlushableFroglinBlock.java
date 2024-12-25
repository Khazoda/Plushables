package com.khazoda.plushables.block.plushable;

import com.khazoda.plushables.block.BasePlushable;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlushableFroglinBlock extends BasePlushable {
  public static final MapCodec<PlushableFroglinBlock> CODEC = simpleCodec(PlushableFroglinBlock::new);

  public PlushableFroglinBlock() {
    this(BasePlushable.defaultSettings);
  }

  public PlushableFroglinBlock(Properties settings) {
    super(settings);
  }

  @Override
  public VoxelShape getShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.or(shape, Shapes.create(0.34375, 0, 0.375, 0.65625, 0.3125, 0.6875));
    shape = Shapes.or(shape, Shapes.create(0.34375, 0.3125, 0.375, 0.46875, 0.4375, 0.5));
    shape = Shapes.or(shape, Shapes.create(0.53125, 0.3125, 0.375, 0.65625, 0.4375, 0.5));
    return shape;
  }

  @Override
  protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
    return CODEC;
  }
}