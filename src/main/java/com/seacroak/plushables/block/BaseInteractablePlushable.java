package com.seacroak.plushables.block;

import com.seacroak.plushables.networking.ParticlePacketHandler;
import com.seacroak.plushables.networking.PlushablesNetworking;
import com.seacroak.plushables.networking.SoundPacketHandler;
import com.seacroak.plushables.registry.assets.SoundRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public abstract class BaseInteractablePlushable extends BasePlushable {
  protected int cooldownPeriod = 60;

  //  Constructors
  public BaseInteractablePlushable(Settings settings) {
    super(settings);
    setDefaultState(this.stateManager.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH).with(WATERLOGGED,false).with(ON_COOLDOWN, false));
  }

  public BaseInteractablePlushable() {
    super();
    setDefaultState(this.stateManager.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH).with(WATERLOGGED,false).with(ON_COOLDOWN, false));
  }

  // Shift Right Click pickup code
  @Override
  public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
    if (!player.isSneaking()) {
      if (state.get(ON_COOLDOWN)) return ActionResult.CONSUME;
      this.startCooldown(state, world, pos);
      world.emitGameEvent(player, GameEvent.BLOCK_ACTIVATE, pos);
      if (world instanceof ServerWorld serverWorld) return this.serverSendEffectPackets(serverWorld, player, pos);
      if (world.isClient) return this.clientRunEffects(world, pos);
    }
    if (player.isSneaking()) {
      /* Serverside */
      if (!player.canModifyBlocks()) return ActionResult.CONSUME;
      if (world instanceof ServerWorld serverWorld) {
        SoundPacketHandler.sendPlayerPacketToClients(serverWorld, new SoundPacketHandler.PlayerSoundPacket(player, pos, SoundRegistry.PLUSHABLE_POP, 1f));
        SoundPacketHandler.sendPlayerPacketToClients(serverWorld, new SoundPacketHandler.PlayerSoundPacket(player, pos, SoundEvents.BLOCK_WOOL_HIT, 1f));
        ParticlePacketHandler.sendPacketToClients(serverWorld, new ParticlePacketHandler.ParticlePacket(player, pos, "minecraft:poof", 5, new Vec3d(0, 0, 0), 0.05f));
        ParticlePacketHandler.sendPacketToClients(serverWorld, new ParticlePacketHandler.ParticlePacket(player, pos, "minecraft:glow", 5, new Vec3d(0, 0, 0), 0.05f));

        ItemScatterer.spawn(world, pos, DefaultedList.ofSize(1, new ItemStack(this)));
        world.updateComparators(pos, this);
        world.removeBlock(pos, false);
        return ActionResult.CONSUME;

        /* Clientside */
      } else if (world.isClient) {
        PlushablesNetworking.playSoundOnClient(SoundRegistry.PLUSHABLE_POP, world, pos, 1f, 1f);
        PlushablesNetworking.playSoundOnClient(SoundEvents.BLOCK_WOOL_HIT, world, pos, 1f, 1f);
        PlushablesNetworking.spawnParticlesOnClient(ParticleTypes.POOF, world, pos, 5, new Vec3d(0, 0, 0), 0.05f);
        PlushablesNetworking.spawnParticlesOnClient(ParticleTypes.GLOW, world, pos, 5, new Vec3d(0, 0, 0), 0.05f);
        return ActionResult.SUCCESS;
      }
    }
    return ActionResult.PASS;
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

  /* Override these two methods to send specific sound and particle packets */
  /* Every sound/particle used needs to be mirrored to each method */
  protected ActionResult serverSendEffectPackets(ServerWorld serverWorld, PlayerEntity player, BlockPos pos) {
    SoundPacketHandler.sendPlayerPacketToClients(serverWorld, new SoundPacketHandler.PlayerSoundPacket(player, pos, SoundRegistry.BUILDER_DING, 1f));
    ParticlePacketHandler.sendPacketToClients(serverWorld, new ParticlePacketHandler.ParticlePacket(player, pos, "minecraft:poof", 10, new Vec3d(0, 0.5, 0), 0f));
    return ActionResult.CONSUME;
  }

  protected ActionResult clientRunEffects(World world, BlockPos pos) {
    PlushablesNetworking.playSoundOnClient(SoundRegistry.BUILDER_DING, world, pos, 1f, 1f);
    PlushablesNetworking.spawnParticlesOnClient(ParticleTypes.POOF, world, pos, 1, new Vec3d(0, 0.5, 0), 0);
    return ActionResult.SUCCESS;
  }

  // Append initial properties
  @Override
  protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
    builder.add(ON_COOLDOWN).add(FACING).add(WATERLOGGED);
  }
}
