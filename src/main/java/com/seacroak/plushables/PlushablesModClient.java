package com.seacroak.plushables;

import com.seacroak.plushables.block.WizardBlock;
import com.seacroak.plushables.registry.MainRegistry;
import com.seacroak.plushables.registry.ScreenRegistry;
import com.seacroak.plushables.registry.TileRegistryClient;

import com.seacroak.plushables.util.PlushablesNetworking;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.math.BlockPos;

public final class PlushablesModClient implements ClientModInitializer {
  @Override
  @Environment(EnvType.CLIENT)
  public void onInitializeClient() {
    ScreenRegistry.initClient();
    TileRegistryClient.initClient();

//    Functional Transparency
//    Simple Plushables
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.PIG_PLUSHABLE, RenderLayer.getCutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.TRUFFLES_PLUSHABLE, RenderLayer.getCutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.WHELPLING_PLUSHABLE, RenderLayer.getCutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.RAPTOR_PLUSHABLE, RenderLayer.getCutout());
//    Complex Plushables
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.RUPERT_BLOCK, RenderLayer.getCutout());

    /* Networking Packet Client Receipt */
    ClientPlayNetworking.registerGlobalReceiver(PlushablesNetworking.packetID, ((client, handler, buf, responseSender) -> {
      var packet = PlushablesNetworking.SoundPacket.read(buf);
      if (packet.player == client.player.getUuid())
        return;
      client.execute(() -> {
        if (client.world == null)
          return;
        WizardBlock.playSoundAnimation(client.world, packet.pos, new BlockPos((int) packet.pos.x, (int) packet.pos.y, (int) packet.pos.z), 1f);
      });
    }));
  }
}
