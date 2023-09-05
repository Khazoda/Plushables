package com.seacroak.plushables.registry.uncommon;

import com.seacroak.plushables.PlushablesMod;
import com.seacroak.plushables.block.tile.*;
import com.seacroak.plushables.registry.MainRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;


public final class TileRegistry {
	public static final BlockEntityType<BuilderTileEntity> BUILDER_TILE = Registry.register(
			Registries.BLOCK_ENTITY_TYPE, PlushablesMod.MOD_ID + ":builder_block_entity",
			FabricBlockEntityTypeBuilder.create(BuilderTileEntity::new,
					MainRegistry.BUILDER_BLOCK).build(null));

	public static final BlockEntityType<BasketBlockEntity> BASKET_TILE = Registry.register(
			Registries.BLOCK_ENTITY_TYPE, PlushablesMod.MOD_ID + ":basket_block_entity",
			FabricBlockEntityTypeBuilder.create(BasketBlockEntity::new,
					MainRegistry.BASKET_BLOCK).build(null));

	public static final BlockEntityType<CluckyTileEntity> CLUCKY_TILE = Registry.register(
			Registries.BLOCK_ENTITY_TYPE, PlushablesMod.MOD_ID + ":clucky_block_entity",
			FabricBlockEntityTypeBuilder.create(CluckyTileEntity::new,
					MainRegistry.CLUCKY_PLUSHABLE).build(null));

  public static final BlockEntityType<RupertTileEntity> RUPERT_TILE = Registry.register(
    Registries.BLOCK_ENTITY_TYPE, PlushablesMod.MOD_ID + ":rupert_block_entity",
    FabricBlockEntityTypeBuilder.create(RupertTileEntity::new,
      MainRegistry.RUPERT_PLUSHABLE).build(null));

  public static final BlockEntityType<DragonTileEntity> DRAGON_TILE = Registry.register(
    Registries.BLOCK_ENTITY_TYPE, PlushablesMod.MOD_ID + ":dragon_block_entity",
    FabricBlockEntityTypeBuilder.create(DragonTileEntity::new,
      MainRegistry.DRAGON_PLUSHABLE).build(null));

	public static final BlockEntityType<OrangutanTileEntity> ORANGUTAN_TILE = Registry.register(
			Registries.BLOCK_ENTITY_TYPE, PlushablesMod.MOD_ID + ":orangutan_block_entity",
			FabricBlockEntityTypeBuilder.create(OrangutanTileEntity::new,
					MainRegistry.ORANGUTAN_PLUSHABLE).build(null));

	public static final BlockEntityType<OwlTileEntity> OWL_TILE = Registry.register(
			Registries.BLOCK_ENTITY_TYPE, PlushablesMod.MOD_ID + ":owl_block_entity",
			FabricBlockEntityTypeBuilder.create(OwlTileEntity::new,
					MainRegistry.OWL_PLUSHABLE).build(null));
}