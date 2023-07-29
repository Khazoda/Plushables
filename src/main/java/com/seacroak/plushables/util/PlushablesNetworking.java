package com.seacroak.plushables.util;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import java.util.UUID;

public class PlushablesNetworking {
  public static final Identifier packetID = GenericUtils.ID("plushable_packet");

  public static void sendDataToClients(ServerWorld world, SoundPacket packet) {
    world.getPlayers().forEach(player -> {
      if (player.getUuid() == packet.player)
        return;
      var buf = PacketByteBufs.create();
      packet.write(buf);
      ServerPlayNetworking.send(player, packetID, buf);
    });
  }

  public static class SoundPacket {
    public UUID player;
    public Vec3d pos;

    public SoundPacket(UUID player, Vec3d pos) {
      this.player = player;
      this.pos = pos;

    }

    public SoundPacket(PlayerEntity player, BlockPos pos) {
      this(player.getUuid(), pos.toCenterPos());
    }

    public void write(PacketByteBuf buf) {
      buf.writeUuid(player);
      buf.writeDouble(pos.x);
      buf.writeDouble(pos.y);
      buf.writeDouble(pos.z);
    }

    public static SoundPacket read(PacketByteBuf buf) {
      UUID player = buf.readUuid();
      Vec3d pos = new Vec3d(buf.readDouble(), buf.readDouble(), buf.readDouble());
      return new SoundPacket(player, pos);
    }
  }

  public static void registerGlobalReceiver() {
    /* Registers global packet receiver in MainRegistry.class */
    ServerPlayNetworking.registerGlobalReceiver(PlushablesNetworking.packetID, ((server, player, handler, buf, responseSender) -> {
      var packet = PlushablesNetworking.SoundPacket.read(buf);
      if (packet.player == player.getUuid())
        return;
      server.execute(() -> {
        PlushablesNetworking.sendDataToClients(player.getServerWorld(), packet);
      });
    }));
  }
}