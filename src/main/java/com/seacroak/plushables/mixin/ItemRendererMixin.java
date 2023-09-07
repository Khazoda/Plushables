package com.seacroak.plushables.mixin;

import com.seacroak.plushables.PlushablesMod;
import com.seacroak.plushables.registry.MainRegistry;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {
  @ModifyVariable(method = "renderItem", at = @At(value = "HEAD"), argsOnly = true)
  public BakedModel useCodexModel(BakedModel value, ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
    if (stack.isOf(MainRegistry.CODEX_ITEM.asItem()) && renderMode != ModelTransformationMode.GUI) {
      return ((ItemRendererAccessor) this).plushables$getModels().getModelManager().getModel(new ModelIdentifier(PlushablesMod.MOD_ID, "codex_3d", "inventory"));
    }
    return value;
  }
}