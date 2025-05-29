package com.khazoda.plushables.block.plushable;

import com.khazoda.plushables.block.BasePlushable;
import com.khazoda.plushables.block.tooltip.TooltipDataBuilder;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlushableTriceratopsBlock extends BasePlushable {
  public static final MapCodec<PlushableTriceratopsBlock> CODEC = simpleCodec(PlushableTriceratopsBlock::new);

  public PlushableTriceratopsBlock(Properties settings) {
    super(settings, TooltipDataBuilder.create()
        .number(9)
        .artist("@BumbleSculpts")
        .creationDate("12th June 2023")
        .build());
  }

  @Override
  public VoxelShape useShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.or(shape, Shapes.create(0.25, 0, 0.25, 0.75, 0.875, 0.625));
    shape = Shapes.or(shape, Shapes.create(0.0625, 0, 0.09375, 0.25, 0.1875, 0.40625));
    shape = Shapes.or(shape, Shapes.create(0.75, 0, 0.0625, 0.9375, 0.1875, 0.375));

    return shape;
  }

  @Override
  protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
    return CODEC;
  }
}