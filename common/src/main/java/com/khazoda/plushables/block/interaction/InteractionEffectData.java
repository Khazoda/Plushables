package com.khazoda.plushables.block.interaction;

import com.khazoda.plushables.registry.SoundRegistry;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.sounds.SoundEvent;
import org.jetbrains.annotations.Nullable;

public record InteractionEffectData(SoundEvent soundEvent, float soundVolume, float soundPitch,
                                    @Nullable ParticleOptions particleEffect, int particleCount, double particleSpread,
                                    float particleYOffset,
                                    int cooldownPeriod, int lightLevel) {
  public static final InteractionEffectData DEFAULT =
          new InteractionEffectData(SoundRegistry.PLUSHABLE_GENERIC.get(),
                  2.0F,
                  1.0F,
                  null,
                  0,
                  0.5,
                  0,
                  10,
                  0);
}