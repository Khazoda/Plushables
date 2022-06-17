package com.seacroak.plushables.client.model.tile;

import com.seacroak.plushables.PlushablesMod;
import com.seacroak.plushables.block.tile.BuilderTileEntity;
import com.seacroak.plushables.block.tile.PenguinTileEntity;

import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

/**
 * @author VoutVouniern Copyright (c) 03.06.2022 Developed by VoutVouniern
 */
public class PenguinModel extends AnimatedGeoModel<PenguinTileEntity> {

	@Override
	public Identifier getAnimationResource(PenguinTileEntity animatable) {
		return new Identifier(PlushablesMod.ModID, "animations/penguin.animation.json");
	}

	@Override
	public Identifier getModelResource(PenguinTileEntity animateable) {
		return new Identifier(PlushablesMod.ModID, "geo/penguin.geo.json");
	}

	@Override
	public Identifier getTextureResource(PenguinTileEntity entity) {
		return new Identifier(PlushablesMod.ModID, "textures/block/penguin.png");
	}
}