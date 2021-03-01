package net.readycheck.plushables.common.blocks.recycler;

import com.sun.org.apache.xpath.internal.operations.Bool;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.ByteNBT;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.model.ModelDataManager;
import net.readycheck.plushables.common.Registration;
import net.readycheck.plushables.common.interfaces.IAnimationListener;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;

public class RecyclerTileEntity extends TileEntity implements IAnimatable, ITickableTileEntity, IAnimationListener {
    private static final Blocks validBlocks = null;
    private AnimationFactory factory = new AnimationFactory(this);
    BlockState blockState;
    private Boolean isActive = false;
    private Boolean isSwitchingOn;


    public RecyclerTileEntity() {
        super(Registration.RECYCLER_TILE.get());
        this.isSwitchingOn = false;
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController(this, "spinController", 1, this::loopPredicate));
        animationData.addAnimationController(new AnimationController(this, "leverController", 1, this::leverPredicate));

    }

    @Override
    public void startAnimation(boolean arg) {
        try {
            AnimationController spinController = this.factory.getOrCreateAnimationData(this.hashCode()).getAnimationControllers().get("spinController");
            AnimationController leverController = this.factory.getOrCreateAnimationData(this.hashCode()).getAnimationControllers().get("leverController");

            spinController.markNeedsReload();
            spinController.setAnimation(new AnimationBuilder().addAnimation("animation.recycler_active.spin", true));
            leverController.markNeedsReload();
            leverController.setAnimation(new AnimationBuilder().addAnimation("animation.recycler_active.turn_on", false));
//            controller.setAnimation(new AnimationBuilder().addAnimation("animation.recycler_active.spin").addAnimation("animation.recycler_active.turn_on"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private <E extends IAnimatable> PlayState loopPredicate(AnimationEvent<E> event) {
//        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.recycler_active.spin", true));
        return PlayState.CONTINUE;

    }

    private <E extends IAnimatable> PlayState leverPredicate(AnimationEvent<E> event) {
//        if (this.isSwitchingOn) {
////            if (event.getController().getAnimationState() == AnimationState.Stopped) {
//            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.recycler_active.turn_on", false));
////            }
//        } else {
////            entityMoving = true;
////            if (event.getController().getAnimationState() == AnimationState.Stopped) {
//            event.getController().setAnimation((new AnimationBuilder()).addAnimation("animation.recycler_active.turn_off", false));
////            }
//        }
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    @Override
    public void tick() {
        if (world.isRemote) {
            return;
        }
        this.blockState = world.getBlockState(pos);
        this.isActive = blockState.get(Recycler.ACTIVE);
        this.isSwitchingOn = blockState.get(Recycler.SWITCHING_ON);
        markDirty();

//        Minecraft.getInstance().player.sendChatMessage("isActive: " + this.isActive);
//        Minecraft.getInstance().player.sendChatMessage("isSwitchingOn: " + this.isSwitchingOn);


    }
}
