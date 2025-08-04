package com.khazoda.plushables.mixin;

import com.khazoda.plushables.item.PlushableBlockItem;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * == This mixin works in tandem with {@link PlayerRendererMixin} ==
 * <br/>
 * Mixin for modifying humanoid model arm poses when holding plushables.
 * Adjusts arm positions to create a cradling pose when holding plushable
 * items.
 */
@Mixin(HumanoidModel.class)
public class HumanoidModelMixin {
  /**
   * Reference to the player model's right arm
   */
  @Shadow
  @Final
  public ModelPart rightArm;
  /**
   * Reference to the player model's left arm
   */
  @Shadow
  @Final
  public ModelPart leftArm;

  /**
   * Modifies arm poses when holding plushable items.
   * Injects into both poseRightArm and poseLeftArm methods to create a cradling
   * pose.
   *
   * @param entity The player entity whose arms are being posed
   * @param ci     Callback info that can be used to cancel the original method
   */
  @Inject(method = {"poseRightArm", "poseLeftArm"}, at = @At("HEAD"), cancellable = true)
  public void poseArms(LivingEntity entity, CallbackInfo ci) {
    if (entity.getMainHandItem().getItem() instanceof PlushableBlockItem &&
            entity.getOffhandItem().getItem() instanceof PlushableBlockItem) {
      this.rightArm.xRot = -0.5F;
      this.rightArm.yRot = 0.3F;
      this.leftArm.xRot = -0.5F;
      this.leftArm.yRot = -0.3F;
      ci.cancel();
    } else if (entity.getMainHandItem().getItem() instanceof PlushableBlockItem ||
            entity.getOffhandItem().getItem() instanceof PlushableBlockItem) {
      this.rightArm.xRot = -0.90F;
      this.rightArm.yRot = (float) (-Math.PI / 8);
      this.leftArm.xRot = -0.90F;
      this.leftArm.yRot = (float) (Math.PI / 8);
      ci.cancel();
    }
  }
}