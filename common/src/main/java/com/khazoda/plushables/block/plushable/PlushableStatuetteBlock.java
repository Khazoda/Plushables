package com.khazoda.plushables.block.plushable;

import com.khazoda.plushables.block.BasePlushable;
import com.khazoda.plushables.block.interaction.InteractionEffectBuilder;
import com.khazoda.plushables.registry.SoundRegistry;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlushableStatuetteBlock extends BasePlushable {
  public static final MapCodec<PlushableStatuetteBlock> CODEC = simpleCodec(PlushableStatuetteBlock::new);

  public PlushableStatuetteBlock() {
    this(BasePlushable.defaultSettings);
  }

  public PlushableStatuetteBlock(Properties settings) {
    super(settings, InteractionEffectBuilder.create()
        .cooldown(65)
        .sound(SoundRegistry.PLUSHABLE_STATUETTE)
        .particle(ParticleTypes.HEART)
        .count(5)
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
  protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
    return CODEC;
  }
}