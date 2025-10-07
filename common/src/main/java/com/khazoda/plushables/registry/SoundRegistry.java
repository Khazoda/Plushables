package com.khazoda.plushables.registry;

import com.khazoda.plushables.PlushablesCommon;
import com.khazoda.plushables.registry.helper.Reggie;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;

import java.util.function.Supplier;

import static com.khazoda.plushables.Constants.ID;

public class SoundRegistry {
  private static final Reggie<SoundEvent> SOUND_REGISTRAR = PlushablesCommon.REGISTRARS.get(Registries.SOUND_EVENT);

  public static final Supplier<SoundEvent> PLUSHABLE_GENERIC = SOUND_REGISTRAR.register("plushable_generic", () -> SoundEvent.createVariableRangeEvent(ID("plushable_generic")));

  public static final Supplier<SoundEvent> PLUSHABLE_CLUCKY = SOUND_REGISTRAR.register("plushable_clucky", () -> SoundEvent.createVariableRangeEvent(ID("plushable_clucky")));
  public static final Supplier<SoundEvent> PLUSHABLE_DRAGON = SOUND_REGISTRAR.register("plushable_dragon", () -> SoundEvent.createVariableRangeEvent(ID("plushable_dragon")));
  public static final Supplier<SoundEvent> PLUSHABLE_GOLDFISH = SOUND_REGISTRAR.register("plushable_goldfish", () -> SoundEvent.createVariableRangeEvent(ID("plushable_goldfish")));
  public static final Supplier<SoundEvent> PLUSHABLE_OWL = SOUND_REGISTRAR.register("plushable_owl", () -> SoundEvent.createVariableRangeEvent(ID("plushable_owl")));
  public static final Supplier<SoundEvent> PLUSHABLE_RUPERT = SOUND_REGISTRAR.register("plushable_rupert", () -> SoundEvent.createVariableRangeEvent(ID("plushable_rupert")));
  public static final Supplier<SoundEvent> PLUSHABLE_STATUETTE = SOUND_REGISTRAR.register("plushable_statuette", () -> SoundEvent.createVariableRangeEvent(ID("plushable_statuette")));
  public static final Supplier<SoundEvent> PLUSHABLE_WIZARD = SOUND_REGISTRAR.register("plushable_wizard", () -> SoundEvent.createVariableRangeEvent(ID("plushable_wizard")));

}
