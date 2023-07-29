package com.seacroak.plushables.util.networking;

import com.seacroak.plushables.registry.SoundRegistry;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvent;

public class PacketDecoder {
  public static SoundEvent decodeSoundEvent(String stringIdentifier) {
    SoundEvent returnValue;
    switch(stringIdentifier) {
      case "plushables:builder_ding": returnValue = SoundRegistry.BUILDER_DING; break;
      case "plushables:clucky_cluck": returnValue = SoundRegistry.CLUCKY_CLUCK; break;
      case "plushables:rupert_purr": returnValue = SoundRegistry.RUPERT_PURR; break;
      case "plushables:swmg": returnValue = SoundRegistry.SWMG; break;
      case "plushables:lightfury": returnValue = SoundRegistry.LIGHTFURY; break;

      case "plushables:plushable_pop":
      default: returnValue = SoundRegistry.PLUSHABLE_POP;
    }
    return returnValue;
  }

  public static ParticleEffect decodeParticle(String stringIdentifier) {
    ParticleEffect returnValue;
    switch(stringIdentifier) {
      case "minecraft:glow": returnValue = ParticleTypes.GLOW; break;
      case "minecraft:note": returnValue = ParticleTypes.NOTE; break;

      default: returnValue = ParticleTypes.SCULK_SOUL;
    }

    return returnValue;
  }
}
