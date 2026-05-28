package com.khazoda.plushables.block.plushable;

import com.khazoda.plushables.block.BasePlushable;
import com.khazoda.plushables.block.interaction.InteractionEffectBuilder;
import com.khazoda.plushables.block.tooltip.TooltipDataBuilder;
import com.mojang.serialization.MapCodec;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlushableRattiamBlock extends BasePlushable {
  public static final MapCodec<PlushableRattiamBlock> CODEC = simpleCodec(PlushableRattiamBlock::new);

  public PlushableRattiamBlock() {
    this(BasePlushable.defaultSettings);
  }

  public PlushableRattiamBlock(Properties settings) {
    super(settings, TooltipDataBuilder.create()
        .number(8)
        .artist("Luke")
        .creationDate("12th June 2023")
        .build(),
        InteractionEffectBuilder.create()
            .sound(SoundEvents.FOX_SNIFF)
            .pitch(1.1f)
            .build());
  }

  @Override
  public VoxelShape useShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.or(shape, Shapes.create(0.359375, 0.59375, 0.0625, 0.640625, 0.78125, 0.25));
    shape = Shapes.or(shape, Shapes.create(0.40625, 0.59375, -0.0625, 0.59375, 0.71875, 0.0625));
    shape = Shapes.or(shape, Shapes.create(0.46875, 0.65625, -0.125, 0.53125, 0.71875, -0.0625));
    shape = Shapes.or(shape, Shapes.create(0.3125, 0, 0.25, 0.6875, 0.78125, 0.5625));

    return shape;
  }

  @Override
  protected MapCodec<PlushableRattiamBlock> codec() {
    return CODEC;
  }
}