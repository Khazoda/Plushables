package com.seacroak.plushables.client.renderer.tile;

import com.seacroak.plushables.block.tile.BuilderTileEntity;
import com.seacroak.plushables.client.model.tile.BuilderModel;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import org.joml.Matrix4f;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.renderer.GeoBlockRenderer;
import software.bernie.geckolib.renderer.GeoRenderer;

public class BuilderTileRenderer extends GeoBlockRenderer<BuilderTileEntity> {
    public BuilderTileRenderer() {
        super(new BuilderModel());
    }

}
