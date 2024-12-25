package com.khazoda.plushables.mixin;

import com.khazoda.plushables.item.PlushableBlockItem;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerRenderer.class)
public class PlayerRendererMixin {
  @Inject(
      method = "getArmPose(Lnet/minecraft/client/player/AbstractClientPlayer;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/client/model/HumanoidModel$ArmPose;",
      at = @At("TAIL"),
      cancellable = true
  )
  private static void cuddlePlushable(AbstractClientPlayer player, InteractionHand hand, CallbackInfoReturnable<HumanoidModel.ArmPose> ci) {
    ItemStack stack = player.getItemInHand(hand);
    if (stack.getItem() instanceof PlushableBlockItem) {
      ci.setReturnValue(HumanoidModel.ArmPose.CROSSBOW_HOLD);
      ci.cancel();
    }
  }
}