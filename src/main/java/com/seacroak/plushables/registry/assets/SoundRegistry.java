package com.seacroak.plushables.registry.assets;

import com.seacroak.plushables.util.GenericUtils;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;

public final class SoundRegistry {
	public static final SoundEvent PLUSHABLE_POP = register("plushable_pop");
	public static final SoundEvent BUILDER_DING = register("builder_ding");
	public static final SoundEvent CLUCKY_CLUCK = register("clucky_cluck");
  public static final SoundEvent RUPERT_PURR = register("rupert_purr");
  public static final SoundEvent SWMG = register("swmg");
  public static final SoundEvent LIGHTFURY = register("lightfury");
	public static final SoundEvent ORANGUTAN = register("orangutan");
	public static final SoundEvent GOLDFISH = register("goldfish");
	public static final SoundEvent BASKET_IN = register("basket_in");
	public static final SoundEvent BASKET_OUT = register("basket_out");
	public static final SoundEvent BASKET_ATTACK = register("basket_attack");
	public static final SoundEvent STATUETTE = register("statuette");
	public static final SoundEvent OWL = register("owl");

	public static final SoundEvent PATCHOULI_OPEN_SOUND = register("opencodex");
	public static final SoundEvent PATCHOULI_FLIP_SOUND = register("flipcodex");

	public static void init() {
	}

	private static SoundEvent register(String name) {
		return Registry.register(Registries.SOUND_EVENT, name, SoundEvent.of(GenericUtils.ID(name)));
	}

}