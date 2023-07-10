package com.seacroak.plushables.registry;

import com.seacroak.plushables.PlushablesMod;
import com.seacroak.plushables.util.GenericUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SoundRegistry {
  public static final DeferredRegister<SoundEvent> SOUNDS =
      DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, PlushablesMod.MOD_ID);

  public static final RegistryObject<SoundEvent> BUILDER_DING = registerSound("builder_ding");
  public static final RegistryObject<SoundEvent> PLUSHABLE_POP = registerSound("plushable_pop");
  public static final RegistryObject<SoundEvent> CLUCKY_CLUCK = registerSound("clucky_cluck");
  public static final RegistryObject<SoundEvent> RUPERT_PURR = registerSound("rupert_purr");
  public static final RegistryObject<SoundEvent> SWMG = registerSound("swmg");
  public static final RegistryObject<SoundEvent> LIGHTFURY = registerSound("lightfury");

  private static RegistryObject<SoundEvent> registerSound(String name) {
    return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(GenericUtils.ID(name)));
  }

  public static void register(IEventBus eventBus) {
    SOUNDS.register(eventBus);
  }


}
