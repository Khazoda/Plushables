package com.seacroak.plushables.util.networking;

import com.seacroak.plushables.util.GenericUtils;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import java.util.UUID;

public class BasketPacketHandler {
  public static final Identifier PACKET_ID = GenericUtils.ID("plushable_basket_packet");

  /* Basket Data Packet*/
  public static void sendPacketToClients(ServerWorld world, BasketPacketHandler.BasketDataPacket packet) {
    world.getPlayers().forEach(player -> {
      if (player.getUuid() == packet.player)
        return;
      var buf = PacketByteBufs.create();
      packet.write(buf);
      ServerPlayNetworking.send(player, PACKET_ID, buf);
    });
  }

  public static class BasketDataPacket {
    public UUID player;
    public Vec3d pos;
    ItemStack[] plushStack;
    int[] seeds;
    int topPointer;

    public BasketDataPacket(UUID player, Vec3d pos, ItemStack[] plushStack, int[] seeds, int topPointer) {
      this.player = player;
      this.pos = pos;
      this.plushStack = plushStack;
      this.seeds = seeds;
      this.topPointer = topPointer;

    }

    public BasketDataPacket(PlayerEntity player, BlockPos pos, ItemStack[] plushStack, int[] seeds,int topPointer) {
      this(player.getUuid(), pos.toCenterPos(), plushStack, seeds,topPointer);
    }

    public void write(PacketByteBuf buf) {
      buf.writeUuid(player);
      buf.writeDouble(pos.x);
      buf.writeDouble(pos.y);
      buf.writeDouble(pos.z);
//      buf.writeItemStack(plushFromStack);
      buf.writeIntArray(seeds);
      buf.writeInt(topPointer);
    }
  }

}
