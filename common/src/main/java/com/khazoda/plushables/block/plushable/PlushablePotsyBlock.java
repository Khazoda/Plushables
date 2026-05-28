package com.khazoda.plushables.block.plushable;

import com.khazoda.plushables.block.BasePlushable;
import com.khazoda.plushables.block.interaction.InteractionEffectBuilder;
import com.khazoda.plushables.block.tooltip.TooltipDataBuilder;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlushablePotsyBlock extends BasePlushable {
  public static final MapCodec<PlushablePotsyBlock> CODEC = simpleCodec(PlushablePotsyBlock::new);

  public PlushablePotsyBlock() {
    this(BasePlushable.defaultSettings);
  }

  public PlushablePotsyBlock(Properties settings) {
    super(settings, TooltipDataBuilder.create()
        .number(44)
        .artist("Khazoda")
        .creationDate("19th April 2024")
        .build(),
        InteractionEffectBuilder.create()
            .sound(SoundEvents.POINTED_DRIPSTONE_DRIP_WATER_INTO_CAULDRON)
            .particle(ParticleTypes.BUBBLE_POP)
            .particleYOffset(-0.15f)
            .particleSpread(0.5)
            .particleCount(8)
            .build());
  }

  @Override
  public VoxelShape useShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.or(shape, Shapes.create(0.125, 0, 0.125, 0.875, 0.6875, 0.875));
    shape = Shapes.or(shape, Shapes.create(0.1875, 0.6875, 0.1875, 0.8125, 0.75, 0.8125));

    return shape;
  }

  @Override
  protected MapCodec<PlushablePotsyBlock> codec() {
    return CODEC;
  }
}