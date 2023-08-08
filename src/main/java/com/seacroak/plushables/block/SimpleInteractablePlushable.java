package com.seacroak.plushables.block;

import com.seacroak.plushables.registry.SoundRegistry;
import com.seacroak.plushables.util.VoxelShapeUtils;
import com.seacroak.plushables.util.networking.ParticlePacketHandler;
import com.seacroak.plushables.util.networking.PlushablesNetworking;
import com.seacroak.plushables.util.networking.SoundPacketHandler;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
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
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.Random;

public abstract class SimpleInteractablePlushable extends HorizontalFacingBlock {
  public static Random rand;

  //  Constructor
  public SimpleInteractablePlushable() {
    super(FabricBlockSettings.create().sounds(BlockSoundGroup.WOOL).strength(0.7f).nonOpaque());
    setDefaultState(this.stateManager.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH).with(ON_COOLDOWN, false));
    rand = new Random();
  }

  // Shift Right Click pickup code
  @Override
  public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
    if (player.shouldCancelInteraction()) return ActionResult.PASS;

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

  // Custom breaking particle code
  @Override
  public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
    if (world.isClient) {
      for (int i = 0; i < 5; i++) {
        world.addParticle(ParticleTypes.POOF, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, rand.nextFloat(-0.05f, 0.05f), rand.nextFloat(-0.05f, 0.05f), rand.nextFloat(-0.05f, 0.05f));
        world.addParticle(ParticleTypes.GLOW, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, rand.nextFloat(-0.05f, 0.05f), rand.nextFloat(-0.05f, 0.05f), rand.nextFloat(-0.05f, 0.05f));
      }
    }
    world.addParticle(ParticleTypes.FIREWORK, true, pos.getX(), pos.getY(), pos.getZ(), 0.1, 0.1, 0.1);
    super.onBreak(world, pos, state, player);
  }


  // VoxelShape
  //  Default Shape
  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0, 0, 0, 0.8, 0.8, 0.8));
    return shape;
  }

  final VoxelShape blockShape = getShape();
  final VoxelShape[] blockShapes = {blockShape,
      VoxelShapeUtils.rotateShape(Direction.NORTH, Direction.EAST, blockShape),
      VoxelShapeUtils.rotateShape(Direction.NORTH, Direction.SOUTH, blockShape),
      VoxelShapeUtils.rotateShape(Direction.NORTH, Direction.WEST, blockShape)};

  @Override
  public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
    Direction direction = (Direction) state.get(FACING);

    switch (direction) {
      case NORTH: {
        return blockShapes[0];
      }
      case EAST: {
        return blockShapes[1];
      }
      case SOUTH: {
        return blockShapes[2];

      }
      case WEST: {
        return blockShapes[3];

      }
      default:
        return blockShape;
    }
  }

  /* Interactivity */
  public static final BooleanProperty ON_COOLDOWN = BooleanProperty.of("on_cooldown");

  public void startCooldown(BlockState state, World world, BlockPos pos) {
    world.setBlockState(pos, state.with(ON_COOLDOWN, true), 3);
    this.updateNeighbors(state, world, pos);
    world.scheduleBlockTick(pos, this, 60);
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


  // Render Type
  @Override
  public BlockRenderType getRenderType(BlockState state) {
    return BlockRenderType.MODEL;
  }

  // Initial state upon placing
  @Override
  public BlockState getPlacementState(ItemPlacementContext context) {
    return this.getDefaultState().with(Properties.HORIZONTAL_FACING, context.getHorizontalPlayerFacing().getOpposite());

  }

  // Append initial properties
  @Override
  protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
    builder.add(ON_COOLDOWN).add(FACING);
  }
}
