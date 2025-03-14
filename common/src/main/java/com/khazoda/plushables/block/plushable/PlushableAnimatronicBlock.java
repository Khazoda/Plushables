package com.khazoda.plushables.block.plushable;

import com.khazoda.plushables.block.BasePlushable;
import com.khazoda.plushables.block.tooltip.TooltipDataBuilder;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlushableAnimatronicBlock extends BasePlushable {
  public static final MapCodec<PlushableAnimatronicBlock> CODEC = simpleCodec(PlushableAnimatronicBlock::new);

  public PlushableAnimatronicBlock() {
    this(BasePlushable.defaultSettings);
  }

  public PlushableAnimatronicBlock(Properties settings) {
    super(settings, TooltipDataBuilder.create()
        .number(29)
        .artist("Sammy")
        .creationDate("18th August 2023")
        .trivia("Check out the CC:C Bridge mod and program animatronics to do all sorts of things!")
        .build());
  }

  @Override
  public VoxelShape useShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.or(shape, Shapes.create(0.34375, 0.390625, 0.375, 0.65625, 0.703125, 0.6875));
    shape = Shapes.or(shape, Shapes.create(0.40625, 0, 0.46875, 0.59375, 0.421875, 0.59375));
    shape = Shapes.or(shape, Shapes.create(0.40625, 0, 0.4375, 0.59375, 0.03125, 0.625));
    return shape;
  }

  @Override
  protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
    return CODEC;
  }
}