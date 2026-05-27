package com.khazoda.plushables.block.plushable;

import com.khazoda.plushables.block.BasePlushable;
import com.khazoda.plushables.block.interaction.InteractionEffectBuilder;
import com.khazoda.plushables.block.tooltip.TooltipDataBuilder;
import com.khazoda.plushables.registry.SoundRegistry;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlushableCluckyBlock extends BasePlushable {
  public static final MapCodec<PlushableCluckyBlock> CODEC = simpleCodec(PlushableCluckyBlock::new);

  public PlushableCluckyBlock() {
    this(BasePlushable.defaultSettings);
  }

  public PlushableCluckyBlock(Properties settings) {
    super(settings, TooltipDataBuilder.create()
                    .number(4)
                    .artist("@BumbleSculpts")
                    .creationDate("22nd June 2022")
                    .build(),
            InteractionEffectBuilder.create()
                    .cooldown(65)
                    .sound(SoundRegistry.PLUSHABLE_CLUCKY)
                    .particle(ParticleTypes.EGG_CRACK)
                    .particleCount(5)
                    .particleSpread(1.25)
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
  protected MapCodec<PlushableCluckyBlock> codec() {
    return CODEC;
  }
}