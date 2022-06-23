package com.seacroak.plushables.client.model.tile;

import com.seacroak.plushables.block.tile.BuilderTileEntity;
import com.seacroak.plushables.util.GenericUtils;

import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

/**
 * @author VoutVouniern Copyright (c) 03.06.2022 Developed by VoutVouniern
 */
public class BuilderModel extends AnimatedGeoModel<BuilderTileEntity> {

	@Override
	public Identifier getAnimationResource(BuilderTileEntity animatable) {
		return GenericUtils.ID("animations/builder.animation.json");
	}

	@Override
	public Identifier getModelResource(BuilderTileEntity animateable) {
		return GenericUtils.ID("geo/builder.geo.json");
	}

	@Override
	public Identifier getTextureResource(BuilderTileEntity entity) {
		return GenericUtils.ID("textures/block/builder.png");
	}
}