package com.seacroak.plushables.client.model.tile;

import com.seacroak.plushables.block.tile.CluckyTileEntity;
import com.seacroak.plushables.util.GenericUtils;

import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

/**
 * @author VoutVouniern Copyright (c) 03.06.2022 Developed by VoutVouniern
 */
public class CluckyModel extends AnimatedGeoModel<CluckyTileEntity> {

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
}