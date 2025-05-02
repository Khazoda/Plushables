package com.khazoda.plushables.block.plushable;

import com.khazoda.plushables.block.BasePlushable;
import com.khazoda.plushables.block.tooltip.TooltipDataBuilder;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlushableBigIrritaterBlock extends BasePlushable {
  public static final MapCodec<PlushableBigIrritaterBlock> CODEC = simpleCodec(PlushableBigIrritaterBlock::new);

  public PlushableBigIrritaterBlock(Properties settings) {
    super(settings, TooltipDataBuilder.create()
        .number(19)
        .artist("Khazoda")
        .creationDate("31st July 2023")
        .build());
  }

  @Override
  public VoxelShape useShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.or(shape, Shapes.create(0.125, 0.1875, 0.1875, 0.875, 0.25, 0.8125));
    shape = Shapes.or(shape, Shapes.create(0.125, 0.25, 0.125, 0.875, 1, 0.875));
    return shape;
  }

  @Override
  protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
    return CODEC;
  }
}