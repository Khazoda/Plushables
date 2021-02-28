package net.readycheck.plushables.common.entities.froglin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.audio.Sound;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.readycheck.plushables.common.Plushables;
import org.w3c.dom.Attr;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;

public class FroglinEntity extends CreatureEntity implements IAnimatable {

    private AnimationFactory factory = new AnimationFactory(this);
    ResourceLocation froglin_living = new ResourceLocation(Plushables.MOD_ID, "froglin_living");
    SoundEvent froglin_living_sound = new SoundEvent(froglin_living);

    public FroglinEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
        this.ignoreFrustumCheck = true;
    }

    public static AttributeModifierMap.MutableAttribute returnCustomAttributes() {
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 8.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.4D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 0.0D)
                .createMutableAttribute(net.minecraftforge.common.ForgeMod.SWIM_SPEED.get(), 2.0D);

    }

    //    AI
    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(2, new FleeSunGoal(this, 0.7D));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 0.5D));
        this.goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
    }

    @Override
    protected int getExperiencePoints(PlayerEntity player) {
        return 1 + this.world.rand.nextInt(4);
    }

    // SOUND EVENTS
    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return new SoundEvent(froglin_living);
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return new SoundEvent(froglin_living);
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return new SoundEvent(froglin_living);
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.ENTITY_SLIME_SQUISH_SMALL, 1.0f, 1.0f);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        Boolean entityMoving = false;
        if (event.getLimbSwingAmount() > -0.15F && event.getLimbSwingAmount() < 0.15F) {
            entityMoving = false;
            if (event.getController().getAnimationState() == AnimationState.Stopped) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.froglin.idle", true));
            }
        } else {
//            entityMoving = true;
//            if (event.getController().getAnimationState() == AnimationState.Stopped) {
                event.getController().setAnimation((new AnimationBuilder()).addAnimation("animation.froglin.squish", false));
//            }
        }

        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}
