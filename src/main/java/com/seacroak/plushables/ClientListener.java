/*
 * Copyright (c) 2020.
 * Author: Bernie G. (Gecko)
 */

package com.seacroak.plushables;

import com.seacroak.plushables.client.renderer.tile.BuilderTileRenderer;
import com.seacroak.plushables.client.renderer.tile.PenguinTileRenderer;
import com.seacroak.plushables.registry.TileRegistry;

import net.fabricmc.api.ClientModInitializer;

import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;

import net.fabricmc.fabric.impl.blockrenderlayer.BlockRenderLayerMapImpl;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;

import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class ClientListener implements ClientModInitializer {

	@SuppressWarnings({ "unchecked" })
	@Override
	public void onInitializeClient() {
		if (FabricLoader.getInstance().isDevelopmentEnvironment() && !PlushablesMod.DISABLE_IN_DEV) {

			// GeoItemRenderer.registerItemRenderer(ItemRegistry.JACK_IN_THE_BOX, new
			// JackInTheBoxRenderer());
			// GeoItemRenderer.registerItemRenderer(ItemRegistry.PISTOL, new
			// PistolRender());
			// GeoArmorRenderer.registerArmorRenderer(new PotatoArmorRenderer(),
			// ItemRegistry.POTATO_HEAD,
			// ItemRegistry.POTATO_CHEST, ItemRegistry.POTATO_LEGGINGS,
			// ItemRegistry.POTATO_BOOTS);
			// BlockEntityRendererRegistry.register(TileRegistry.HABITAT_TILE,
			// (BlockEntityRendererFactory.Context rendererDispatcherIn) -> new
			// HabitatTileRenderer());
			// BlockEntityRendererRegistry.register(TileRegistry.FERTILIZER,
			// (BlockEntityRendererFactory.Context rendererDispatcherIn) -> new
			// FertilizerTileRenderer());
			// BlockRenderLayerMapImpl.INSTANCE.putBlock(BlockRegistry.HABITAT_BLOCK,
			// RenderLayer.getTranslucent());
			BlockEntityRendererRegistry.register(TileRegistry.BUILDER_TILE,
					(BlockEntityRendererFactory.Context rendererDispatcherIn) -> new BuilderTileRenderer());
			BlockEntityRendererRegistry.register(TileRegistry.PENGUIN_TILE,
					(BlockEntityRendererFactory.Context rendererDispatcherIn) -> new PenguinTileRenderer());
		}
	}

}
