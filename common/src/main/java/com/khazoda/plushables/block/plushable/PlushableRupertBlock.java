package com.khazoda.plushables.block.plushable;

import com.khazoda.plushables.block.BasePlushable;
import com.khazoda.plushables.block.interaction.InteractionEffectBuilder;
import com.khazoda.plushables.block.tooltip.TooltipDataBuilder;
import com.khazoda.plushables.registry.SoundRegistry;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlushableRupertBlock extends BasePlushable {
  public static final MapCodec<PlushableRupertBlock> CODEC = simpleCodec(PlushableRupertBlock::new);

  public PlushableRupertBlock() {
    this(BasePlushable.defaultSettings);
  }

  public PlushableRupertBlock(Properties settings) {
    super(settings,
        TooltipDataBuilder.create()
            .number(13)
            .artist("Luke")
            .creationDate("14th June 2023")
            .build(),
        InteractionEffectBuilder.create()
            .cooldown(50)
            .sound(SoundRegistry.PLUSHABLE_RUPERT)
            .particle(ParticleTypes.HEART)
            .particleCount(1)
            .build());
  }

  @Override
  public VoxelShape useShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.or(shape, Shapes.create(0.3438, 0.1094, 0.25, 0.5938, 0.3594, 0.75));
    shape = Shapes.or(shape, Shapes.create(0.3281, 0.2656, 0.1094, 0.6094, 0.5156, 0.3906));
    shape = Shapes.or(shape, Shapes.create(0.5, 0.01563, 0.6406, 0.625, 0.2656, 0.7656));
    shape = Shapes.or(shape, Shapes.create(0.5, 0, 0.2344, 0.625, 0.25, 0.3594));
    shape = Shapes.or(shape, Shapes.create(0.3125, 0, 0.2344, 0.4375, 0.25, 0.3594));
    shape = Shapes.or(shape, Shapes.create(0.3125, 0.01563, 0.6406, 0.4375, 0.2656, 0.7656));
    shape = Shapes.or(shape, Shapes.create(0.375, 0.2656, 0.07813, 0.5625, 0.3906, 0.1406));
    shape = Shapes.or(shape, Shapes.create(0.3438, 0.5156, 0.1719, 0.4063, 0.5781, 0.2969));
    shape = Shapes.or(shape, Shapes.create(0.5313, 0.5156, 0.1719, 0.5938, 0.5781, 0.2969));
    return shape;
  }

  @Override
  protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
    return CODEC;
  }
}