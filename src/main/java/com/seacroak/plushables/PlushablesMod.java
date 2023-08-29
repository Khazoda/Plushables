package com.seacroak.plushables;

import com.seacroak.plushables.config.PlushablesConfig;
import com.seacroak.plushables.registry.assets.ResourcePackRegistry;
import com.seacroak.plushables.registry.assets.SoundRegistry;
import com.seacroak.plushables.registry.client.ScreenRegistry;
import com.seacroak.plushables.registry.ItemGroupRegistry;
import com.seacroak.plushables.registry.MainRegistry;
import com.seacroak.plushables.registry.uncommon.RecipeRegistry;
import com.seacroak.plushables.registry.uncommon.TileRegistry;
import com.seacroak.plushables.util.GenericUtils;
import com.seacroak.plushables.networking.PlushablesNetworking;
import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;

import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.bernie.geckolib.GeckoLib;

public final class PlushablesMod implements ModInitializer {
  public static final String MOD_ID = "plushables";
  public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
  public static final ItemGroup PLUSHABLES_GROUP = ItemGroupRegistry.createItemGroup();

  @Override
  public void onInitialize() {
    Registry.register(Registries.ITEM_GROUP, GenericUtils.ID("plushables"), PLUSHABLES_GROUP);
    MidnightConfig.init(MOD_ID, PlushablesConfig.class);
    PlushablesNetworking.registerServersideClientJoinListener();

    MainRegistry.init();
    SoundRegistry.init();
    ScreenRegistry.init();
    RecipeRegistry.init();
    /* Keep this in */
    new TileRegistry();

    PlushablesNetworking.registerGlobalSoundPacketReceiverWithPlayer();
    PlushablesNetworking.registerGlobalSoundPacketReceiverWithoutPlayer();
    PlushablesNetworking.registerGlobalParticlePacketReceiver();
    PlushablesNetworking.registerGlobalAnimationPacketReceiver();

    GeckoLib.initialize();
    if(ResourcePackRegistry.registerIfPresent("bovinesandbuttercups","bovinesandbuttercups_moobloom")) LOGGER.info("[Plushables] The bovines have arrived :)");

    LOGGER.info("[Plushables] Finished loading!");
  }
}
