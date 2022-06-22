package com.seacroak.plushables.registry;

import com.seacroak.plushables.PlushablesMod;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SoundRegistry {
	public static SoundEvent BUILDER_DING = Registry.register(Registry.SOUND_EVENT, "builder_ding",
			new SoundEvent(new Identifier(PlushablesMod.ModID, "builder_ding")));
	public static SoundEvent PLUSHABLE_POP = Registry.register(Registry.SOUND_EVENT, "plushable_pop",
			new SoundEvent(new Identifier(PlushablesMod.ModID, "plushable_pop")));
}