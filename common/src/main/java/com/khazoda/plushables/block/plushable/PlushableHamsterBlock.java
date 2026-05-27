package com.khazoda.plushables.block.plushable;

import com.khazoda.plushables.block.BasePlushable;
import com.khazoda.plushables.block.tooltip.TooltipDataBuilder;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlushableHamsterBlock extends BasePlushable {
  public static final MapCodec<PlushableHamsterBlock> CODEC = simpleCodec(PlushableHamsterBlock::new);

  public PlushableHamsterBlock() {
    this(BasePlushable.defaultSettings);
  }

  public PlushableHamsterBlock(Properties settings) {
    super(settings, TooltipDataBuilder.create()
        .number(33)
        .artist("Khazoda")
        .creationDate("5th September 2023")
        .build());
  }

  @Override
  public VoxelShape useShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.or(shape, Shapes.create(0.25, 0.1875, 0.3125, 0.75, 0.625, 0.75));
    shape = Shapes.or(shape, Shapes.create(0.3125, 0.4375, 0.1875, 0.6875, 0.8125, 0.5625));
    shape = Shapes.or(shape, Shapes.create(0.25, 0, 0.25, 0.75, 0.1875, 0.75));
    return shape;
  }

  @Override
  protected MapCodec<PlushableHamsterBlock> codec() {
    return CODEC;
  }
}