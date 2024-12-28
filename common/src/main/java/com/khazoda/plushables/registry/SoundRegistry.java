package com.khazoda.plushables.registry;

import com.khazoda.plushables.PlushablesCommon;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;

import java.util.function.Supplier;

import static com.khazoda.plushables.Constants.ID;

public class SoundRegistry {
  private static final Reggie<SoundEvent> SOUND_REGISTRAR = PlushablesCommon.REGISTRARS.get(Registries.SOUND_EVENT);

  public static final Supplier<SoundEvent> PLUSHABLE_OWL = SOUND_REGISTRAR.register("plushable_owl", () -> SoundEvent.createVariableRangeEvent(ID("plushable_owl")));

}
