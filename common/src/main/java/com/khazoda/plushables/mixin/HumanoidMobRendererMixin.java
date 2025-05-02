package com.khazoda.plushables.mixin;

import com.khazoda.plushables.duck.IHumanoidRenderState;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HumanoidMobRenderer.class)
public abstract class HumanoidMobRendererMixin {
  @Inject(
      method = "extractHumanoidRenderState",
      at = @At("RETURN")
  )
  private static void addItemState(LivingEntity entity, HumanoidRenderState reusedState, float partialTick, ItemModelResolver itemModelResolver, CallbackInfo ci) {
    ((IHumanoidRenderState)reusedState).plushables$setMainHandItem(entity.getMainHandItem().copy());
    ((IHumanoidRenderState)reusedState).plushables$setOffHandItem(entity.getOffhandItem().copy());
  }
}
