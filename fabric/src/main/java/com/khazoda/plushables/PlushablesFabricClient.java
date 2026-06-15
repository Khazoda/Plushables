package com.khazoda.plushables;

import com.khazoda.plushables.client.model.PlushableOrientationModel;
import com.khazoda.plushables.registry.MainRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelModifier;
import net.minecraft.client.renderer.RenderType;

public class PlushablesFabricClient implements ClientModInitializer {
  @Override
  public void onInitializeClient() {
    ModelLoadingPlugin.register(context
        -> context.modifyModelAfterBake().register(ModelModifier.WRAP_PHASE, (model, bakeContext)
        -> model != null && PlushableOrientationModel.isPlushableBlockModel(bakeContext.topLevelId()) ? new PlushableOrientationModel(model) : model));
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
}
