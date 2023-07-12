package com.seacroak.plushables.client.model.tile;

import com.seacroak.plushables.block.tile.RupertTileEntity;
import com.seacroak.plushables.util.GenericUtils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class RupertModel extends GeoModel<RupertTileEntity> {

	@Override
	public ResourceLocation getAnimationResource(RupertTileEntity animatable) {
		return GenericUtils.ID("animations/rupert.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(RupertTileEntity animateable) {
		return GenericUtils.ID("geo/rupert.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(RupertTileEntity entity) {
		return GenericUtils.ID("textures/block/rupert_texture.png");
	}

	@Override
	public RenderType getRenderType(RupertTileEntity animatable, ResourceLocation texture) {
		return RenderType.entityTranslucent(getTextureResource(animatable));
	}
}