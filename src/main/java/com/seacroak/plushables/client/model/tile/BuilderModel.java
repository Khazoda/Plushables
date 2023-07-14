package com.seacroak.plushables.client.model.tile;

import com.seacroak.plushables.block.tile.BuilderTileEntity;
import com.seacroak.plushables.block.tile.NewBuilderTileEntity;
import com.seacroak.plushables.util.GenericUtils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class BuilderModel extends GeoModel<NewBuilderTileEntity> {

    @Override
    public ResourceLocation getAnimationResource(NewBuilderTileEntity animatable) {
        return GenericUtils.ID("animations/builder.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(NewBuilderTileEntity animateable) {
        return GenericUtils.ID("geo/builder.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(NewBuilderTileEntity entity) {
        return GenericUtils.ID("textures/block/builder.png");
    }

    @Override
    public RenderType getRenderType(NewBuilderTileEntity animatable, ResourceLocation texture) {
        return RenderType.entityTranslucent(getTextureResource(animatable));
    }
}