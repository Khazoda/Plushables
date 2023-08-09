package com.seacroak.plushables.registry;

import com.seacroak.plushables.PlushablesMod;
import com.seacroak.plushables.block.tile.*;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;


public final class TileRegistry {
	public static final BlockEntityType<BuilderTileEntity> BUILDER_TILE = Registry.register(
			Registries.BLOCK_ENTITY_TYPE, PlushablesMod.MOD_ID + ":builder_tile",
			FabricBlockEntityTypeBuilder.create(BuilderTileEntity::new,
					MainRegistry.BUILDER_BLOCK).build(null));

	public static final BlockEntityType<HatRackBlockEntity> HAT_RACK_TILE = Registry.register(
			Registries.BLOCK_ENTITY_TYPE, PlushablesMod.MOD_ID + ":hat_rack_tile",
			FabricBlockEntityTypeBuilder.create(HatRackBlockEntity::new,
					MainRegistry.HAT_RACK_BLOCK).build(null));

	public static final BlockEntityType<CluckyTileEntity> CLUCKY_TILE = Registry.register(
			Registries.BLOCK_ENTITY_TYPE, PlushablesMod.MOD_ID + ":clucky_tile",
			FabricBlockEntityTypeBuilder.create(CluckyTileEntity::new,
					MainRegistry.CLUCKY_BLOCK).build(null));

  public static final BlockEntityType<RupertTileEntity> RUPERT_TILE = Registry.register(
    Registries.BLOCK_ENTITY_TYPE, PlushablesMod.MOD_ID + ":rupert_tile",
    FabricBlockEntityTypeBuilder.create(RupertTileEntity::new,
      MainRegistry.RUPERT_BLOCK).build(null));

  public static final BlockEntityType<DragonTileEntity> DRAGON_TILE = Registry.register(
    Registries.BLOCK_ENTITY_TYPE, PlushablesMod.MOD_ID + ":dragon_tile",
    FabricBlockEntityTypeBuilder.create(DragonTileEntity::new,
      MainRegistry.DRAGON_BLOCK).build(null));

	public static final BlockEntityType<OrangutanTileEntity> ORANGUTAN_TILE = Registry.register(
			Registries.BLOCK_ENTITY_TYPE, PlushablesMod.MOD_ID + ":orangutan_tile",
			FabricBlockEntityTypeBuilder.create(OrangutanTileEntity::new,
					MainRegistry.ORANGUTAN_BLOCK).build(null));
}