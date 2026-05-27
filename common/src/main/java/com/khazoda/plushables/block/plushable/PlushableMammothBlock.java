package com.khazoda.plushables.block.plushable;

import com.khazoda.plushables.block.BasePlushable;
import com.khazoda.plushables.block.tooltip.TooltipDataBuilder;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlushableMammothBlock extends BasePlushable {
  public static final MapCodec<PlushableMammothBlock> CODEC = simpleCodec(PlushableMammothBlock::new);

  public PlushableMammothBlock() {
    this(BasePlushable.defaultSettings);
  }

  public PlushableMammothBlock(Properties settings) {
    super(settings, TooltipDataBuilder.create()
        .number(37)
        .artist("Khazoda")
        .creationDate("9th September 2023")
        .build());
  }

  @Override
  public VoxelShape useShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.or(shape, Shapes.create(0.3125, 0.125, 0.25, 0.6875, 0.5625, 0.5625));
    shape = Shapes.or(shape, Shapes.create(0.3125, 0.125, 0.5625, 0.6875, 0.5, 0.75));
    shape = Shapes.or(shape, Shapes.create(0.59375, 0, 0.25, 0.71875, 0.25, 0.375));
    shape = Shapes.or(shape, Shapes.create(0.59375, 0, 0.625, 0.71875, 0.25, 0.75));
    shape = Shapes.or(shape, Shapes.create(0.28125, 0, 0.25, 0.40625, 0.25, 0.375));
    shape = Shapes.or(shape, Shapes.create(0.28125, 0, 0.625, 0.40625, 0.25, 0.75));
    return shape;
  }

  @Override
  protected MapCodec<PlushableMammothBlock> codec() {
    return CODEC;
  }
}