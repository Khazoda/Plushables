package com.seacroak.plushables.block;
import com.seacroak.plushables.registry.SoundRegistry;
import com.seacroak.plushables.util.networking.ParticlePacketHandler;
import com.seacroak.plushables.util.networking.PlushablesNetworking;
import com.seacroak.plushables.util.networking.SoundPacketHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.World;

public class GoldfishBlock extends SimpleInteractablePlushable {
  public GoldfishBlock() {
    super();
  }
  @Override
  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0, 0.125, 0.8125, 0.625, 0.75));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.0625, 0.3125, 0.1875, 0.1875, 0.5, 0.375));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.8125, 0.3125, 0.1875, 0.9375, 0.5, 0.375));

    return shape;
  }

  @Override
  protected ActionResult serverSendEffectPackets(ServerWorld serverWorld, PlayerEntity player, BlockPos pos) {
    SoundPacketHandler.sendPlayerPacketToClients(serverWorld, new SoundPacketHandler.PlayerSoundPacket(player, pos, SoundRegistry.GOLDFISH, 1f));
    ParticlePacketHandler.sendPacketToClients(serverWorld, new ParticlePacketHandler.ParticlePacket
        (player, pos, "minecraft:dolphin", 15, new Vec3d(0, 0.15, 0), 0.05f));
    ParticlePacketHandler.sendPacketToClients(serverWorld, new ParticlePacketHandler.ParticlePacket
        (player, pos, "minecraft:fishing", 15, new Vec3d(0, 0.15, 0), 0.05f));
    return ActionResult.CONSUME;
  }

  @Override
  protected ActionResult clientRunEffects(World world, BlockPos pos) {
    PlushablesNetworking.playSoundOnClient(SoundRegistry.GOLDFISH, world, pos, 1f, 1f);
    PlushablesNetworking.spawnParticlesOnClient(ParticleTypes.DOLPHIN, world, pos, 15, new Vec3d(0, 0.15, 0), 0.05f);
    PlushablesNetworking.spawnParticlesOnClient(ParticleTypes.FISHING, world, pos, 15, new Vec3d(0, 0.15, 0), 0.05f);

    return ActionResult.SUCCESS;
  }
}