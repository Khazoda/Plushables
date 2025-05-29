package com.khazoda.plushables.block.plushable;

import com.khazoda.plushables.block.BasePlushable;
import com.khazoda.plushables.block.tooltip.TooltipDataBuilder;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlushableDormouseBlock extends BasePlushable {
  public static final MapCodec<PlushableDormouseBlock> CODEC = simpleCodec(PlushableDormouseBlock::new);

  public PlushableDormouseBlock(Properties settings) {
    super(settings, TooltipDataBuilder.create()
        .number(34)
        .artist("Khazoda")
        .creationDate("5th September 2023")
        .build());
  }

  @Override
  public VoxelShape useShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.or(shape, Shapes.create(0.3125, 0.125, 0.25, 0.6875, 0.4375, 0.8125));
    shape = Shapes.or(shape, Shapes.create(0.375, 0.1875, 0.1875, 0.625, 0.3125, 0.25));
    shape = Shapes.or(shape, Shapes.create(0.25, 0.0625, 0.28125, 0.3125, 0.1875, 0.40625));
    shape = Shapes.or(shape, Shapes.create(0.25, 0.0625, 0.59375, 0.3125, 0.25, 0.78125));
    shape = Shapes.or(shape, Shapes.create(0.6875, 0.0625, 0.28125, 0.75, 0.1875, 0.40625));
    shape = Shapes.or(shape, Shapes.create(0.6875, 0.0625, 0.59375, 0.75, 0.25, 0.78125));
    return shape;
  }

  @Override
  protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
    return CODEC;
  }
}