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

public class PlushableDragonBlock extends BasePlushable {
  public static final MapCodec<PlushableDragonBlock> CODEC = simpleCodec(PlushableDragonBlock::new);

  public PlushableDragonBlock() {
    this(BasePlushable.defaultSettings);
  }

  public PlushableDragonBlock(Properties settings) {
    super(settings, TooltipDataBuilder.create()
                    .number(14)
                    .artist("Luke")
                    .creationDate("17th June 2023")
                    .build(),
            InteractionEffectBuilder.create()
                    .cooldown(40)
                    .sound(SoundRegistry.PLUSHABLE_DRAGON)
                    .particle(ParticleTypes.SOUL_FIRE_FLAME)
                    .particleCount(5)
                    .particleSpread(1.25)
                    .build());
  }

  @Override
  public VoxelShape useShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.or(shape, Shapes.create(0.3125, 0, 0.1875, 0.40625, 0.125, 0.3125));
    shape = Shapes.or(shape, Shapes.create(0.5, 0, 0.1875, 0.59375, 0.125, 0.3125));
    shape = Shapes.or(shape, Shapes.create(0.3125, 0, 0.3125, 0.59375, 0.4375, 0.5625));
    shape = Shapes.or(shape, Shapes.create(0.3125, 0.3125, 0.171875, 0.59375, 0.5, 0.421875));
    return shape;
  }

  @Override
  protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
    return CODEC;
  }
}