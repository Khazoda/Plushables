package com.seacroak.plushables.client.renderer.tile;

import com.seacroak.plushables.block.tile.CluckyTileEntity;
import com.seacroak.plushables.client.model.tile.CluckyModel;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class CluckyTileRenderer extends GeoBlockRenderer<CluckyTileEntity> {
	public CluckyTileRenderer() {
		super(new CluckyModel());
	}

	@Override
	public RenderLayer getRenderType(CluckyTileEntity animatable, float partialTicks, MatrixStack stack,
			VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
			Identifier textureLocation) {
		return RenderLayer.getEntityCutout(getTextureResource(animatable));
	}
}
