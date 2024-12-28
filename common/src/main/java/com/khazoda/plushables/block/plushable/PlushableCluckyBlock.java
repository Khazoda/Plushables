package com.khazoda.plushables.block.plushable;

import com.khazoda.plushables.block.BasePlushable;
import com.khazoda.plushables.block.interaction.InteractionEffectBuilder;
import com.khazoda.plushables.registry.SoundRegistry;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlushableCluckyBlock extends BasePlushable {
  public static final MapCodec<PlushableCluckyBlock> CODEC = simpleCodec(PlushableCluckyBlock::new);

  public PlushableCluckyBlock() {
    this(BasePlushable.defaultSettings);
  }

  public PlushableCluckyBlock(Properties settings) {
    super(settings, InteractionEffectBuilder.create()
        .cooldown(75)
        .sound(SoundRegistry.PLUSHABLE_CLUCKY)
        .particle(ParticleTypes.HEART)
        .count(2)
        .build());
  }

  @Override
  public VoxelShape useShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.or(shape, Shapes.create(0.375, 0.0625, 0.3125, 0.625, 0.25, 0.625));
    shape = Shapes.or(shape, Shapes.create(0.25, 0, 0.25, 0.75, 0.0625, 0.6875));
    return shape;
  }

  @Override
  protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
    return CODEC;
  }
}