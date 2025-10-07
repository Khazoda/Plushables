package com.khazoda.plushables.block.plushable;

import com.khazoda.plushables.block.BasePlushable;
import com.khazoda.plushables.block.interaction.InteractionEffectBuilder;
import com.khazoda.plushables.block.tooltip.TooltipDataBuilder;
import com.mojang.serialization.MapCodec;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlushableUnicornBlock extends BasePlushable {
  public static final MapCodec<PlushableUnicornBlock> CODEC = simpleCodec(PlushableUnicornBlock::new);

  public PlushableUnicornBlock(Properties settings) {
    super(settings, TooltipDataBuilder.create()
                    .number(10)
                    .artist("Luke")
                    .creationDate("12th June 2023")
                    .build(),
            InteractionEffectBuilder.create()
                    .sound(SoundEvents.HORSE_BREATHE)
                    .build());
  }

  @Override
  public VoxelShape useShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.or(shape, Shapes.create(0.359375, 0, 0.3125, 0.609375, 0.3125, 0.625));
    shape = Shapes.or(shape, Shapes.create(0.359375, 0.3125, 0.1875, 0.609375, 0.5625, 0.5));

    return shape;
  }

  @Override
  protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
    return CODEC;
  }
}