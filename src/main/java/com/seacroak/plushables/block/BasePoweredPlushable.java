package com.seacroak.plushables.block;

import com.seacroak.plushables.block.tile.PoweredBlockEntity;
import com.seacroak.plushables.networking.AnimationPacketHandler;
import com.seacroak.plushables.networking.PlushablesNetworking;
import com.seacroak.plushables.networking.SoundPacketHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.LocalRandom;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.core.animation.AnimationController;

public abstract class BasePoweredPlushable<T extends PoweredBlockEntity> extends BasePlushable implements BlockEntityProvider {
  //  Fields
  public static LocalRandom randPitch;
  public BlockEntity blockEntityReference;
  protected int cooldownPeriod = 60;

  /* Subclass of powered block entity */
  private final Class<T> BE_TYPE;
  private final SoundEvent soundEffect;

  /* float values default to 0.0f */
  private float pitch;

  //  Constructor with constant pitch
  public BasePoweredPlushable(Class<T> t, SoundEvent sound_effect_in, float pitch_in) {
    super();
    setDefaultState(this.stateManager.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH).with(WATERLOGGED, false).with(ON_COOLDOWN, false));
    randPitch = new LocalRandom(100);
    blockEntityReference = null;
    this.BE_TYPE = t;
    this.soundEffect = sound_effect_in;
    this.pitch = pitch_in;
  }

  //  Constructor for random pitch
  public BasePoweredPlushable(Class<T> t, SoundEvent sound_effect_in) {
    super();
    setDefaultState(this.stateManager.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH).with(WATERLOGGED, false).with(ON_COOLDOWN, false));
    randPitch = new LocalRandom(100);
    blockEntityReference = null;
    this.BE_TYPE = t;
    this.soundEffect = sound_effect_in;
  }

  // Shift Right Click pickup code
  @Override
  public ActionResult onUse(BlockState state, World world, BlockPos pos,
                            PlayerEntity player, Hand hand, BlockHitResult hit) {
    if (!player.isSneaking()) {
      if (pitch == 0.0f) {
        pitch = 0.7f + randPitch.nextFloat() / 2;
      }
      BlockEntity blockEntity = world.getBlockEntity(pos);

      if (!(BE_TYPE.isInstance(blockEntity))) return ActionResult.CONSUME;

      @SuppressWarnings("unchecked")
      T typedBlockEntity = (T) blockEntity;
      if (world instanceof ServerWorld serverWorld) {
        if (state.get(ON_COOLDOWN)) return ActionResult.CONSUME;
        this.startCooldown(state, world, pos);
        world.emitGameEvent(player, GameEvent.BLOCK_ACTIVATE, pos);
        if (!typedBlockEntity.shouldAnimate()) {
          /* Server: Send sound & animation packets to clients*/
          SoundPacketHandler.sendPlayerPacketToClients(serverWorld, new SoundPacketHandler.PlayerSoundPacket(player, pos, soundEffect, pitch));
          AnimationPacketHandler.sendPacketToClients(serverWorld, new AnimationPacketHandler.AnimationPacket(player, pos, true, "interaction"));
        }
        return ActionResult.CONSUME;
      }
      if (world.isClient) {
        if (!state.get(ON_COOLDOWN) && typedBlockEntity.interactionController.getAnimationState() == AnimationController.State.STOPPED) {
          typedBlockEntity.shouldAnimate(true);
          /* Client: Play animation & sound */
          PlushablesNetworking.playAnimationOnClient(true, world, pos, "interaction");
          PlushablesNetworking.playSoundOnClient(soundEffect, world, pos, 1f, pitch);
          return ActionResult.SUCCESS;
        }
        return ActionResult.CONSUME;
      }
    }
    return super.onUse(state, world, pos, player, hand, hit);
  }

  // Render Type
  @Override
  public BlockRenderType getRenderType(BlockState state) {
    return BlockRenderType.ENTITYBLOCK_ANIMATED;
  }

  @Nullable
  @Override
  public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
    return null;
  }


  /* Interactivity */
  public static final BooleanProperty ON_COOLDOWN = BooleanProperty.of("on_cooldown");

  public void startCooldown(BlockState state, World world, BlockPos pos) {
    world.setBlockState(pos, state.with(ON_COOLDOWN, true), 3);
    this.updateNeighbors(state, world, pos);
    world.scheduleBlockTick(pos, this, cooldownPeriod);
  }

  public void scheduledTick(BlockState state, ServerWorld world, BlockPos
      pos, net.minecraft.util.math.random.Random random) {
    world.setBlockState(pos, state.with(ON_COOLDOWN, false), 3);
    this.updateNeighbors(state, world, pos);
  }

  private void updateNeighbors(BlockState state, World world, BlockPos pos) {
    world.updateNeighborsAlways(pos, this);
  }

  @Override
  protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
    builder.add(ON_COOLDOWN).add(FACING).add(WATERLOGGED);
  }

}
