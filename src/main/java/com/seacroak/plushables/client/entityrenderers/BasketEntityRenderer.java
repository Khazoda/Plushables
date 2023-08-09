package com.seacroak.plushables.client.entityrenderers;

import com.seacroak.plushables.block.tile.BasketBlockEntity;
import net.minecraft.block.CampfireBlock;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Direction;

public class BasketEntityRenderer implements BlockEntityRenderer<BasketBlockEntity> {
  private final ItemRenderer itemRenderer;

  public BasketEntityRenderer(BlockEntityRendererFactory.Context ctx) {
    this.itemRenderer = ctx.getItemRenderer();
  }

  public void render(BasketBlockEntity blockEntity, float f, MatrixStack stack, VertexConsumerProvider vertexConsumerProvider, int i, int j) {
    Direction direction = (Direction) blockEntity.getCachedState().get(CampfireBlock.FACING);
    DefaultedList<ItemStack> hats = blockEntity.getPlushieStack();
    int seed = (int) blockEntity.getPos().asLong();

//    System.out.println(hats.get(0));
    if (hats.get(0) != ItemStack.EMPTY) {
      stack.push();
      stack.translate(0, 0.5, 0);
      this.itemRenderer.renderItem(hats.get(0), ModelTransformationMode.HEAD, i, j, stack, vertexConsumerProvider, blockEntity.getWorld(), seed);
      stack.pop();
    }
    if (hats.get(1) != ItemStack.EMPTY) {
      stack.push();
      stack.translate(1, 0.5, 0);
      this.itemRenderer.renderItem(hats.get(1), ModelTransformationMode.HEAD, i, j, stack, vertexConsumerProvider, blockEntity.getWorld(), seed + 1);
      stack.pop();
    }

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