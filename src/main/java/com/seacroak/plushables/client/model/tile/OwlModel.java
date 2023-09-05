package com.seacroak.plushables.client.model.tile;

import com.seacroak.plushables.block.tile.OwlTileEntity;
import com.seacroak.plushables.util.GenericUtils;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class OwlModel extends GeoModel<OwlTileEntity> {

	@Override
	public Identifier getAnimationResource(OwlTileEntity animatable) {
		return GenericUtils.ID("animations/owl.animation.json");
	}

	@Override
	public Identifier getModelResource(OwlTileEntity animateable) {
		return GenericUtils.ID("geo/owl.geo.json");
	}

	@Override
	public Identifier getTextureResource(OwlTileEntity entity) {
		return GenericUtils.ID("textures/block/owl_texture.png");
	}

	@Override
	public RenderLayer getRenderType(OwlTileEntity animatable, Identifier texture) {
		return RenderLayer.getEntityCutout(getTextureResource(animatable));
	}
}