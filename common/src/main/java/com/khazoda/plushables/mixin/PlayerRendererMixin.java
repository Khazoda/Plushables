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

/**
 * == This mixin works in tandem with {@link HumanoidModelMixin} ==
 * <br/>
 * Mixin for modifying player arm poses when holding plushables.
 * Changes the arm pose to CROSSBOW_HOLD when a player is holding a plushable
 * item,
 * creating a modifiable cradling pose.
 */
@Mixin(PlayerRenderer.class)
public class PlayerRendererMixin {
  /**
   * Modifies the arm pose when holding plushable items.
   * Injects at the end of getArmPose to override the pose when holding
   * plushables.
   *
   * @param player The player whose arm pose is being determined
   * @param hand   The hand being checked for items
   * @param ci     Callback info that can return and cancel the original method
   */
  @Inject(method = "getArmPose(Lnet/minecraft/client/player/AbstractClientPlayer;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/client/model/HumanoidModel$ArmPose;", at = @At("TAIL"), cancellable = true)
  private static void cuddlePlushable(AbstractClientPlayer player, InteractionHand hand,
                                      CallbackInfoReturnable<HumanoidModel.ArmPose> ci) {
    ItemStack stack = player.getItemInHand(hand);
    if (stack.getItem() instanceof PlushableBlockItem) {
      ci.setReturnValue(HumanoidModel.ArmPose.CROSSBOW_HOLD);
      ci.cancel();
    }
  }
}