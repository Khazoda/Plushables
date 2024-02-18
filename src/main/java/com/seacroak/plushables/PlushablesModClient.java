package com.seacroak.plushables;

import com.seacroak.plushables.config.ConfigPacketHandler;
import com.seacroak.plushables.config.ConfigPacketHandler.ConfigPacket;
import com.seacroak.plushables.networking.*;
import com.seacroak.plushables.networking.AnimationPacketHandler.AnimationPacket;
import com.seacroak.plushables.networking.ParticlePacketHandler.ParticlePacket;
import com.seacroak.plushables.networking.SoundPacketHandler.NoPlayerSoundPacket;
import com.seacroak.plushables.networking.SoundPacketHandler.PlayerSoundPacket;
import com.seacroak.plushables.recipe.BuilderRecipe;
import com.seacroak.plushables.registry.MainRegistry;
import com.seacroak.plushables.registry.client.EntityRendererRegistry;
import com.seacroak.plushables.registry.client.ScreenRegistry;
import com.seacroak.plushables.registry.client.TileRegistryClient;
import io.wispforest.lavender.client.LavenderBookScreen;
import io.wispforest.lavender.md.features.RecipeFeature;
import io.wispforest.owo.ui.component.ItemComponent;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.container.StackLayout;
import io.wispforest.owo.ui.core.ParentComponent;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import java.util.Map;

public final class PlushablesModClient implements ClientModInitializer {
  public static boolean onServer = false;

  @Override
  @Environment(EnvType.CLIENT)
  public void onInitializeClient() {
    ScreenRegistry.initClient();
    TileRegistryClient.initClient();
    EntityRendererRegistry.initClient();

    /* Functional Transparency*/
    /* Blocks */
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.BASKET_BLOCK, RenderLayer.getCutout());

    /* Simple Plushables */
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.PIG_PLUSHABLE, RenderLayer.getCutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.TRUFFLES_PLUSHABLE, RenderLayer.getCutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.WHELPLING_PLUSHABLE, RenderLayer.getCutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.RAPTOR_PLUSHABLE, RenderLayer.getCutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.BIG_TATER_PLUSHABLE, RenderLayer.getCutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.BIG_IRRITATER_PLUSHABLE, RenderLayer.getCutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.OTTER_PLUSHABLE, RenderLayer.getCutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.SHRUMP_PLUSHABLE, RenderLayer.getCutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.WHALE_PLUSHABLE, RenderLayer.getCutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.GOLDFISH_PLUSHABLE, RenderLayer.getCutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.TRATER_PLUSHABLE, RenderLayer.getCutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.FROGE_PLUSHABLE, RenderLayer.getCutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.MAMMOTH_PLUSHABLE, RenderLayer.getCutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.TIGER_PLUSHABLE, RenderLayer.getCutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.BLAHAJ_PLUSHABLE, RenderLayer.getCutout());

    /* Complex Plushables */
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.RUPERT_PLUSHABLE, RenderLayer.getCutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.OWL_PLUSHABLE, RenderLayer.getCutout());
    BlockRenderLayerMap.INSTANCE.putBlock(MainRegistry.STATUETTE_PLUSHABLE, RenderLayer.getCutout());

    /* Config Sync Networking Packet Client Receipt */
    ClientPlayNetworking.registerGlobalReceiver(ConfigPacketHandler.PACKET_ID, ((client, handler, buf, responseSender) -> {
      var packet = ConfigPacket.read(buf);
      if(client == null) return;
      client.execute(() -> {
        PlushablesNetworking.priorityConfig(packet.enable_basket, packet.allow_all_block_items_in_baskets);
      });
    }));

    /* Sound Event Networking Packet Client Receipt */
    ClientPlayNetworking.registerGlobalReceiver(SoundPacketHandler.PACKET_ID_PLAYER, ((client, handler, buf, responseSender) -> {
      var packet = PlayerSoundPacket.read(buf);
      SoundEvent decodedSoundEvent = PacketDecoder.decodeSoundEvent(packet.soundIdentifier);
      if(client == null) return;
      if (packet.player == client.player.getUuid())
        return;
      client.execute(() -> {
        if (client.world == null)
          return;
        PlushablesNetworking.playSoundOnClient(decodedSoundEvent, client.world, BlockPos.ofFloored(packet.pos), 1f, packet.pitch);

      });
    }));

    /* Sound Event Networking Packet Client Receipt */
    ClientPlayNetworking.registerGlobalReceiver(SoundPacketHandler.PACKET_ID_NO_PLAYER, ((client, handler, buf, responseSender) -> {
      var packet = NoPlayerSoundPacket.read(buf);
      SoundEvent decodedSoundEvent = PacketDecoder.decodeSoundEvent(packet.soundIdentifier);
      if(client == null) return;
      client.execute(() -> {
        if (client.world == null)
          return;
        PlushablesNetworking.playSoundOnClient(decodedSoundEvent, client.world, BlockPos.ofFloored(packet.pos), 1f, packet.pitch);
      });
    }));

    /* Particle Networking Packet Client Receipt */
    ClientPlayNetworking.registerGlobalReceiver(ParticlePacketHandler.PACKET_ID, ((client, handler, buf, responseSender) -> {
      var packet = ParticlePacket.read(buf);
      ParticleEffect decodedParticles = PacketDecoder.decodeParticle(packet.particleIdentifier);
      if(client == null) return;
      if (packet.player == client.player.getUuid())
        return;
      client.execute(() -> {
        if (client.world == null)
          return;
        PlushablesNetworking.spawnParticlesOnClient(decodedParticles, client.world, BlockPos.ofFloored(packet.pos), packet.particleCount, packet.offset, packet.spread);

      });
    }));

    /* Animation Event Networking Packet Client Receipt */
    ClientPlayNetworking.registerGlobalReceiver(AnimationPacketHandler.PACKET_ID, ((client, handler, buf, responseSender) -> {
      var packet = AnimationPacket.read(buf);
      if(client == null) return;
      if (packet.player == client.player.getUuid())
        return;
      client.execute(() -> {
        if (client.world == null)
          return;
        PlushablesNetworking.playAnimationOnClient(packet.shouldAnimate, client.world, BlockPos.ofFloored(packet.pos), packet.animationName);

      });
    }));

    LavenderBookScreen.registerRecipePreviewBuilder(new Identifier("plushables:codex"), BuilderRecipe.Type.INSTANCE, (componentSource, recipeEntry) -> {
      var preview = componentSource.template(new Identifier("plushables:codex"), ParentComponent.class, "builder-recipe", Map.of());

      preview.childById(StackLayout.class, "title-anchor").child(componentSource.builtinTemplate(FlowLayout.class, "page-title", Map.of("title", Text.translatable("item.plushables.codex.builder_recipe").getString())));

      preview.childById(RecipeFeature.IngredientComponent.class, "top-input").ingredient(recipeEntry.value().getRecipeItems().get(0));
      preview.childById(RecipeFeature.IngredientComponent.class, "bottom-input").ingredient(recipeEntry.value().getRecipeItems().get(1));
      preview.childById(RecipeFeature.IngredientComponent.class, "heart-input").ingredient(recipeEntry.value().getRecipeItems().get(2));
      preview.childById(ItemComponent.class, "result").stack(recipeEntry.value().getResult(MinecraftClient.getInstance().world.getRegistryManager()));

      return preview;
    });
  }
}
