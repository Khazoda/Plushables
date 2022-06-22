package com.seacroak.plushables;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.seacroak.plushables.registry.ItemGroupRegistry;
import com.seacroak.plushables.registry.MainRegistry;
import com.seacroak.plushables.registry.RecipeRegistry;
import com.seacroak.plushables.registry.ScreenRegistry;
import com.seacroak.plushables.registry.SoundRegistry;
import com.seacroak.plushables.registry.TileRegistry;

import net.fabricmc.api.ModInitializer;
import net.minecraft.item.ItemGroup;
import software.bernie.geckolib3.GeckoLib;

public final class PlushablesMod implements ModInitializer {
	public static final String MOD_ID = "plushables";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final ItemGroup PLUSHABLES_GROUP = ItemGroupRegistry.createItemGroup();

	@Override
	public void onInitialize() {
		GeckoLib.initialize();
		MainRegistry.init();
		SoundRegistry.init();
		ScreenRegistry.init();
		RecipeRegistry.init();
		new TileRegistry();

		LOGGER.info("Plushables has loaded");
	}

}
