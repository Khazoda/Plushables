package com.khazoda.plushables.block.plushable;

import com.khazoda.plushables.block.BasePlushable;
import com.khazoda.plushables.block.tooltip.TooltipDataBuilder;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlushableConductorBlock extends BasePlushable {
  public static final MapCodec<PlushableConductorBlock> CODEC = simpleCodec(PlushableConductorBlock::new);

  public PlushableConductorBlock() {
    this(BasePlushable.defaultSettings);
  }

  public PlushableConductorBlock(Properties settings) {
    super(settings, TooltipDataBuilder.create()
        .number(28)
        .artist("Steam n' Rails Team")
        .creationDate("18th August 2023")
        .trivia("You can drive trains alongside this little guy in the Create: Steam n' Rails mod")
        .build());
  }

  @Override
  public VoxelShape useShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.or(shape, Shapes.create(0.25, 0.1875, 0.3125, 0.75, 0.5, 0.6875));
    shape = Shapes.or(shape, Shapes.create(0.75, 0.015625, 0.375, 0.9375, 0.578125, 0.625));
    shape = Shapes.or(shape, Shapes.create(0.0625, 0.015625, 0.375, 0.25, 0.578125, 0.625));
    shape = Shapes.or(shape, Shapes.create(0.3125, 0, 0.375, 0.6875, 0.25, 0.625));
    shape = Shapes.or(shape, Shapes.create(0.25, 0.5, 0.25, 0.75, 1, 0.75));
    shape = Shapes.or(shape, Shapes.create(0.1875, 0, 0.125, 0.375, 0.25, 0.4375));
    shape = Shapes.or(shape, Shapes.create(0.625, 0, 0.125, 0.8125, 0.25, 0.4375));
    return shape;
  }

  @Override
  protected MapCodec<PlushableConductorBlock> codec() {
    return CODEC;
  }
}