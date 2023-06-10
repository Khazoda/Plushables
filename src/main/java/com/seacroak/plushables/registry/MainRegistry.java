package com.seacroak.plushables.registry;

import com.seacroak.plushables.block.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;

public final class MainRegistry {
	// Item Settings
	static final FabricItemSettings defaultItemSettings = new FabricItemSettings().maxCount(8);
	static final FabricItemSettings plushableItemSettings = new FabricItemSettings().maxCount(8).equipmentSlot(stack -> EquipmentSlot.HEAD);

	// Complex Plushables
	public static final BuilderBlock BUILDER_BLOCK = register("builder_block", new BuilderBlock());
	public static final CluckyBlock CLUCKY_BLOCK = register("clucky_plushable", new CluckyBlock(),
			plushableItemSettings);

	// Simple Plushables
	public static final PenguinBlock PENGUIN_PLUSHABLE = register("penguin_plushable", new PenguinBlock(),
			plushableItemSettings);
	public static final FroglinBlock FROGLIN_PLUSHABLE = register("froglin_plushable", new FroglinBlock(),
			plushableItemSettings);
	public static final FoxBlock FOX_PLUSHABLE = register("fox_plushable", new FoxBlock(), plushableItemSettings);
	public static final PigBlock PIG_PLUSHABLE = register("pig_plushable", new PigBlock(), plushableItemSettings);
	public static final TrufflesBlock TRUFFLES_PLUSHABLE = register("truffles_plushable", new TrufflesBlock(),
			plushableItemSettings);

	// Items
	public static final Item HEART_OF_GOLD = register("heart_of_gold");

	public static void init() {
	}

	private static <B extends Block> B register(String name, B block, FabricItemSettings itemSettings) {
		return RegistryHelper.registerBlock(name, block, itemSettings);
	}

	private static <B extends Block> B register(String name, B block) {
		return RegistryHelper.registerBlock(name, block, defaultItemSettings);
	}

	private static Item register(String name) {
		return RegistryHelper.registerItem(name, new Item(defaultItemSettings));
	}

	private static Item register(String name, FabricItemSettings itemSettings) {
		return RegistryHelper.registerItem(name, new Item(itemSettings));
	}
}
