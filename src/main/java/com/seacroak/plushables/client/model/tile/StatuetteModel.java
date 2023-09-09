package com.seacroak.plushables.client.model.tile;

import com.seacroak.plushables.block.tile.StatuetteTileEntity;
import com.seacroak.plushables.util.GenericUtils;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class StatuetteModel extends GeoModel<StatuetteTileEntity> {

	@Override
	public Identifier getAnimationResource(StatuetteTileEntity animatable) {
		return GenericUtils.ID("animations/statuette.animation.json");
	}

	@Override
	public Identifier getModelResource(StatuetteTileEntity animateable) {
		return GenericUtils.ID("geo/statuette.geo.json");
	}

	@Override
	public Identifier getTextureResource(StatuetteTileEntity entity) {
		return GenericUtils.ID("textures/block/statuette_texture.png");
	}

	@Override
	public RenderLayer getRenderType(StatuetteTileEntity animatable, Identifier texture) {
		return RenderLayer.getEntityCutout(getTextureResource(animatable));
	}
}