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
import net.minecraft.util.math.RotationAxis;

public class BasketEntityRenderer implements BlockEntityRenderer<BasketBlockEntity> {
  private final ItemRenderer itemRenderer;

  public BasketEntityRenderer(BlockEntityRendererFactory.Context context) {
    this.itemRenderer = context.getItemRenderer();
  }

  private float genSeed(int index,int[] seeds) {
    return (float) seeds[index] / 10;
  }

  @Override
  public void render(BasketBlockEntity be, float tickDelta, MatrixStack stack, VertexConsumerProvider vertexConsumers, int light, int overlay) {
    ItemStack[] plushies = be.getPlushStack();
    int[] seeds = be.getSeeds();

    if (plushies[0] == ItemStack.EMPTY) return;

    stack.push();
    stack.translate(0.5F, 0f, 0.5F);
    stack.scale(0.5f, 0.5f, 0.5f);
    for (int i = 0; i < 4; i++) {
      stack.push();
      stack.translate(0f, i*0.3f, 0f);
      stack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(25f+(i*90f - genSeed(i,seeds)) + genSeed(i,seeds) * 5f));
      stack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(genSeed(i,seeds) * 180f));
      stack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(genSeed(i,seeds)* -180f));

      stack.translate(-0f, 0.35f, 0f);
      stack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(genSeed(i,seeds) * 5 * 5f));

      int lightAbove = WorldRenderer.getLightmapCoordinates(be.getWorld(), be.getPos().up());
      BakedModel bakedModel = itemRenderer.getModels().getModel(plushies[i]);
      itemRenderer.renderItem(plushies[i], ModelTransformationMode.GUI, true, stack, vertexConsumers, lightAbove, OverlayTexture.DEFAULT_UV, bakedModel);
      stack.pop();
//      stack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(genFloat(i+1,0f,360f)));
//      stack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(genFloat(i+1,0f,75f)));

    }
    stack.pop();
  }

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
