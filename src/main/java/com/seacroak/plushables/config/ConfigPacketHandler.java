package com.seacroak.plushables.config;

import com.seacroak.plushables.PlushablesModClient;
import com.seacroak.plushables.util.GenericUtils;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.PacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import java.util.UUID;

/* Similar structure to all packet handlers in plushables.networking */
/* Assures that the serverside config always takes priority over the client's */
/* Packet is always sent from the server to clients */
/* Packet fields should mimic those found in PlushablesConfig.class */

public class ConfigPacketHandler {
  public static final Identifier PACKET_ID = GenericUtils.ID("plushable_config_packet");

  public static void sendConfigPacketToClient(ServerPlayerEntity player, ConfigPacket packet) {
      var buf = PacketByteBufs.create();
      packet.write(buf);
      ServerPlayNetworking.send(player, PACKET_ID, buf);
  }

  public static class ConfigPacket implements Packet<PacketListener> {
    public UUID player_to_sync;
    public boolean enable_basket;
    public boolean allow_all_block_items_in_baskets;

    public ConfigPacket(UUID playerToSync,boolean enableBasket, boolean allowBlockItems) {
      this.player_to_sync = playerToSync;
      this.enable_basket = enableBasket;
      this.allow_all_block_items_in_baskets = allowBlockItems;
    }

    @Override
    public void write(PacketByteBuf buf) {
      buf.writeUuid(player_to_sync);
      buf.writeBoolean(enable_basket);
      buf.writeBoolean(allow_all_block_items_in_baskets);
    }

    @Override
    public void apply(PacketListener listener) {
      PlushablesModClient.onServer = true;
    }


    public static ConfigPacket read(PacketByteBuf buf) {
      UUID player_to_sync = buf.readUuid();
      boolean enableBasket = buf.readBoolean();
      boolean allowBlockItems = buf.readBoolean();
      return new ConfigPacket(player_to_sync,enableBasket, allowBlockItems);
    }
  }
}
