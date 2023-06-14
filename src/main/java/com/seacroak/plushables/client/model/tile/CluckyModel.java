package com.seacroak.plushables.client.model.tile;

import com.seacroak.plushables.block.tile.CluckyTileEntity;
import com.seacroak.plushables.util.GenericUtils;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class CluckyModel extends GeoModel<CluckyTileEntity> {

	@Override
	public Identifier getAnimationResource(CluckyTileEntity animatable) {
		return GenericUtils.ID("animations/clucky.animation.json");
	}

	@Override
	public Identifier getModelResource(CluckyTileEntity animateable) {
		return GenericUtils.ID("geo/clucky.geo.json");
	}

	@Override
	public Identifier getTextureResource(CluckyTileEntity entity) {
		return GenericUtils.ID("textures/block/clucky_texture.png");
	}

	@Override
	public RenderLayer getRenderType(CluckyTileEntity animatable, Identifier texture) {
		return RenderLayer.getEntityCutout(getTextureResource(animatable));
	}
}