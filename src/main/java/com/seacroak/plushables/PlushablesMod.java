package com.seacroak.plushables;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.GeckoLib;

import com.seacroak.plushables.registry.*;

public class PlushablesMod implements ModInitializer {
	public static boolean DISABLE_IN_DEV = false;
	public static final String ModID = "plushables";

	boolean isDevelopmentEnvironment = FabricLoader.getInstance().isDevelopmentEnvironment();

	public static final ItemGroup PLUSHABLES_GROUP = FabricItemGroupBuilder.create(
			new Identifier(PlushablesMod.ModID, "plushables"))
			.icon(() -> new ItemStack(MainRegistry.FOX_PLUSHABLE))
			.appendItems(stacks -> {
				stacks.add(new ItemStack(MainRegistry.FOX_PLUSHABLE));
				stacks.add(new ItemStack(MainRegistry.PENGUIN_PLUSHABLE));
				stacks.add(new ItemStack(MainRegistry.FROGLIN_PLUSHABLE));

			}).build();

	@Override
	public void onInitialize() {
		GeckoLib.initialize();
		new MainRegistry();
		new TileRegistry();

		if (isDevelopmentEnvironment && !PlushablesMod.DISABLE_IN_DEV) {
			// Code to be run in dev environment only
			// new SoundRegistry();
		}
	}
}

// public class PlushablesMod implements ModInitializer {
// // This logger is used to write text to the console and the log file.
// // It is considered best practice to use your mod id as the logger's name.
// // That way, it's clear which mod wrote info, warnings, and errors.
// public static final Logger LOGGER = LoggerFactory.getLogger("plushables");

// // Builder
// public static final Block BUILDER_BLOCK = new
// Block(FabricBlockSettings.of(Material.WOOD).strength(4.0f));
// public static BlockEntityType<BuilderBlockEntity> BUILDER_BLOCK_ENTITY;

// @Override
// public void onInitialize() {
// // This code runs as soon as Minecraft is in a mod-load-ready state.
// // However, some things (like resources) may still be uninitialized.
// // Proceed with mild caution.

// LOGGER.info("<<Plushables Loaded>>");

// // Builder
// Registry.register(Registry.BLOCK, new Identifier("plushables",
// "builder_block"), BUILDER_BLOCK);
// Registry.register(Registry.ITEM, new Identifier("plushables",
// "builder_block"),
// new BlockItem(BUILDER_BLOCK, new
// FabricItemSettings().group(ItemGroup.MISC)));
// BUILDER_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE,
// "plushables:builder_block_entity",
// FabricBlockEntityTypeBuilder.create(BuilderBlockEntity::new,
// BUILDER_BLOCK).build(null));

// }

// }
