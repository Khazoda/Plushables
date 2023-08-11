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
import net.minecraft.item.Items;
import net.minecraft.util.math.RotationAxis;

import java.util.Objects;

import static com.seacroak.plushables.block.tile.BasketBlockEntity.max_stack_size;

public class BasketEntityRenderer implements BlockEntityRenderer<BasketBlockEntity> {
  private final ItemRenderer itemRenderer;

  public BasketEntityRenderer(BlockEntityRendererFactory.Context context) {
    this.itemRenderer = context.getItemRenderer();
  }

  private float genSeed(int index, int[] seeds) {
    return (float) seeds[index] / 100;
  }

  @Override
  public void render(BasketBlockEntity be, float tickDelta, MatrixStack stack, VertexConsumerProvider vertexConsumers, int light, int overlay) {
    ItemStack[] plushies = be.getPlushStack();
    int[] seeds = be.getSeeds();
    int lightAbove = WorldRenderer.getLightmapCoordinates(Objects.requireNonNull(be.getWorld()), be.getPos().up());
    int startIndex = 0;

    /* If basket is empty, cancel method call */
    if (plushies[0].isOf(Items.AIR)) return;
    /* Culls bottom 4 plushies when the basket is full */
    if (plushies[7].isOf(Items.AIR)) startIndex = 0;
    else startIndex = 4;

    stack.push();
    stack.translate(0.5F, 0.25f, 0.5F);
    stack.scale(0.5f, 0.5f, 0.5f);
    for (int i = startIndex; i < max_stack_size; i++) {
      stack.push();
      if (i % 4 == 0) {
        stack.translate(0.3f, i * 0.19f, 0.3f);
      } else if (i % 4 == 1) {
        stack.translate(-0.3f, i * 0.19f, -0.3f);
      } else if (i % 4 == 2) {
        stack.translate(-0.3f, i * 0.19f, 0.3f);
      } else {
        stack.translate(0.3f, i * 0.19f, -0.3f);
      }
      stack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(genSeed(i, seeds) * 360f));
      BakedModel bakedModel = itemRenderer.getModels().getModel(plushies[i]);
      itemRenderer.renderItem(plushies[i], ModelTransformationMode.GUI, true, stack, vertexConsumers, lightAbove, OverlayTexture.DEFAULT_UV, bakedModel);
      stack.pop();
    }
    stack.pop();
  }
}
