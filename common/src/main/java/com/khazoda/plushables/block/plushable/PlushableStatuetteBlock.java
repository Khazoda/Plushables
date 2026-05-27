package com.khazoda.plushables.block.plushable;

import com.khazoda.plushables.block.BasePlushable;
import com.khazoda.plushables.block.interaction.InteractionEffectBuilder;
import com.khazoda.plushables.block.tooltip.TooltipDataBuilder;
import com.khazoda.plushables.registry.SoundRegistry;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlushableStatuetteBlock extends BasePlushable {
  public static final MapCodec<PlushableStatuetteBlock> CODEC = simpleCodec(PlushableStatuetteBlock::new);

  public PlushableStatuetteBlock() {
    this(BasePlushable.defaultSettings);
  }

  public PlushableStatuetteBlock(Properties settings) {
    super(settings,
        TooltipDataBuilder.create()
            .number(36)
            .artist("Khazoda")
            .creationDate("9th September 2023")
            .trivia("The Terracotta Knights mod lets you command armies of these warriors")
            .build(),
        InteractionEffectBuilder.create()
            .cooldown(65)
            .sound(SoundRegistry.PLUSHABLE_STATUETTE)
            .particle(ParticleTypes.HEART)
            .particleCount(5)
            .build());
  }

  @Override
  public VoxelShape useShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.or(shape, Shapes.create(0.5, 0, 0.40625, 0.59375, 0.28125, 0.5));
    shape = Shapes.or(shape, Shapes.create(0.40625, 0.28125, 0.3828, 0.59375, 0.5625, 0.5234));
    shape = Shapes.or(shape, Shapes.create(0.59375, 0.28125, 0.40625, 0.6875, 0.5625, 0.5));
    shape = Shapes.or(shape, Shapes.create(0.3125, 0.28125, 0.40625, 0.40625, 0.5625, 0.5));
    shape = Shapes.or(shape, Shapes.create(0.40625, 0, 0.40625, 0.5, 0.28125, 0.5));
    shape = Shapes.or(shape, Shapes.create(0.40625, 0.5625, 0.3828, 0.59375, 0.75, 0.5234));
    return shape;
  }

  @Override
  protected MapCodec<PlushableStatuetteBlock> codec() {
    return CODEC;
  }
}