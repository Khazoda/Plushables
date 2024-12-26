package com.khazoda.plushables;

import com.khazoda.plushables.registry.MainRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;

public class PlushablesFabricClient implements ClientModInitializer {
  @Override
  public void onInitializeClient() {
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.PLUSHABLE_PIG_BLOCK.get(), RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.PLUSHABLE_TRUFFLES_BLOCK.get(), RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.PLUSHABLE_WHELPLING_BLOCK.get(), RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.PLUSHABLE_RAPTOR_BLOCK.get(), RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.PLUSHABLE_BIG_TATER_BLOCK.get(), RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.PLUSHABLE_BIG_IRRITATER_BLOCK.get(), RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.PLUSHABLE_OTTER_BLOCK.get(), RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.PLUSHABLE_SHRUMP_BLOCK.get(), RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.PLUSHABLE_WHALE_BLOCK.get(), RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.PLUSHABLE_GOLDFISH_BLOCK.get(), RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.PLUSHABLE_TRATER_BLOCK.get(), RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.PLUSHABLE_FROGE_BLOCK.get(), RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.PLUSHABLE_MAMMOTH_BLOCK.get(), RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.PLUSHABLE_TIGER_BLOCK.get(), RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.PLUSHABLE_BLAHAJ_BLOCK.get(), RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.PLUSHABLE_RUPERT_BLOCK.get(), RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.PLUSHABLE_OWL_BLOCK.get(), RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.PLUSHABLE_STATUETTE_BLOCK.get(), RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.PLUSHABLE_CLUCKY_BLOCK.get(), RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.PLUSHABLE_DRAGON_BLOCK.get(), RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.PLUSHABLE_SNOWIE_BLOCK.get(), RenderType.cutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.PLUSHABLE_RIBBIT_BLOCK.get(), RenderType.cutout());
  }
}
