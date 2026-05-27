package com.khazoda.plushables;

import com.khazoda.plushables.client.model.PlushableNorgeOrientationModel;
import com.khazoda.plushables.client.model.PlushableOrientationModel;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.ModelEvent;

public class PlushablesNorgeClient {
  public static void register(IEventBus eventBus) {
    eventBus.addListener(PlushablesNorgeClient::modifyBakingResult);
  }

  public static void modifyBakingResult(ModelEvent.ModifyBakingResult event) {
    event.getModels().replaceAll((location, model) -> PlushableOrientationModel.isPlushableBlockModel(location) ? new PlushableNorgeOrientationModel(model) : model);
  }
}
