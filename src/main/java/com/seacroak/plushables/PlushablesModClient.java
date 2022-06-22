package com.seacroak.plushables;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.seacroak.plushables.registry.ItemGroupRegistry;
import com.seacroak.plushables.registry.ScreenRegistry;
import com.seacroak.plushables.registry.TileRegistry;
import com.seacroak.plushables.registry.TileRegistryClient;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ItemGroup;

public final class PlushablesModClient implements ClientModInitializer {
	@Override
	@Environment(EnvType.CLIENT)
	public void onInitializeClient() {
		ScreenRegistry.initClient();
		TileRegistryClient.initClient();
	}

}
