package com.seacroak.plushables;

import com.seacroak.plushables.registry.*;
import com.seacroak.plushables.util.GenericUtils;
import com.seacroak.plushables.util.networking.PlushablesNetworking;
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
  /* Unix Epoch start time since mod loaded. Used for timer comparisons */
  public static Long START_TIME = System.currentTimeMillis() / 1000;

  @Override
  public void onInitialize() {
    Registry.register(Registries.ITEM_GROUP, GenericUtils.ID("plushables"), PLUSHABLES_GROUP);

    MainRegistry.init();
    SoundRegistry.init();
    ScreenRegistry.init();
    RecipeRegistry.init();
    new TileRegistry();

    PlushablesNetworking.registerGlobalSoundPacketReceiverWithPlayer();
    PlushablesNetworking.registerGlobalSoundPacketReceiverWithoutPlayer();
    PlushablesNetworking.registerGlobalParticlePacketReceiver();
    PlushablesNetworking.registerGlobalAnimationPacketReceiver();

    GeckoLib.initialize();
    LOGGER.info("Plushables has loaded");
  }

}
