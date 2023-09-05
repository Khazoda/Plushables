package com.seacroak.plushables.registry.client;

import com.seacroak.plushables.client.renderer.tile.*;
import com.seacroak.plushables.registry.uncommon.TileRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;

public final class TileRegistryClient {
	@Environment(EnvType.CLIENT)
	public static void initClient() {
		BlockEntityRendererFactories.register(TileRegistry.BUILDER_TILE,
				(BlockEntityRendererFactory.Context rendererDispatcherIn) -> new BuilderTileRenderer());
		BlockEntityRendererFactories.register(TileRegistry.CLUCKY_TILE,
				(BlockEntityRendererFactory.Context rendererDispatcherIn) -> new CluckyTileRenderer());
    BlockEntityRendererFactories.register(TileRegistry.RUPERT_TILE,
      (BlockEntityRendererFactory.Context rendererDispatcherIn) -> new RupertTileRenderer());
    BlockEntityRendererFactories.register(TileRegistry.DRAGON_TILE,
      (BlockEntityRendererFactory.Context rendererDispatcherIn) -> new DragonTileRenderer());
		BlockEntityRendererFactories.register(TileRegistry.ORANGUTAN_TILE,
				(BlockEntityRendererFactory.Context rendererDispatcherIn) -> new OrangutanTileRenderer());
		BlockEntityRendererFactories.register(TileRegistry.OWL_TILE,
				(BlockEntityRendererFactory.Context rendererDispatcherIn) -> new OwlTileRenderer());
	}
}