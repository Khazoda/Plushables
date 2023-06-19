package com.seacroak.plushables.client.model.tile;

import com.seacroak.plushables.block.tile.CluckyTileEntity;
import com.seacroak.plushables.block.tile.RupertTileEntity;
import com.seacroak.plushables.util.GenericUtils;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class RupertModel extends GeoModel<RupertTileEntity> {

	@Override
	public Identifier getAnimationResource(RupertTileEntity animatable) {
		return GenericUtils.ID("animations/rupert.animation.json");
	}

	@Override
	public Identifier getModelResource(RupertTileEntity animateable) {
		return GenericUtils.ID("geo/rupert.geo.json");
	}

	@Override
	public Identifier getTextureResource(RupertTileEntity entity) {
		return GenericUtils.ID("textures/block/rupert_texture.png");
	}

	@Override
	public RenderLayer getRenderType(RupertTileEntity animatable, Identifier texture) {
		return RenderLayer.getEntityCutout(getTextureResource(animatable));
	}
}