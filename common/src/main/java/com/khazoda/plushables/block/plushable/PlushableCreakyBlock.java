package com.khazoda.plushables.block.plushable;

import com.khazoda.plushables.block.BasePlushable;
import com.khazoda.plushables.block.tooltip.TooltipDataBuilder;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlushableCreakyBlock extends BasePlushable {
  public static final MapCodec<PlushableCreakyBlock> CODEC = simpleCodec(PlushableCreakyBlock::new);

  public PlushableCreakyBlock(Properties settings) {
    super(settings, TooltipDataBuilder.create()
        .number(47)
        .artist("Omasumi")
        .creationDate("14th March 2025")
        .build());
  }

  @Override
  public VoxelShape useShape() {
    VoxelShape shape = Shapes.empty();

    shape = Shapes.or(shape, Shapes.box(0.4375, 0, 0.5, 0.59375, 0.25, 0.5625));
    shape = Shapes.or(shape, Shapes.box(0.546875, 0, 0.25, 0.609375, 0.0625, 0.5));
    shape = Shapes.or(shape, Shapes.box(0.453125, 0, 0.25, 0.515625, 0.0625, 0.5));

    return shape;
  }

  @Override
  protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
    return CODEC;
  }
}