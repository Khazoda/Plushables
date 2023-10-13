package com.seacroak.plushables.networking;

import com.seacroak.plushables.util.GenericUtils;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import java.util.UUID;

public class ParticlePacketHandler {
  public static final Identifier PACKET_ID = GenericUtils.ID("plushable_particle_packet");

  /* Particle Packet*/
  public static void sendPacketToClients(ServerWorld world, ParticlePacket packet) {
    BlockPos builderPos = new BlockPos((int) packet.pos.x, (int) packet.pos.y, (int) packet.pos.z);
    /* Iterate through players that can see sound event emitter */
    PlayerLookup.tracking(world, builderPos).forEach(player -> {
      if (player.getUuid() == packet.player)
        return;
      var buf = PacketByteBufs.create();
      packet.write(buf);
      ServerPlayNetworking.send(player, PACKET_ID, buf);
    });
  }

  public static class ParticlePacket {
    public UUID player;
    public Vec3d pos;
    public String particleIdentifier;
    public int particleCount;
    public Vec3d offset;
    public float spread;

    public ParticlePacket(UUID player, Vec3d pos, String particleIdentifier, int particleCount, Vec3d offset, float spread) {
      this.player = player;
      this.pos = pos;
      this.particleIdentifier = particleIdentifier;
      this.particleCount = particleCount;
      this.offset = offset;
      this.spread = spread;
    }

    public ParticlePacket(PlayerEntity player, BlockPos pos, String particleIdentifier, int particleCount, Vec3d offset, float spread) {
      this(player.getUuid(), pos.toCenterPos(), particleIdentifier, particleCount, offset, spread);
    }

    public void write(PacketByteBuf buf) {
      buf.writeUuid(player);
      buf.writeDouble(pos.x);
      buf.writeDouble(pos.y);
      buf.writeDouble(pos.z);
      buf.writeString(particleIdentifier);
      buf.writeInt(particleCount);
      buf.writeDouble(offset.x);
      buf.writeDouble(offset.y);
      buf.writeDouble(offset.z);
      buf.writeFloat(spread);
    }

    public static ParticlePacket read(PacketByteBuf buf) {
      UUID player = buf.readUuid();
      Vec3d pos = new Vec3d(buf.readDouble(), buf.readDouble(), buf.readDouble());
      String particleIdentifier = buf.readString();
      int particleCount = buf.readInt();
      Vec3d offset = new Vec3d(buf.readDouble(), buf.readDouble(), buf.readDouble());
      float spread = buf.readFloat();
      return new ParticlePacket(player, pos, particleIdentifier, particleCount, offset, spread);
    }
  }
}
