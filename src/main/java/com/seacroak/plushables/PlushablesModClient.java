package com.seacroak.plushables;

import com.seacroak.plushables.registry.ScreenRegistry;
import com.seacroak.plushables.registry.TileRegistryClient;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

public final class PlushablesModClient implements ClientModInitializer {
	@Override
	@Environment(EnvType.CLIENT)
	public void onInitializeClient() {
		ScreenRegistry.initClient();
		TileRegistryClient.initClient();
	}

}
