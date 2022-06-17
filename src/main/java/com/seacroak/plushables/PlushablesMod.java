package com.seacroak.plushables;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import software.bernie.geckolib3.GeckoLib;

import com.seacroak.plushables.registry.*;

public class PlushablesMod implements ModInitializer {
	public static boolean DISABLE_IN_DEV = false;
	public static final String ModID = "plushables";

	boolean isDevelopmentEnvironment = FabricLoader.getInstance().isDevelopmentEnvironment();

	@Override
	public void onInitialize() {
		GeckoLib.initialize();
		if (isDevelopmentEnvironment && !PlushablesMod.DISABLE_IN_DEV) {
			new ItemRegistry();
			new TileRegistry();
			new BlockRegistry();
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
