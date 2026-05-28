package com.khazoda.plushables.block.plushable;

import com.khazoda.plushables.block.BasePlushable;
import com.khazoda.plushables.block.interaction.InteractionEffectBuilder;
import com.khazoda.plushables.block.tooltip.TooltipDataBuilder;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlushableWispBlock extends BasePlushable {
  public static final MapCodec<PlushableWispBlock> CODEC = simpleCodec(PlushableWispBlock::new);

  public PlushableWispBlock() {
    this(BasePlushable.defaultSettings);
  }

  public PlushableWispBlock(Properties settings) {
    super(settings, TooltipDataBuilder.create()
        .number(41)
        .artist("Khazoda")
        .creationDate("13th April 2024")
        .build(),
        InteractionEffectBuilder.create()
            .lightLevel(8)
            .sound(SoundEvents.ENCHANTMENT_TABLE_USE)
            .particle(ParticleTypes.GLOW)
            .particleCount(4)
            .particleSpread(2)
            .build());
  }

  @Override
  public VoxelShape useShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.or(shape, Shapes.create(0.3125, 0, 0.3125, 0.6875, 0.375, 0.6875));
    shape = Shapes.or(shape, Shapes.create(0.125, 0.1875, 0.75, 0.25, 0.3125, 0.875));
    return shape;
  }

  @Override
  protected MapCodec<PlushableWispBlock> codec() {
    return CODEC;
  }
}