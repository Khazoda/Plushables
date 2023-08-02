package com.seacroak.plushables.client.model.tile;

import com.seacroak.plushables.block.tile.OrangutanTileEntity;
import com.seacroak.plushables.util.GenericUtils;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class OrangutanModel extends GeoModel<OrangutanTileEntity> {

	@Override
	public Identifier getAnimationResource(OrangutanTileEntity animatable) {
		return GenericUtils.ID("animations/orangutan.animation.json");
	}

	@Override
	public Identifier getModelResource(OrangutanTileEntity animateable) {
		return GenericUtils.ID("geo/orangutan.geo.json");
	}

	@Override
	public Identifier getTextureResource(OrangutanTileEntity entity) {
		return GenericUtils.ID("textures/block/orangutan_texture.png");
	}

	@Override
	public RenderLayer getRenderType(OrangutanTileEntity animatable, Identifier texture) {
		return RenderLayer.getEntityCutout(getTextureResource(animatable));
	}
}