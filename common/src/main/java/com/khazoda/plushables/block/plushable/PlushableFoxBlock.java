package com.khazoda.plushables.block.plushable;

import com.khazoda.plushables.block.BasePlushable;
import com.khazoda.plushables.block.tooltip.TooltipDataBuilder;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlushableFoxBlock extends BasePlushable {
  public static final MapCodec<PlushableFoxBlock> CODEC = simpleCodec(PlushableFoxBlock::new);

  public PlushableFoxBlock() {
    this(BasePlushable.defaultSettings);
  }

  public PlushableFoxBlock(Properties settings) {
    super(settings, TooltipDataBuilder.create()
        .number(3)
        .artist("Khazoda")
        .creationDate("14th June 2022")
        .build());
  }

  @Override
  public VoxelShape useShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.or(shape, Shapes.create(0.34375, 0, 0.3125, 0.65625, 0.3125, 0.625));
    shape = Shapes.or(shape, Shapes.create(0.34375, 0.3125, 0.375, 0.46875, 0.375, 0.4375));
    shape = Shapes.or(shape, Shapes.create(0.53125, 0.3125, 0.375, 0.65625, 0.375, 0.4375));
    shape = Shapes.or(shape, Shapes.create(0.40625, 0.125, 0.25, 0.59375, 0.1875, 0.3125));
    shape = Shapes.or(shape, Shapes.create(0.28125, 0, 0.3125, 0.34375, 0.0625, 0.4375));
    shape = Shapes.or(shape, Shapes.create(0.65625, 0, 0.3125, 0.71875, 0.0625, 0.4375));
    shape = Shapes.or(shape, Shapes.create(0.28125, 0, 0.5625, 0.34375, 0.0625, 0.6875));
    shape = Shapes.or(shape, Shapes.create(0.65625, 0, 0.5625, 0.71875, 0.0625, 0.6875));
    return shape;
  }

  @Override
  protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
    return CODEC;
  }
}