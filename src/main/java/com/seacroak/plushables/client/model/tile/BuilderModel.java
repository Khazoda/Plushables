package com.seacroak.plushables.client.model.tile;

import com.seacroak.plushables.block.tile.BuilderTileEntity;
import com.seacroak.plushables.util.GenericUtils;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class BuilderModel extends GeoModel<BuilderTileEntity> {

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
        return GenericUtils.ID("textures/block/builder_texture.png");
    }

    @Override
    public RenderLayer getRenderType(BuilderTileEntity animatable, Identifier texture) {
        return RenderLayer.getEntityCutout(getTextureResource(animatable));
    }
}