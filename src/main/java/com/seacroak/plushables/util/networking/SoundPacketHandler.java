package com.seacroak.plushables.util.networking;

import com.seacroak.plushables.util.GenericUtils;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import java.util.UUID;

public class SoundPacketHandler {
  public static final Identifier PACKET_ID = GenericUtils.ID("plushable_sound_packet");

  /* Sound Packet*/
  public static void sendPacketToClients(ServerWorld world, SoundPacket packet) {
    world.getPlayers().forEach(player -> {
      if (player.getUuid() == packet.player)
        return;
      var buf = PacketByteBufs.create();
      packet.write(buf);
      ServerPlayNetworking.send(player, PACKET_ID, buf);
    });
  }

  public static class SoundPacket {
    public UUID player;
    public Vec3d pos;
    public String soundIdentifier;

    public SoundPacket(UUID player, Vec3d pos,String soundIdentifier) {
      this.player = player;
      this.pos = pos;
      this.soundIdentifier = soundIdentifier;
    }

    public SoundPacket(PlayerEntity player, BlockPos pos, SoundEvent soundEvent) {
      this(player.getUuid(), pos.toCenterPos(),soundEvent.getId().toString());
    }

    public void write(PacketByteBuf buf) {
      buf.writeUuid(player);
      buf.writeDouble(pos.x);
      buf.writeDouble(pos.y);
      buf.writeDouble(pos.z);
      buf.writeString(soundIdentifier);
    }

    public static SoundPacket read(PacketByteBuf buf) {
      UUID player = buf.readUuid();
      Vec3d pos = new Vec3d(buf.readDouble(), buf.readDouble(), buf.readDouble());
      String soundIdentifier = buf.readString();
      return new SoundPacket(player, pos,soundIdentifier);
    }
  }
}
