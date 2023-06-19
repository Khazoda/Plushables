package com.seacroak.plushables.registry;

import com.seacroak.plushables.util.GenericUtils;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;

public final class SoundRegistry {
	public static final SoundEvent BUILDER_DING = register("builder_ding");
	public static final SoundEvent PLUSHABLE_POP = register("plushable_pop");
	public static final SoundEvent CLUCKY_CLUCK = register("clucky_cluck");
  public static final SoundEvent RUPERT_PURR = register("rupert_purr");
  public static final SoundEvent SWMG = register("swmg");
  public static final SoundEvent LIGHTFURY = register("lightfury");

	public static void init() {
	}

	private static SoundEvent register(String name) {
		return Registry.register(Registries.SOUND_EVENT, name, SoundEvent.of(GenericUtils.ID(name)));
	}

}