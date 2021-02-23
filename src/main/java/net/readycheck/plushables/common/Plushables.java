package net.readycheck.plushables.common;

import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.readycheck.plushables.common.blocks.recycler.Recycler;
import net.readycheck.plushables.common.blocks.recycler.StartupCommon;
import net.readycheck.plushables.tools.debugging.ForgeLoggerTweaker;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Plushables.MOD_ID)
public class Plushables
{
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "plushables";
    public static final ItemGroup PlushablesGroup = new PlushablesGroup(Plushables.MOD_ID);

    public Plushables() {
        final boolean HIDE_CONSOLE_NOISE = false;
        if (HIDE_CONSOLE_NOISE) {
            ForgeLoggerTweaker.setMinimumLevel(Level.WARN);
            ForgeLoggerTweaker.applyLoggerFilter();
        }
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        registerCommonEvents(modEventBus);

        // Register ourselves for server and other game events we are interested in
//        MinecraftForge.EVENT_BUS.register(this);

    }


public void registerCommonEvents(IEventBus eventBus) {
        eventBus.register(net.readycheck.plushables.common.blocks.recycler.StartupCommon.class);

//      ====================
        eventBus.register(net.readycheck.plushables.tools.debugging.StartupCommon.class);

}
    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("Plushables", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }
    public static class PlushablesGroup extends ItemGroup {

        public PlushablesGroup(String name) {
            super(name);
        }

        @Override
        public ItemStack createIcon() {
            return StartupCommon.itemRecycler.getDefaultInstance();
        }

        @Override
        public void fill(NonNullList<ItemStack> items) {
            super.fill(items);
        }
    }
}


