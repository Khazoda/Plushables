package com.khazoda.plushables;

import com.khazoda.plushables.registry.MainRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;

public class PlushablesFabricClient implements ClientModInitializer {
  @Override
  public void onInitializeClient() {
    BlockRenderLayerMap.putBlock(MainRegistry.PLUSHABLE_PIG_BLOCK.get(), ChunkSectionLayer.CUTOUT);
    BlockRenderLayerMap.putBlock(MainRegistry.PLUSHABLE_TRUFFLES_BLOCK.get(), ChunkSectionLayer.CUTOUT);
    BlockRenderLayerMap.putBlock(MainRegistry.PLUSHABLE_WHELPLING_BLOCK.get(), ChunkSectionLayer.CUTOUT);
    BlockRenderLayerMap.putBlock(MainRegistry.PLUSHABLE_RAPTOR_BLOCK.get(), ChunkSectionLayer.CUTOUT);
    BlockRenderLayerMap.putBlock(MainRegistry.PLUSHABLE_BIG_TATER_BLOCK.get(), ChunkSectionLayer.CUTOUT);
    BlockRenderLayerMap.putBlock(MainRegistry.PLUSHABLE_BIG_IRRITATER_BLOCK.get(), ChunkSectionLayer.CUTOUT);
    BlockRenderLayerMap.putBlock(MainRegistry.PLUSHABLE_OTTER_BLOCK.get(), ChunkSectionLayer.CUTOUT);
    BlockRenderLayerMap.putBlock(MainRegistry.PLUSHABLE_SHRUMP_BLOCK.get(), ChunkSectionLayer.CUTOUT);
    BlockRenderLayerMap.putBlock(MainRegistry.PLUSHABLE_WHALE_BLOCK.get(), ChunkSectionLayer.CUTOUT);
    BlockRenderLayerMap.putBlock(MainRegistry.PLUSHABLE_GOLDFISH_BLOCK.get(), ChunkSectionLayer.CUTOUT);
    BlockRenderLayerMap.putBlock(MainRegistry.PLUSHABLE_TRATER_BLOCK.get(), ChunkSectionLayer.CUTOUT);
    BlockRenderLayerMap.putBlock(MainRegistry.PLUSHABLE_FROGE_BLOCK.get(), ChunkSectionLayer.CUTOUT);
    BlockRenderLayerMap.putBlock(MainRegistry.PLUSHABLE_MAMMOTH_BLOCK.get(), ChunkSectionLayer.CUTOUT);
    BlockRenderLayerMap.putBlock(MainRegistry.PLUSHABLE_TIGER_BLOCK.get(), ChunkSectionLayer.CUTOUT);
    BlockRenderLayerMap.putBlock(MainRegistry.PLUSHABLE_BLAHAJ_BLOCK.get(), ChunkSectionLayer.CUTOUT);
    BlockRenderLayerMap.putBlock(MainRegistry.PLUSHABLE_RUPERT_BLOCK.get(), ChunkSectionLayer.CUTOUT);
    BlockRenderLayerMap.putBlock(MainRegistry.PLUSHABLE_OWL_BLOCK.get(), ChunkSectionLayer.CUTOUT);
    BlockRenderLayerMap.putBlock(MainRegistry.PLUSHABLE_STATUETTE_BLOCK.get(), ChunkSectionLayer.CUTOUT);
    BlockRenderLayerMap.putBlock(MainRegistry.PLUSHABLE_CLUCKY_BLOCK.get(), ChunkSectionLayer.CUTOUT);
    BlockRenderLayerMap.putBlock(MainRegistry.PLUSHABLE_DRAGON_BLOCK.get(), ChunkSectionLayer.CUTOUT);
    BlockRenderLayerMap.putBlock(MainRegistry.PLUSHABLE_SNOWIE_BLOCK.get(), ChunkSectionLayer.CUTOUT);
    BlockRenderLayerMap.putBlock(MainRegistry.PLUSHABLE_RIBBIT_BLOCK.get(), ChunkSectionLayer.CUTOUT);
    BlockRenderLayerMap.putBlock(MainRegistry.PLUSHABLE_CREAKY_BLOCK.get(), ChunkSectionLayer.CUTOUT);
    BlockRenderLayerMap.putBlock(MainRegistry.PLUSHABLE_KWEEBEC_BLOCK.get(), ChunkSectionLayer.CUTOUT);

  }
}
