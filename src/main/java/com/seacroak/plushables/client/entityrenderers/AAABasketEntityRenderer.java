package com.seacroak.plushables.client.entityrenderers;

import com.seacroak.plushables.block.tile.AAABasketBlockEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.RotationAxis;

public class AAABasketEntityRenderer implements BlockEntityRenderer<AAABasketBlockEntity> {
  private final ItemRenderer itemRenderer;

  public AAABasketEntityRenderer(BlockEntityRendererFactory.Context ctx) {
    this.itemRenderer = ctx.getItemRenderer();
  }

  public void render(AAABasketBlockEntity blockEntity, float f, MatrixStack stack, VertexConsumerProvider vertexConsumerProvider, int i, int j) {
    if (blockEntity.isEmpty()) return;
    int seed = (int) blockEntity.getPos().asLong();
    DefaultedList<ItemStack> plushieStack = blockEntity.getPlushieStack();
    stack.push();
    stack.scale(0.5f, 0.5f, 0.5f);

    for (int x = 0; x < plushieStack.size(); ++x) {
      ItemStack plushie = plushieStack.get(x);
      if (plushie != ItemStack.EMPTY) {
        stack.translate(0.5F, 0.44921875F, 0.5F);
        stack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(x * 90));
        stack.translate(-0.3125F, -0.3125F, 0.0F);

        BakedModel bakedModel = itemRenderer.getModels().getModel(plushie);
        itemRenderer.renderItem(plushie, ModelTransformationMode.GROUND, true, stack, vertexConsumerProvider, i, OverlayTexture.DEFAULT_UV, bakedModel);

      }
    }

    stack.pop();

//    Direction direction = (Direction) blockEntity.getCachedState().get(CampfireBlock.FACING);
//      if (leftHat != ItemStack.EMPTY) {
//        matrixStack.push();
//        matrixStack.translate(0.5F, 0.44921875F, 0.5F);
//        Direction direction2 = Direction.fromHorizontal((l + direction.getHorizontal()) % 4);
//        float g = -direction2.asRotation();
//        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(g));
//        matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90.0F));
//        matrixStack.translate(-0.3125F, -0.3125F, 0.0F);
//        matrixStack.scale(0.375F, 0.375F, 0.375F);
//        this.itemRenderer.renderItem(itemStack, ModelTransformationMode.FIXED, i, j, matrixStack, vertexConsumerProvider, blockEntity.getWorld(), k + l);
//        matrixStack.pop();
//      }
//    }

  }
}