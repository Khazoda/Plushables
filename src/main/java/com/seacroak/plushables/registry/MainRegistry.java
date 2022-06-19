package com.seacroak.plushables.registry;

import com.seacroak.plushables.PlushablesMod;
import com.seacroak.plushables.block.BuilderBlock;
import com.seacroak.plushables.block.FoxBlock;
import com.seacroak.plushables.block.FroglinBlock;
import com.seacroak.plushables.block.PenguinBlock;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;

public class MainRegistry {
	// Default item settings
	static final FabricItemSettings defaultItemSettings = new FabricItemSettings().maxCount(8)
			.group(PlushablesMod.PLUSHABLES_GROUP);
	// Builder Block
	public static final BuilderBlock BUILDER_BLOCK = RegistryHelper.register("builder_block", new BuilderBlock(),
			defaultItemSettings);
	// Penguin Plushable
	public static final PenguinBlock PENGUIN_PLUSHABLE = RegistryHelper.register("penguin_plushable",
			new PenguinBlock(), defaultItemSettings);
	// Fox Plushable
	public static final FoxBlock FOX_PLUSHABLE = RegistryHelper.register("fox_plushable",
			new FoxBlock(), defaultItemSettings);
	// Froglin Plushable
	public static final FroglinBlock FROGLIN_PLUSHABLE = RegistryHelper.register("froglin_plushable",
			new FroglinBlock(), defaultItemSettings);

}
