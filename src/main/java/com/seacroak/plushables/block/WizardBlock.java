package com.seacroak.plushables.block;

import com.seacroak.plushables.registry.SoundRegistry;
import com.seacroak.plushables.util.networking.ParticlePacketHandler;
import com.seacroak.plushables.util.networking.PlushablesNetworking;
import com.seacroak.plushables.util.networking.SoundPacketHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.World;

import java.util.Timer;
import java.util.TimerTask;


public class WizardBlock extends SimplePlushable {
  public static final BooleanProperty ON_COOLDOWN = BooleanProperty.of("on_cooldown");
  Timer timer = null;
  TimerTask timerTask = null;

  public WizardBlock() {
    super();
    setDefaultState(this.getStateManager().getDefaultState().with(ON_COOLDOWN, false));
  }

  public Timer registerTimer(World world, BlockPos pos, BlockState state) {
    Timer timer = new Timer();
    this.timerTask = new TimerTask() {
      @Override
      public void run() {
        world.setBlockState(pos, state.with(ON_COOLDOWN, false));
        destroyTimer();
      }
    };
    return timer;
  }

  public void destroyTimer() {
    this.timer = null;
    this.timerTask = null;
  }


  @Override
  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0, 0.25, 0.75, 0.640625, 0.625));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.375, 0.9375, 0.15625, 0.625, 1, 0.5));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.3125, 0.625, 0.15625, 0.6875, 0.9375, 0.5625));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.421875, 0.296875, 0.21875, 0.578125, 0.4375, 0.25));

    return shape;
  }

  //   Shift Right Click pickup code
  @Override
  public ActionResult onUse(BlockState state, World world, BlockPos pos,
                            PlayerEntity player, Hand hand, BlockHitResult hit) {

    if (timer == null) this.timer = registerTimer(world, pos, state);
    // Injects superclass method
    super.onUse(state, world, pos, player, hand, hit);
    if (player.shouldCancelInteraction()) return ActionResult.PASS;
    if (!player.isSneaking()) {
      /* Only play interaction sounds and animations if block isn't on cooldown */
      if (!world.getBlockState(pos).get(ON_COOLDOWN)) {
        world.setBlockState(pos, state.with(ON_COOLDOWN, true));
        /* Run Cooldown Timer */

        timer.schedule(this.timerTask, 3000);
        if (world instanceof ServerWorld serverWorld) {
          SoundPacketHandler.sendPlayerPacketToClients(serverWorld, new SoundPacketHandler.PlayerSoundPacket(player, pos, SoundRegistry.SWMG, 1f));
          ParticlePacketHandler.sendPacketToClients(serverWorld, new ParticlePacketHandler.ParticlePacket
              (player, pos, "minecraft:note", 1, new Vec3d(0, 0.5, 0), 0f));
          ParticlePacketHandler.sendPacketToClients(serverWorld, new ParticlePacketHandler.ParticlePacket
              (player, pos, "minecraft:glow", 5, new Vec3d(0, 0, 0), 0.05f));

          return ActionResult.CONSUME;

        } else {
          PlushablesNetworking.playSoundOnClient(SoundRegistry.SWMG, world, pos, 1f, 1f);
          PlushablesNetworking.spawnParticlesOnClient(ParticleTypes.NOTE, world, pos, 1, new Vec3d(0, 0.5, 0), 0);
          PlushablesNetworking.spawnParticlesOnClient(ParticleTypes.GLOW, world, pos, 5, new Vec3d(0, 0, 0), 0.05f);
          return ActionResult.SUCCESS;
        }
      }
    }
    return ActionResult.PASS;
  }

  @Override
  protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
    builder.add(ON_COOLDOWN).add(FACING);
  }
}
