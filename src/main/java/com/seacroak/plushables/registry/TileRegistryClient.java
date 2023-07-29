package com.seacroak.plushables.registry;

import com.seacroak.plushables.client.renderer.tile.BuilderTileRenderer;
import com.seacroak.plushables.client.renderer.tile.CluckyTileRenderer;
import com.seacroak.plushables.client.renderer.tile.DragonTileRenderer;
import com.seacroak.plushables.client.renderer.tile.RupertTileRenderer;
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
	}
}