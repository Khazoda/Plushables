package com.khazoda.plushables;

import com.khazoda.plushables.client.model.PlushableNorgeOrientationModel;
import com.khazoda.plushables.registry.MainRegistry;
import net.minecraft.client.renderer.block.BlockModelShaper;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.ModelEvent;

import java.util.IdentityHashMap;
import java.util.Map;

public class PlushablesNorgeClient {
  public static void register(IEventBus eventBus) {
    eventBus.addListener(PlushablesNorgeClient::modifyBakingResult);
  }

  public static void modifyBakingResult(ModelEvent.ModifyBakingResult event) {
    wrapPlushableModels(event.getModels());
  }

  private static void wrapPlushableModels(Map<ModelResourceLocation, BakedModel> models) {
    Map<BakedModel, PlushableNorgeOrientationModel> wrappedModels = new IdentityHashMap<>();

    for (var plushable : MainRegistry.PLUSHABLE_LIST) {
      Block block = plushable.getBlock();
      for (var state : block.getStateDefinition().getPossibleStates()) {
        ModelResourceLocation location = BlockModelShaper.stateToModelLocation(state);
        BakedModel model = models.get(location);
        if (model != null && !(model instanceof PlushableNorgeOrientationModel)) {
          models.put(location, wrappedModels.computeIfAbsent(model, PlushableNorgeOrientationModel::new));
        }
      }
    }
  }
}
