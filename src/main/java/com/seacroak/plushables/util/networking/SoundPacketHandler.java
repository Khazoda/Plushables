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
  public static final Identifier PACKET_ID_PLAYER = GenericUtils.ID("plushable_sound_packet_with_player");
  public static final Identifier PACKET_ID_NO_PLAYER = GenericUtils.ID("plushable_sound_packet_without_player");

  public static void sendPlayerPacketToClients(ServerWorld world, PlayerSoundPacket packet) {
    world.getPlayers().forEach(player -> {
      if (player.getUuid() == packet.player)
        return;
      var buf = PacketByteBufs.create();
      packet.write(buf);
      ServerPlayNetworking.send(player, PACKET_ID_PLAYER, buf);
    });
  }

  public static void sendNoPlayerPacketToClients(ServerWorld world, NoPlayerSoundPacket packet) {
    world.getPlayers().forEach(player -> {
      var buf = PacketByteBufs.create();
      packet.write(buf);
      ServerPlayNetworking.send(player, PACKET_ID_NO_PLAYER, buf);
    });
  }


  public static class PlayerSoundPacket {
    public UUID player;
    public Vec3d pos;
    public String soundIdentifier;
    public float pitch;

    /* Packet originating from player */
    public PlayerSoundPacket(UUID player, Vec3d pos,String soundIdentifier,float pitch) {
      this.player = player;
      this.pos = pos;
      this.soundIdentifier = soundIdentifier;
      this.pitch = pitch;
    }
    public PlayerSoundPacket(PlayerEntity player, BlockPos pos, SoundEvent soundEvent,float pitch) {
      this(player.getUuid(), pos.toCenterPos(),soundEvent.getId().toString(),pitch);
    }
    public void writeWithPlayer(PacketByteBuf buf) {
      buf.writeUuid(player);
      buf.writeDouble(pos.x);
      buf.writeDouble(pos.y);
      buf.writeDouble(pos.z);
      buf.writeString(soundIdentifier);
      buf.writeFloat(pitch);
    }

    public void write(PacketByteBuf buf) {
      buf.writeUuid(player);
      buf.writeDouble(pos.x);
      buf.writeDouble(pos.y);
      buf.writeDouble(pos.z);
      buf.writeString(soundIdentifier);
      buf.writeFloat(pitch);
    }

    public static PlayerSoundPacket read(PacketByteBuf buf) {
      UUID player = buf.readUuid();
      Vec3d pos = new Vec3d(buf.readDouble(), buf.readDouble(), buf.readDouble());
      String soundIdentifier = buf.readString();
      float pitch = buf.readFloat();
      return new PlayerSoundPacket(player, pos,soundIdentifier,pitch);
    }
  }

  public static class NoPlayerSoundPacket {
    public Vec3d pos;
    public String soundIdentifier;
    public float pitch;

    /* Packet originating from player */
    public NoPlayerSoundPacket(UUID player, Vec3d pos,String soundIdentifier,float pitch) {
      this.pos = pos;
      this.soundIdentifier = soundIdentifier;
      this.pitch = pitch;
    }
    /* Packet originating from server */
    public NoPlayerSoundPacket(Vec3d pos,String soundIdentifier,float pitch) {
      this.pos = pos;
      this.soundIdentifier = soundIdentifier;
      this.pitch = pitch;
    }
    public NoPlayerSoundPacket(BlockPos pos, SoundEvent soundEvent,float pitch) {
      this(pos.toCenterPos(),soundEvent.getId().toString(),pitch);
    }
    public void write(PacketByteBuf buf) {
      buf.writeDouble(pos.x);
      buf.writeDouble(pos.y);
      buf.writeDouble(pos.z);
      buf.writeString(soundIdentifier);
      buf.writeFloat(pitch);
    }
    public static NoPlayerSoundPacket read(PacketByteBuf buf) {
      Vec3d pos = new Vec3d(buf.readDouble(), buf.readDouble(), buf.readDouble());
      String soundIdentifier = buf.readString();
      float pitch = buf.readFloat();
      return new NoPlayerSoundPacket(pos,soundIdentifier,pitch);
    }
  }
}
