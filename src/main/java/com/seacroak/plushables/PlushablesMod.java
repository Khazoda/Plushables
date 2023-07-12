package com.seacroak.plushables;

import com.mojang.logging.LogUtils;
import com.seacroak.plushables.client.renderer.tile.BuilderTileRenderer;
import com.seacroak.plushables.client.renderer.tile.CluckyTileRenderer;
import com.seacroak.plushables.client.renderer.tile.DragonTileRenderer;
import com.seacroak.plushables.client.renderer.tile.RupertTileRenderer;
import com.seacroak.plushables.gui.BuilderScreen;
import com.seacroak.plushables.registry.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;
import software.bernie.geckolib.GeckoLib;

import static com.seacroak.plushables.registry.MainRegistry.PIG_PLUSHABLE;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(PlushablesMod.MOD_ID)
public class PlushablesMod {
  // Define mod id in a common place for everything to reference
  public static final String MOD_ID = "plushables";
  // Directly reference a slf4j logger
  private static final Logger LOGGER = LogUtils.getLogger();


  public PlushablesMod() {
    IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

    MainRegistry.register(modEventBus);
    ItemGroupRegistry.register(modEventBus);
    SoundRegistry.register(modEventBus);
    TileRegistry.register(modEventBus);
    RecipeRegistry.register(modEventBus);


    GeckoLib.initialize();
    // Register the commonSetup method for modloading
    modEventBus.addListener(this::commonSetup);

    // Register ourselves for server and other game events we are interested in
    MinecraftForge.EVENT_BUS.register(this);

    // Register the item to a creative tab
    modEventBus.addListener(this::addCreative);
  }

  private void commonSetup(final FMLCommonSetupEvent event) {

  }

  // Add the example block item to the building blocks tab
  private void addCreative(BuildCreativeModeTabContentsEvent event) {

  }

  // You can use SubscribeEvent and let the Event Bus discover methods to call
  @SubscribeEvent
  public void onServerStarting(ServerStartingEvent event) {
    // Do something when the server starts
    LOGGER.info("Plushables has loaded on the server!");
  }

  // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
  @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
  public static class ClientModEvents {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
      IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
      // Screens
      ScreenRegistry.register(modEventBus);
      MenuScreens.register(ScreenRegistry.BUILDER_SCREEN_HANDLER.get(), BuilderScreen::new);
      // Block Entities (Tile Entities)
      TileRegistryClient.initClient();
      LOGGER.info("Plushables has loaded on the client!");
    }

  }
}
