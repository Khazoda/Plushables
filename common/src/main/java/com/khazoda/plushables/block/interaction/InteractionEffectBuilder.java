package com.khazoda.plushables.block.interaction;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.sounds.SoundEvent;

import java.util.function.Supplier;

public class InteractionEffectBuilder {
  private SoundEvent soundEvent = InteractionEffectData.DEFAULT.soundEvent();
  private float soundVolume = InteractionEffectData.DEFAULT.soundVolume();
  private float soundPitch = InteractionEffectData.DEFAULT.soundPitch();
  private ParticleOptions particleEffect = InteractionEffectData.DEFAULT.particleEffect();
  private int particleCount = InteractionEffectData.DEFAULT.particleCount();
  private double particleSpread = InteractionEffectData.DEFAULT.particleSpread();
  private float particleYOffset = InteractionEffectData.DEFAULT.particleYOffset();
  private int cooldownPeriod = InteractionEffectData.DEFAULT.cooldownPeriod();
  private int lightLevel = InteractionEffectData.DEFAULT.lightLevel();

  public static InteractionEffectBuilder create() {
    return new InteractionEffectBuilder();
  }

  public InteractionEffectBuilder sound(Supplier<SoundEvent> event) {
    soundEvent = event.get();
    return this;
  }

  public InteractionEffectBuilder volume(float vol) {
    soundVolume = vol;
    return this;
  }

  public InteractionEffectBuilder pitch(float pitch) {
    soundPitch = pitch;
    return this;
  }

  public InteractionEffectBuilder particle(ParticleOptions effect) {
    particleEffect = effect;
    return this;
  }

  public InteractionEffectBuilder particleCount(int count) {
    particleCount = count;
    return this;
  }

  public InteractionEffectBuilder particleSpread(double spread) {
    particleSpread = spread;
    return this;
  }

  public InteractionEffectBuilder particleYOffset(float Yoffset) {
    particleYOffset = Yoffset;
    return this;
  }

  public InteractionEffectBuilder cooldown(int ticks) {
    cooldownPeriod = ticks;
    return this;
  }

  public InteractionEffectBuilder lightLevel(int level) {
    lightLevel = level;
    return this;
  }

  public InteractionEffectData build() {
    return new InteractionEffectData(soundEvent, soundVolume, soundPitch, particleEffect, particleCount, particleSpread, particleYOffset, cooldownPeriod, lightLevel);
  }
}