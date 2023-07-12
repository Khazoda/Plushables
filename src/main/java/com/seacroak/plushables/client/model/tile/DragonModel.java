package com.seacroak.plushables.client.model.tile;

import com.seacroak.plushables.block.tile.DragonTileEntity;
import com.seacroak.plushables.util.GenericUtils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class DragonModel extends GeoModel<DragonTileEntity> {

	@Override
	public ResourceLocation getAnimationResource(DragonTileEntity animatable) {
		return GenericUtils.ID("animations/dragon.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(DragonTileEntity animateable) {
		return GenericUtils.ID("geo/dragon.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(DragonTileEntity entity) {
    if(entity.getVariant() == 1) {
      return GenericUtils.ID("textures/block/toothless_texture.png");
    } else {
      return GenericUtils.ID("textures/block/lightfury_texture.png");
    }
	}

	@Override
	public RenderType getRenderType(DragonTileEntity animatable, ResourceLocation texture) {
		return RenderType.entityCutout(getTextureResource(animatable));
	}
}