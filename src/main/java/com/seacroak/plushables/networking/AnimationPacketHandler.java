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

public class AnimationPacketHandler {
  public static final Identifier PACKET_ID = GenericUtils.ID("plushable_animation_packet");

  /* Animation Packet*/
  public static void sendPacketToClients(ServerWorld world, AnimationPacket packet) {
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

  public static class AnimationPacket {
    public UUID player;
    public Vec3d pos;
    public boolean shouldAnimate;
    public String animationName;

    public AnimationPacket(UUID player, Vec3d pos, boolean shouldAnimate, String animationName) {
      this.player = player;
      this.pos = pos;
      this.shouldAnimate = shouldAnimate;
      this.animationName = animationName;
    }

    public AnimationPacket(PlayerEntity player, BlockPos pos, boolean shouldAnimate, String animationName) {
      this(player.getUuid(), pos.toCenterPos(), shouldAnimate, animationName);
    }

    public void write(PacketByteBuf buf) {
      buf.writeUuid(player);
      buf.writeDouble(pos.x);
      buf.writeDouble(pos.y);
      buf.writeDouble(pos.z);
      buf.writeBoolean(shouldAnimate);
      buf.writeString(animationName);
    }

    public static AnimationPacket read(PacketByteBuf buf) {
      UUID player = buf.readUuid();
      Vec3d pos = new Vec3d(buf.readDouble(), buf.readDouble(), buf.readDouble());
      boolean shouldAnimate = buf.readBoolean();
      String animationName = buf.readString();
      return new AnimationPacket(player, pos, shouldAnimate, animationName);
    }
  }
}
