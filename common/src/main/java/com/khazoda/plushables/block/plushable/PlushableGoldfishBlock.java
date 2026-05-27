package com.khazoda.plushables.block.plushable;

import com.khazoda.plushables.block.BasePlushable;
import com.khazoda.plushables.block.interaction.InteractionEffectBuilder;
import com.khazoda.plushables.block.tooltip.TooltipDataBuilder;
import com.khazoda.plushables.registry.SoundRegistry;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlushableGoldfishBlock extends BasePlushable {
  public static final MapCodec<PlushableGoldfishBlock> CODEC = simpleCodec(PlushableGoldfishBlock::new);

  public PlushableGoldfishBlock() {
    this(BasePlushable.defaultSettings);
  }

  public PlushableGoldfishBlock(Properties settings) {
    super(settings,
        TooltipDataBuilder.create()
            .number(26)
            .artist("Khazoda")
            .creationDate("8th August 2023")
            .build(),
        InteractionEffectBuilder.create()
            .cooldown(65)
            .sound(SoundRegistry.PLUSHABLE_GOLDFISH)
            .particle(ParticleTypes.FISHING)
            .particleCount(5)
            .particleSpread(1.25)
            .build()
    );
  }

  @Override
  public VoxelShape useShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.or(shape, Shapes.create(0.1875, 0, 0.125, 0.8125, 0.625, 0.75));
    shape = Shapes.or(shape, Shapes.create(0.0625, 0.3125, 0.1875, 0.1875, 0.5, 0.375));
    shape = Shapes.or(shape, Shapes.create(0.8125, 0.3125, 0.1875, 0.9375, 0.5, 0.375));
    return shape;
  }

  @Override
  protected MapCodec<PlushableGoldfishBlock> codec() {
    return CODEC;
  }
}