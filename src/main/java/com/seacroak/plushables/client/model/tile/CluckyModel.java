package com.seacroak.plushables.client.model.tile;

import com.seacroak.plushables.block.tile.CluckyTileEntity;
import com.seacroak.plushables.util.GenericUtils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class CluckyModel extends GeoModel<CluckyTileEntity> {

	@Override
	public ResourceLocation getAnimationResource(CluckyTileEntity animatable) {
		return GenericUtils.ID("animations/clucky.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(CluckyTileEntity animateable) {
		return GenericUtils.ID("geo/clucky.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(CluckyTileEntity entity) {
		return GenericUtils.ID("textures/block/clucky_texture.png");
	}

	@Override
	public RenderType getRenderType(CluckyTileEntity animatable, ResourceLocation texture) {
		return RenderType.entityTranslucent(getTextureResource(animatable));
	}
}