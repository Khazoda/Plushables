package com.khazoda.plushables.block.plushable;

import com.khazoda.plushables.block.BasePlushable;
import com.khazoda.plushables.block.tooltip.TooltipDataBuilder;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlushableSnailBlock extends BasePlushable {
  public static final MapCodec<PlushableSnailBlock> CODEC = simpleCodec(PlushableSnailBlock::new);

  public PlushableSnailBlock() {
    this(BasePlushable.defaultSettings);
  }

  public PlushableSnailBlock(Properties settings) {
    super(settings, TooltipDataBuilder.create()
        .number(23)
        .artist("Khazoda")
        .creationDate("2nd August 2023")
        .build());
  }

  @Override
  public VoxelShape useShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.or(shape, Shapes.create(0.3125, 0, 0.0625, 0.6875, 0.125, 0.9375));
    shape = Shapes.or(shape, Shapes.create(0.5625, 0.125, 0.125, 0.625, 0.375, 0.25));
    shape = Shapes.or(shape, Shapes.create(0.3125, 0.125, 0.3125, 0.6875, 0.6875, 0.875));
    shape = Shapes.or(shape, Shapes.create(0.375, 0.125, 0.125, 0.4375, 0.375, 0.25));
    shape = Shapes.or(shape, Shapes.create(0.5625, 0.375, 0, 0.625, 0.5, 0.25));
    shape = Shapes.or(shape, Shapes.create(0.375, 0.375, 0, 0.4375, 0.5, 0.25));
    return shape;
  }

  @Override
  protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
    return CODEC;
  }
}