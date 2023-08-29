package com.seacroak.plushables.registry.client;

import com.seacroak.plushables.client.entityrenderers.BasketEntityRenderer;
import com.seacroak.plushables.registry.uncommon.TileRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;

public class EntityRendererRegistry {
  public static void initClient() {
    BlockEntityRendererRegistry.register(TileRegistry.BASKET_TILE, BasketEntityRenderer::new);
  }
}
