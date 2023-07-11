package com.seacroak.plushables.client.model.tile;

import com.seacroak.plushables.block.tile.BuilderTileEntity;
import com.seacroak.plushables.util.GenericUtils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class BuilderModel extends GeoModel<BuilderTileEntity> {

    @Override
    public ResourceLocation getAnimationResource(BuilderTileEntity animatable) {
        return GenericUtils.ID("animations/builder.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(BuilderTileEntity animateable) {
        return GenericUtils.ID("geo/builder.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BuilderTileEntity entity) {
        return GenericUtils.ID("textures/block/builder.png");
    }

    @Override
    public RenderType getRenderType(BuilderTileEntity animatable, ResourceLocation texture) {
        return RenderType.entityTranslucent(getTextureResource(animatable));
    }
}