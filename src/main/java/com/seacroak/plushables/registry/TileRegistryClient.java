package com.seacroak.plushables.registry;

import com.seacroak.plushables.client.renderer.tile.BuilderTileRenderer;
import com.seacroak.plushables.client.renderer.tile.CluckyTileRenderer;
import com.seacroak.plushables.client.renderer.tile.RupertTileRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;

// public class TileRegistry {
// 	// public static final BlockEntityType<HabitatTileEntity> HABITAT_TILE =
// 	// Registry.register(
// 	// Registry.BLOCK_ENTITY_TYPE, GeckoLib.ModID + ":habitattile",
// 	// FabricBlockEntityTypeBuilder.create(HabitatTileEntity::new,
// 	// BlockRegistry.HABITAT_BLOCK).build(null));
// 	// public static final BlockEntityType<FertilizerTileEntity> FERTILIZER =
// 	// Registry.register(Registry.BLOCK_ENTITY_TYPE,
// 	// GeckoLib.ModID + ":fertilizertile",
// 	// FabricBlockEntityTypeBuilder.create(FertilizerTileEntity::new,
// 	// BlockRegistry.FERTILIZER_BLOCK).build(null));

// }

public final class TileRegistryClient {
	@Environment(EnvType.CLIENT)
	public static void initClient() {
		BlockEntityRendererFactories.register(TileRegistry.BUILDER_TILE,
				(BlockEntityRendererFactory.Context rendererDispatcherIn) -> new BuilderTileRenderer());
		BlockEntityRendererFactories.register(TileRegistry.CLUCKY_TILE,
				(BlockEntityRendererFactory.Context rendererDispatcherIn) -> new CluckyTileRenderer());
    BlockEntityRendererFactories.register(TileRegistry.RUPERT_TILE,
      (BlockEntityRendererFactory.Context rendererDispatcherIn) -> new RupertTileRenderer());
	}
}