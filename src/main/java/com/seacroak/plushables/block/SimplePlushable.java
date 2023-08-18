package com.seacroak.plushables.block;

import com.seacroak.plushables.registry.SoundRegistry;
import com.seacroak.plushables.util.VoxelShapeUtils;
import com.seacroak.plushables.networking.ParticlePacketHandler;
import com.seacroak.plushables.networking.PlushablesNetworking;
import com.seacroak.plushables.networking.SoundPacketHandler;
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

import java.util.Random;

public abstract class SimplePlushable extends HorizontalFacingBlock {
  public static Random rand;

  //  Constructor
  public SimplePlushable() {
    super(FabricBlockSettings.create().sounds(BlockSoundGroup.WOOL).strength(0.7f).nonOpaque());
    setDefaultState(this.stateManager.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH));
    rand = new Random();
  }

  // Shift Right Click pickup code
  @Override
  public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {

    if (player.isSneaking()) {
      if(!player.canModifyBlocks()) return ActionResult.CONSUME;
      /* Serverside */
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
  final VoxelShape[] blockShapes = VoxelShapeUtils.calculateBlockShapes(blockShape);

  @Override
  public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
    Direction direction = state.get(FACING);
    return VoxelShapeUtils.getSidedOutlineShape(direction, blockShape, blockShapes);
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
  protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
    builder.add(FACING);
  }

}
