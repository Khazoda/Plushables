package com.seacroak.plushables.client.renderer.tile;

import com.seacroak.plushables.block.tile.BuilderTileEntity;
import com.seacroak.plushables.block.tile.PenguinTileEntity;
import com.seacroak.plushables.client.model.tile.BuilderModel;
import com.seacroak.plushables.client.model.tile.PenguinModel;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class PenguinTileRenderer extends GeoBlockRenderer<PenguinTileEntity> {

	public PenguinTileRenderer() {
		super(new PenguinModel());
	}

	@Override
	public RenderLayer getRenderType(PenguinTileEntity animatable, float partialTicks, MatrixStack stack,
			VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
			Identifier textureLocation) {
		return RenderLayer.getEntityTranslucent(getTextureResource(animatable));
	}
}
