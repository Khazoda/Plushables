package com.seacroak.plushables.registry;

import com.seacroak.plushables.util.GenericUtils;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.registry.Registry;

public final class SoundRegistry {
	public static final SoundEvent BUILDER_DING = register("builder_ding");
	public static final SoundEvent PLUSHABLE_POP = register("plushable_pop");

	public static void init() {
	}

	private static SoundEvent register(String name) {
		return Registry.register(Registry.SOUND_EVENT, name, new SoundEvent(GenericUtils.ID(name)));
	}

}