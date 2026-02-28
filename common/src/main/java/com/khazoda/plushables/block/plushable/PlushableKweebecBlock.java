package com.khazoda.plushables.block.plushable;

import com.khazoda.plushables.block.BasePlushable;
import com.khazoda.plushables.block.interaction.InteractionEffectBuilder;
import com.khazoda.plushables.block.tooltip.TooltipDataBuilder;
import com.khazoda.plushables.registry.SoundRegistry;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlushableKweebecBlock extends BasePlushable {
  public static final MapCodec<PlushableKweebecBlock> CODEC = simpleCodec(PlushableKweebecBlock::new);

  public PlushableKweebecBlock(Properties settings) {
    super(settings,
        TooltipDataBuilder.create()
            .number(48)
            .artist("Khazoda")
            .creationDate("24th December 2025")
            .trivia("Hytale early access released January 13th 2026!")
            .build(),
        InteractionEffectBuilder.create()
            .cooldown(65)
            .sound(SoundRegistry.PLUSHABLE_KWEEBEC)
            .particle(ColorParticleOption.create(
                ParticleTypes.TINTED_LEAVES,
                0.46f, 0.67f, 0.18f
            ))
            .particleCount(8)
            .particleSpread(1)
            .build()
    );
  }

  @Override
  public VoxelShape useShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.join(shape, Shapes.box(0.25, 0.0625, 0.25, 0.75, 0.5625, 0.75), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.3125, 0, 0.3125, 0.4375, 0.0625, 0.625), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.5625, 0, 0.3125, 0.6875, 0.0625, 0.625), BooleanOp.OR);

    return shape;
  }

  @Override
  protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
    return CODEC;
  }
}