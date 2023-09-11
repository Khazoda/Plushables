package com.seacroak.plushables.networking;

import com.seacroak.plushables.registry.assets.SoundRegistry;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class PacketDecoder {
  public static SoundEvent decodeSoundEvent(String stringIdentifier) {
    SoundEvent returnValue;
    switch(stringIdentifier) {
      /* Custom Sounds */
      case "plushables:builder_ding": returnValue = SoundRegistry.BUILDER_DING; break;
      case "plushables:clucky_cluck": returnValue = SoundRegistry.CLUCKY_CLUCK; break;
      case "plushables:rupert_purr": returnValue = SoundRegistry.RUPERT_PURR; break;
      case "plushables:swmg": returnValue = SoundRegistry.SWMG; break;
      case "plushables:lightfury": returnValue = SoundRegistry.LIGHTFURY; break;
      case "plushables:orangutan": returnValue = SoundRegistry.ORANGUTAN; break;
      case "plushables:goldfish": returnValue = SoundRegistry.GOLDFISH; break;
      case "minecraft:entity.frog.ambient": returnValue = SoundEvents.ENTITY_FROG_AMBIENT; break;
      case "plushables:statuette": returnValue = SoundRegistry.STATUETTE; break;
      case "plushables:owl": returnValue = SoundRegistry.OWL; break;

      case "plushables:basket_in": returnValue = SoundRegistry.BASKET_IN; break;
      case "plushables:basket_out": returnValue = SoundRegistry.BASKET_OUT; break;
      case "plushables:basket_attack": returnValue = SoundRegistry.BASKET_ATTACK; break;


      /* MC Sounds */
      case "minecraft:block.moss.place": returnValue = SoundEvents.BLOCK_MOSS_PLACE; break;
      case "minecraft:block.wool.hit": returnValue = SoundEvents.BLOCK_WOOL_HIT; break;

      /* Default */
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
      case "minecraft:poof": returnValue = ParticleTypes.POOF; break;
      case "minecraft:dolphin": returnValue = ParticleTypes.DOLPHIN; break;
      case "minecraft:fishing": returnValue = ParticleTypes.FISHING; break;
      case "minecraft:mycelium": returnValue = ParticleTypes.MYCELIUM; break;
      case "minecraft:wax_on": returnValue = ParticleTypes.WAX_ON; break;

      default: returnValue = ParticleTypes.SCULK_SOUL;
    }

    return returnValue;
  }
}
