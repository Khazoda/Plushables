package com.khazoda.plushables.block.interaction;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import org.jetbrains.annotations.Nullable;

public record InteractionEffectData(SoundEvent soundEvent, float soundVolume, float soundPitch,
                                    @Nullable ParticleOptions particleEffect, int particleCount, double particleSpread,
                                    int cooldownPeriod) {
  public static final InteractionEffectData DEFAULT =
      new InteractionEffectData(SoundEvents.WOOL_HIT,
          2.0F,
          1.0F,
          null,
          0,
          0.5,
          10);
}