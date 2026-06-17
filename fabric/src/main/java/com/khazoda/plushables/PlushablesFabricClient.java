package com.khazoda.plushables;

import com.khazoda.plushables.client.model.PlushableOrientationModel;
import com.khazoda.plushables.registry.MainRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelModifier;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockModelShaper;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.world.level.block.Block;

import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

public class PlushablesFabricClient implements ClientModInitializer {
  @Override
  public void onInitializeClient() {
    ModelLoadingPlugin.register(context -> {
      Set<ModelResourceLocation> plushableModelLocations = plushableModelLocations();
      Map<BakedModel, PlushableOrientationModel> wrappedModels = new IdentityHashMap<>();

      context.modifyModelAfterBake().register(ModelModifier.WRAP_PHASE, (model, bakeContext) -> {
        ModelResourceLocation location = bakeContext.topLevelId();
        if (model == null || !plushableModelLocations.contains(location)) {
          return model;
        }
        return wrappedModels.computeIfAbsent(model, PlushableOrientationModel::new);
      });
    });

    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.PLUSHABLE_PIG_BLOCK.getBlock(), RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.PLUSHABLE_TRUFFLES_BLOCK.getBlock(), RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.PLUSHABLE_WHELPLING_BLOCK.getBlock(), RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.PLUSHABLE_RAPTOR_BLOCK.getBlock(), RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.PLUSHABLE_BIG_TATER_BLOCK.getBlock(), RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.PLUSHABLE_BIG_IRRITATER_BLOCK.getBlock(), RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.PLUSHABLE_OTTER_BLOCK.getBlock(), RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.PLUSHABLE_SHRUMP_BLOCK.getBlock(), RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.PLUSHABLE_WHALE_BLOCK.getBlock(), RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.PLUSHABLE_GOLDFISH_BLOCK.getBlock(), RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.PLUSHABLE_TRATER_BLOCK.getBlock(), RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.PLUSHABLE_FROGE_BLOCK.getBlock(), RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.PLUSHABLE_MAMMOTH_BLOCK.getBlock(), RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.PLUSHABLE_TIGER_BLOCK.getBlock(), RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.PLUSHABLE_BLAHAJ_BLOCK.getBlock(), RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.PLUSHABLE_RUPERT_BLOCK.getBlock(), RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.PLUSHABLE_OWL_BLOCK.getBlock(), RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.PLUSHABLE_STATUETTE_BLOCK.getBlock(), RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.PLUSHABLE_CLUCKY_BLOCK.getBlock(), RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.PLUSHABLE_DRAGON_BLOCK.getBlock(), RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.PLUSHABLE_SNOWIE_BLOCK.getBlock(), RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.PLUSHABLE_RIBBIT_BLOCK.getBlock(), RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.PLUSHABLE_CREAKY_BLOCK.getBlock(), RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.PLUSHABLE_KWEEBEC_BLOCK.getBlock(), RenderType.cutout());

  }

  private static Set<ModelResourceLocation> plushableModelLocations() {
    Set<ModelResourceLocation> locations = new HashSet<>();
    for (var plushable : MainRegistry.PLUSHABLE_LIST) {
      Block block = plushable.getBlock();
      for (var state : block.getStateDefinition().getPossibleStates()) {
        locations.add(BlockModelShaper.stateToModelLocation(state));
      }
    }
    return locations;
  }
}
