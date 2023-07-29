package com.seacroak.plushables;

import com.seacroak.plushables.registry.MainRegistry;
import com.seacroak.plushables.registry.ScreenRegistry;
import com.seacroak.plushables.registry.TileRegistryClient;

import com.seacroak.plushables.util.GenericUtils;
import com.seacroak.plushables.util.networking.PacketDecoder;
import com.seacroak.plushables.util.networking.ParticlePacketHandler;
import com.seacroak.plushables.util.networking.PlushablesNetworking;
import com.seacroak.plushables.util.networking.SoundPacketHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.sound.SoundEvent;
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

    /* Sound Event Networking Packet Client Receipt */
    ClientPlayNetworking.registerGlobalReceiver(SoundPacketHandler.PACKET_ID, ((client, handler, buf, responseSender) -> {
      var packet = SoundPacketHandler.SoundPacket.read(buf);
      SoundEvent decodedSoundEvent = PacketDecoder.decodeSoundEvent(packet.soundIdentifier);
      if (packet.player == client.player.getUuid())
        return;
      client.execute(() -> {
        if (client.world == null)
          return;
        PlushablesNetworking.playSound(decodedSoundEvent,client.world, BlockPos.ofFloored(packet.pos), 1f);

      });
    }));

    /* Particle Networking Packet Client Receipt */
    ClientPlayNetworking.registerGlobalReceiver(ParticlePacketHandler.PACKET_ID, ((client, handler, buf, responseSender) -> {
      var packet = ParticlePacketHandler.ParticlePacket.read(buf);
      System.out.println(packet.particleIdentifier);
      ParticleEffect decodedParticles = PacketDecoder.decodeParticle(packet.particleIdentifier);
      System.out.println(decodedParticles);

      if (packet.player == client.player.getUuid())
        return;
      client.execute(() -> {
        if (client.world == null)
          return;
        PlushablesNetworking.spawnParticles(decodedParticles,client.world, BlockPos.ofFloored(packet.pos), packet.particleCount,packet.offset,packet.spread);

      });
    }));
  }
}
