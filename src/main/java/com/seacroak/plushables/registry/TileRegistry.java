package com.seacroak.plushables.registry;

import com.seacroak.plushables.PlushablesMod;
import com.seacroak.plushables.block.tile.BuilderTileEntity;
import com.seacroak.plushables.block.tile.PenguinTileEntity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;
import software.bernie.example.block.tile.HabitatTileEntity;
import software.bernie.example.block.tile.FertilizerTileEntity;
import software.bernie.geckolib3.GeckoLib;

public class TileRegistry {
	// public static final BlockEntityType<HabitatTileEntity> HABITAT_TILE =
	// Registry.register(
	// Registry.BLOCK_ENTITY_TYPE, GeckoLib.ModID + ":habitattile",
	// FabricBlockEntityTypeBuilder.create(HabitatTileEntity::new,
	// BlockRegistry.HABITAT_BLOCK).build(null));
	// public static final BlockEntityType<FertilizerTileEntity> FERTILIZER =
	// Registry.register(Registry.BLOCK_ENTITY_TYPE,
	// GeckoLib.ModID + ":fertilizertile",
	// FabricBlockEntityTypeBuilder.create(FertilizerTileEntity::new,
	// BlockRegistry.FERTILIZER_BLOCK).build(null));

	public static final BlockEntityType<BuilderTileEntity> BUILDER_TILE = Registry.register(
			Registry.BLOCK_ENTITY_TYPE, PlushablesMod.ModID + ":builder_tile",
			FabricBlockEntityTypeBuilder.create(BuilderTileEntity::new, BlockRegistry.BUILDER_BLOCK).build(null));

	public static final BlockEntityType<PenguinTileEntity> PENGUIN_TILE = Registry.register(
			Registry.BLOCK_ENTITY_TYPE, PlushablesMod.ModID + ":penguin_tile",
			FabricBlockEntityTypeBuilder.create(PenguinTileEntity::new, BlockRegistry.PENGUIN_BLOCK).build(null));
}
