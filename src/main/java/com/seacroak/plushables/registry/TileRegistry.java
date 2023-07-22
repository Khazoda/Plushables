package com.seacroak.plushables.registry;

import com.seacroak.plushables.PlushablesMod;
import com.seacroak.plushables.block.tile.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.seacroak.plushables.registry.MainRegistry.*;

public final class TileRegistry {

	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
			DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, PlushablesMod.MOD_ID);

	public static final RegistryObject<BlockEntityType<BuilderTileEntity>> BUILDER_TILE = BLOCK_ENTITIES.register("builder_tile",
			() -> BlockEntityType.Builder.of(BuilderTileEntity::new, BUILDER_BLOCK.get()).build(null));
	public static final RegistryObject<BlockEntityType<CluckyTileEntity>> CLUCKY_TILE = BLOCK_ENTITIES.register("clucky_tile",
			() -> BlockEntityType.Builder.of(CluckyTileEntity::new, CLUCKY_BLOCK.get()).build(null));
	public static final RegistryObject<BlockEntityType<RupertTileEntity>> RUPERT_TILE = BLOCK_ENTITIES.register("rupert_tile",
			() -> BlockEntityType.Builder.of(RupertTileEntity::new, RUPERT_BLOCK.get()).build(null));
	public static final RegistryObject<BlockEntityType<DragonTileEntity>> DRAGON_TILE = BLOCK_ENTITIES.register("dragon_tile",
			() -> BlockEntityType.Builder.of(DragonTileEntity::new, DRAGON_BLOCK.get()).build(null));

	public static void register(IEventBus eventBus) {
		BLOCK_ENTITIES.register(eventBus);
	}
}