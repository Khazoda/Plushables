package com.seacroak.plushables.client.model.tile;

import com.seacroak.plushables.PlushablesMod;
import com.seacroak.plushables.block.tile.BuilderTileEntity;

import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

/**
 * @author VoutVouniern Copyright (c) 03.06.2022 Developed by VoutVouniern
 */
public class BuilderModel extends AnimatedGeoModel<BuilderTileEntity> {

	@Override
	public Identifier getAnimationResource(BuilderTileEntity animatable) {
		return new Identifier(PlushablesMod.MOD_ID, "animations/builder.animation.json");
	}

	@Override
	public Identifier getModelResource(BuilderTileEntity animateable) {
		return new Identifier(PlushablesMod.MOD_ID, "geo/builder.geo.json");
	}

	@Override
	public Identifier getTextureResource(BuilderTileEntity entity) {
		return new Identifier(PlushablesMod.MOD_ID, "textures/block/builder.png");
	}
}