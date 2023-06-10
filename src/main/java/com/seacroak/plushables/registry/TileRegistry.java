package com.seacroak.plushables.registry;

import com.seacroak.plushables.PlushablesMod;
import com.seacroak.plushables.block.tile.BuilderTileEntity;
import com.seacroak.plushables.block.tile.CluckyTileEntity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

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

public final class TileRegistry {
	public static final BlockEntityType<BuilderTileEntity> BUILDER_TILE = Registry.register(
			Registries.BLOCK_ENTITY_TYPE, PlushablesMod.MOD_ID + ":builder_tile",
			FabricBlockEntityTypeBuilder.create(BuilderTileEntity::new,
					MainRegistry.BUILDER_BLOCK).build(null));

	public static final BlockEntityType<CluckyTileEntity> CLUCKY_TILE = Registry.register(
			Registries.BLOCK_ENTITY_TYPE, PlushablesMod.MOD_ID + ":clucky_tile",
			FabricBlockEntityTypeBuilder.create(CluckyTileEntity::new,
					MainRegistry.CLUCKY_BLOCK).build(null));
}