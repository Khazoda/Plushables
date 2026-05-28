package com.khazoda.plushables.block.plushable;

import com.khazoda.plushables.block.BasePlushable;
import com.khazoda.plushables.block.interaction.InteractionEffectBuilder;
import com.khazoda.plushables.block.tooltip.TooltipDataBuilder;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlushableWhelplingBlock extends BasePlushable {
  public static final MapCodec<PlushableWhelplingBlock> CODEC = simpleCodec(PlushableWhelplingBlock::new);

  public PlushableWhelplingBlock() {
    this(BasePlushable.defaultSettings);
  }

  public PlushableWhelplingBlock(Properties settings) {
    super(settings, TooltipDataBuilder.create()
        .number(11)
        .artist("Luke")
        .creationDate("13th June 2023")
        .build(),
        InteractionEffectBuilder.create()
            .sound(SoundEvents.FIRECHARGE_USE)
            .pitch(1.3f)
            .particle(ParticleTypes.SMALL_FLAME)
            .particleCount(3)
            .particleSpread(1.2)
            .build());
  }

  @Override
  public VoxelShape useShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.or(shape, Shapes.create(0.3375, 0, 0.271875, 0.6703125, 0.1890625, 0.690625));

    return shape;
  }

  @Override
  protected MapCodec<PlushableWhelplingBlock> codec() {
    return CODEC;
  }
}