package com.seacroak.plushables.registry;

import com.seacroak.plushables.client.renderer.tile.BuilderTileRenderer;
import com.seacroak.plushables.client.renderer.tile.CluckyTileRenderer;
import com.seacroak.plushables.client.renderer.tile.DragonTileRenderer;
import com.seacroak.plushables.client.renderer.tile.RupertTileRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;


public final class TileRegistryClient {
  public static void initClient() {
    BlockEntityRenderers.register(TileRegistry.BUILDER_TILE.get(),BuilderTileRenderer::new);
    BlockEntityRenderers.register(TileRegistry.CLUCKY_TILE.get(),CluckyTileRenderer::new);
    BlockEntityRenderers.register(TileRegistry.DRAGON_TILE.get(),DragonTileRenderer::new);
    BlockEntityRenderers.register(TileRegistry.RUPERT_TILE.get(),RupertTileRenderer::new);

  }
//  public static void initClient(final EntityRenderersEvent.RegisterRenderers event) {
//    event.registerBlockEntityRenderer(TileRegistry.BUILDER_TILE.get(), BuilderTileRenderer::new);
//    event.registerBlockEntityRenderer(TileRegistry.CLUCKY_TILE.get(), CluckyTileRenderer::new);
//    event.registerBlockEntityRenderer(TileRegistry.DRAGON_TILE.get(), DragonTileRenderer::new);
//    event.registerBlockEntityRenderer(TileRegistry.RUPERT_TILE.get(), RupertTileRenderer::new);
//  }
}