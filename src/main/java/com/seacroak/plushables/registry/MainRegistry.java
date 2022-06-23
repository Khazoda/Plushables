package com.seacroak.plushables.registry;

import com.seacroak.plushables.PlushablesMod;
import com.seacroak.plushables.block.BuilderBlock;
import com.seacroak.plushables.block.CluckyBlock;
import com.seacroak.plushables.block.FoxBlock;
import com.seacroak.plushables.block.FroglinBlock;
import com.seacroak.plushables.block.PenguinBlock;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public final class MainRegistry {
	static final FabricItemSettings defaultItemSettings = new FabricItemSettings().maxCount(8)
			.group(PlushablesMod.PLUSHABLES_GROUP);

	public static final BuilderBlock BUILDER_BLOCK = register("builder_block", new BuilderBlock());

	public static final CluckyBlock CLUCKY_BLOCK = register("clucky_plushable", new CluckyBlock());

	public static final PenguinBlock PENGUIN_PLUSHABLE = register("penguin_plushable", new PenguinBlock());
	public static final FroglinBlock FROGLIN_PLUSHABLE = register("froglin_plushable", new FroglinBlock());
	public static final FoxBlock FOX_PLUSHABLE = register("fox_plushable", new FoxBlock());
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
