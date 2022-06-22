package com.seacroak.plushables.registry;

import com.seacroak.plushables.PlushablesMod;
import com.seacroak.plushables.block.tile.BuilderTileEntity;
import com.seacroak.plushables.client.renderer.tile.BuilderTileRenderer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.util.registry.Registry;

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
	@SuppressWarnings("unchecked")
	@Environment(EnvType.CLIENT)
	public static void initClient() {
		BlockEntityRendererRegistry.register(TileRegistry.BUILDER_TILE,
				(BlockEntityRendererFactory.Context rendererDispatcherIn) -> new BuilderTileRenderer());
	}
}