package com.seacroak.plushables.client.renderer.tile;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.seacroak.plushables.block.tile.BuilderTileEntity;
import com.seacroak.plushables.client.model.tile.BuilderModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.CampfireBlock;
import org.joml.Quaternionf;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class BuilderTileRenderer extends GeoBlockRenderer<BuilderTileEntity> {

  private static final float SIZE = 0.375F;
  private final ItemRenderer itemRenderer;

  public BuilderTileRenderer(BlockEntityRendererProvider.Context context) {
    super(new BuilderModel());
    this.itemRenderer = context.getItemRenderer();
  }

  @Override
  public void actuallyRender(PoseStack poseStack, BuilderTileEntity blockEntity, BakedGeoModel model, RenderType renderType, MultiBufferSource buffer, VertexConsumer vertexConsumer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    super.actuallyRender(poseStack, animatable, model, renderType, buffer, vertexConsumer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);

    Direction direction = blockEntity.getBlockState().getValue(CampfireBlock.FACING);
    NonNullList<ItemStack> nonnulllist = blockEntity.getOutput();
    int i = (int) blockEntity.getBlockPos().asLong();

    for (int j = 0; j < nonnulllist.size(); ++j) {
      ItemStack itemstack = nonnulllist.get(j);
      if (itemstack != ItemStack.EMPTY) {
        poseStack.pushPose();
        poseStack.translate(0.5f, 0.35f, 0.45f);
        /* Input Wool */
        if (j == 1) {
          poseStack.scale(1f, 1f, 1f);
        }
        /* Input Item */
        if (j == 0) {
          poseStack.mulPose(Axis.YP.rotationDegrees(-90.0F));
          poseStack.translate(0f, 0f, -0.35f);
          poseStack.scale(0.7f, 0.7f, 0.7f);
        }
        /* Input Heart */
        if (j == 2) {
          poseStack.mulPose(Axis.YP.rotationDegrees(90.0F));
          poseStack.translate(0f, 0f, -0.35f);
          poseStack.scale(0.7f, 0.7f, 0.7f);
        }
        /* Output Plushable */
        if (j == 3) {
          poseStack.translate(0f, 0.9f, 0f);
          poseStack.scale(1f, 1f, 1f);
        }
        this.itemRenderer.renderStatic(itemstack, ItemDisplayContext.FIXED, packedLight, packedOverlay, poseStack, buffer, blockEntity.getLevel(), i + j);
        poseStack.popPose();
//                Direction direction1 = Direction.from2DDataValue((j + direction.get2DDataValue()) % 4);
//                float f = -direction1.toYRot();
//                poseStack.mulPose(Axis.YP.rotationDegrees(f));
//                poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
//                poseStack.translate(-0.3125F, -0.3125F, 0.0F);
//                poseStack.scale(0.375F, 0.375F, 0.375F);

      }
    }
  }
}
