package com.khazoda.plushables.mixin;

import com.khazoda.plushables.duck.IHumanoidRenderState;
import com.khazoda.plushables.item.PlushableBlockItem;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin for modifying humanoid model arm poses when holding plushables.
 * Adjusts arm positions to create a cradling pose when holding plushable
 * items.
 */
@Mixin(HumanoidModel.class)
public class HumanoidModelMixin<T extends HumanoidRenderState> {
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
     * @param renderState The player renderState whose arms are being posed
     * @param ci          Callback info that can be used to cancel the original method
     */
    @Inject(method = {"poseRightArm", "poseLeftArm"}, at = @At("HEAD"), cancellable = true)
    public void poseArms(T renderState, HumanoidModel.ArmPose pose, CallbackInfo ci) {
        ItemStack main = ((IHumanoidRenderState) renderState).plushables$getMainHandItem();
        ItemStack off = ((IHumanoidRenderState) renderState).plushables$getOffHandItem();

        boolean mainPlush = main != null && !main.isEmpty() && main.getItem() instanceof PlushableBlockItem;
        boolean offPlush = off != null && !off.isEmpty() && off.getItem() instanceof PlushableBlockItem;

        if (mainPlush && offPlush) {
            this.rightArm.xRot = -0.5F;
            this.rightArm.yRot = 0.3F;
            this.leftArm.xRot = -0.5F;
            this.leftArm.yRot = -0.3F;
            ci.cancel();
        } else if (mainPlush || offPlush) {
            this.rightArm.xRot = -0.90F;
            this.rightArm.yRot = (float) (-Math.PI / 8);
            this.leftArm.xRot = -0.90F;
            this.leftArm.yRot = (float) (Math.PI / 8);
            ci.cancel();
        }
    }
}