package com.seacroak.plushables.client.entityrenderers;

import com.seacroak.plushables.block.tile.BasketBlockEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

public class BasketEntityRenderer implements BlockEntityRenderer<BasketBlockEntity> {
  private final ItemRenderer itemRenderer;

  public BasketEntityRenderer(BlockEntityRendererFactory.Context context) {
    this.itemRenderer = context.getItemRenderer();
  }

  @Override
  public void render(BasketBlockEntity be, float tickDelta, MatrixStack stack, VertexConsumerProvider vertexConsumers, int light, int overlay) {
//    DefaultedList<ItemStack> plushieStack = be.getPlushStack();
    ItemStack[] plushies = be.getPlushStack();

    if (plushies[0] == ItemStack.EMPTY) return;
//    if (plushieStack.get(0) == ItemStack.EMPTY) return;

    stack.push();
    stack.translate(0.5F, 0.5F, 0.5F);

    for (int i = 0; i < 4; i++) {
      stack.translate(0f, i * 0.25F, 0f);

//      ItemStack plushie = plushieStack.get(i);
//      BakedModel bakedModel = itemRenderer.getModels().getModel(plushie);
//      itemRenderer.renderItem(plushie, ModelTransformationMode.GROUND, true, stack, vertexConsumers, i, OverlayTexture.DEFAULT_UV, bakedModel);
      int lightAbove = WorldRenderer.getLightmapCoordinates(be.getWorld(), be.getPos().up());
      BakedModel bakedModel = itemRenderer.getModels().getModel(plushies[i]);
      itemRenderer.renderItem(plushies[i], ModelTransformationMode.GROUND, true, stack, vertexConsumers, lightAbove, OverlayTexture.DEFAULT_UV, bakedModel);

    }
    stack.pop();
  }
}
