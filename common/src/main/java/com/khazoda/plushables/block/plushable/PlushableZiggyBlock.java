package com.khazoda.plushables.block.plushable;

import com.khazoda.plushables.block.BasePlushable;
import com.khazoda.plushables.block.interaction.InteractionEffectBuilder;
import com.khazoda.plushables.block.tooltip.TooltipDataBuilder;
import com.mojang.serialization.MapCodec;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlushableZiggyBlock extends BasePlushable {
  public static final MapCodec<PlushableZiggyBlock> CODEC = simpleCodec(PlushableZiggyBlock::new);

  public PlushableZiggyBlock(Properties settings) {
    super(settings, TooltipDataBuilder.create()
                    .number(43)
                    .artist("Khazoda")
                    .creationDate("17th April 2024")
                    .build(),
            InteractionEffectBuilder.create()
                    .sound(SoundEvents.CAT_PURREOW)
                    .build());
  }

  @Override
  public VoxelShape useShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.or(shape, Shapes.create(0.125, 0, 0.5, 0.75, 0.25, 0.8125));
    shape = Shapes.or(shape, Shapes.create(0.5, 0.0625, 0.8125, 0.5625, 0.125, 0.875));
    shape = Shapes.or(shape, Shapes.create(0.5, 0, 0.8125, 0.6875, 0.0625, 0.875));
    shape = Shapes.or(shape, Shapes.create(0.125, 0, 0.4375, 0.5, 0.0625, 0.5));
    shape = Shapes.or(shape, Shapes.create(0.3125, 0.0625, 0.4375, 0.375, 0.125, 0.5));
    shape = Shapes.or(shape, Shapes.create(0.1875, 0, 0.8125, 0.375, 0.0625, 0.875));
    return shape;
  }

  @Override
  protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
    return CODEC;
  }
}