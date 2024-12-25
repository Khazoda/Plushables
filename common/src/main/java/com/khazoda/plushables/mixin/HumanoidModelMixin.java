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

@Mixin(HumanoidModel.class)
public class HumanoidModelMixin {
  @Shadow
  @Final
  public ModelPart rightArm;
  @Shadow
  @Final
  public ModelPart leftArm;

  @Inject(
      method = {"poseRightArm", "poseLeftArm"},
      at = @At("HEAD"),
      cancellable = true
  )
  public void poseArms(LivingEntity entity, CallbackInfo ci) {
    if (entity.getMainHandItem().getItem() instanceof PlushableBlockItem ||
        entity.getOffhandItem().getItem() instanceof PlushableBlockItem) {
      this.rightArm.xRot = -0.90F;
      this.rightArm.yRot = (float) (-Math.PI / 8);
      this.leftArm.xRot = -0.90F;
      this.leftArm.yRot = (float) (Math.PI / 8);
      ci.cancel();
    }
  }
}