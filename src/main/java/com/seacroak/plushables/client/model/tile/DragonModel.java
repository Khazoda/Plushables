package com.seacroak.plushables.client.model.tile;

import com.seacroak.plushables.block.tile.CluckyTileEntity;
import com.seacroak.plushables.block.tile.DragonTileEntity;
import com.seacroak.plushables.util.GenericUtils;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class DragonModel extends GeoModel<DragonTileEntity> {

	@Override
	public Identifier getAnimationResource(DragonTileEntity animatable) {
		return GenericUtils.ID("animations/dragon.animation.json");
	}

	@Override
	public Identifier getModelResource(DragonTileEntity animateable) {
		return GenericUtils.ID("geo/dragon.geo.json");
	}

	@Override
	public Identifier getTextureResource(DragonTileEntity entity) {
    if(entity.getVariant() == 1) {
      return GenericUtils.ID("textures/block/toothless_texture.png");
    } else {
      return GenericUtils.ID("textures/block/lightfury_texture.png");
    }
	}

	@Override
	public RenderLayer getRenderType(DragonTileEntity animatable, Identifier texture) {
		return RenderLayer.getEntityCutout(getTextureResource(animatable));
	}
}