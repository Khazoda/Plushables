package net.readycheck.plushables.common;

import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.readycheck.plushables.common.blocks.recycler.Recycler;
import net.readycheck.plushables.common.blocks.penguin.StartupCommon;
import net.readycheck.plushables.common.entities.froglin.FroglinEntity;
import net.readycheck.plushables.common.entities.froglin.FroglinRenderer;
import net.readycheck.plushables.tools.debugging.ForgeLoggerTweaker;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.example.client.renderer.entity.ExampleGeoRenderer;
import software.bernie.example.registry.EntityRegistry;
import software.bernie.geckolib3.GeckoLib;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Plushables.MOD_ID)
public class Plushables {
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "plushables";
    public static final ItemGroup PlushablesGroup = new PlushablesGroup(Plushables.MOD_ID);
    public static final ItemGroup PlushieGroup = new PlushieGroup("plushies");

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
//        final ClientRegistryHandler clientSideOnlyModEventRegistrar = new ClientRegistryHandler(modEventBus);

        Registration.init();
//      clientRegistration is called in doClientStuff
        registerCommonEvents(modEventBus);
//        DistExecutor.safeRunWhenOn(Dist.CLIENT,
//                () -> clientSideOnlyModEventRegistrar::registerClientOnlyEvents);

//        Entities

//      Geckolib Init
        GeckoLib.initialize();

        // Register ourselves for server and other game events we are interested in
//        MinecraftForge.EVENT_BUS.register(this);

    }


    public void registerCommonEvents(IEventBus eventBus) {
//        eventBus.register(net.readycheck.plushables.common.blocks.recycler.StartupCommon.class);
//        eventBus.register(net.readycheck.plushables.common.blocks.penguin.StartupCommon.class);
//        eventBus.register(net.readycheck.plushables.common.entities.froglin.StartupCommon.class);
//        DeferredWorkQueue.runLater(() -> {
//            GlobalEntityTypeAttributes.put(net.readycheck.plushables.common.entities.froglin.StartupCommon.FROGLIN.get(), FroglinEntity.setCustomAttributes().create());
//        });

//      ====================
        eventBus.register(net.readycheck.plushables.tools.debugging.StartupCommon.class);

    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerRenderers(final FMLClientSetupEvent event) {

    }

    private void setup(final FMLCommonSetupEvent event) {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
        Registration.setupCreatures();

    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
        ClientRegistration.setup();

    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("Plushables", "helloworld", () -> {
            LOGGER.info("Hello world from the MDK");
            return "Hello world";
        });
    }

    private void processIMC(final InterModProcessEvent event) {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m -> m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    public static class PlushieGroup extends ItemGroup {

        public PlushieGroup(String name) {
            super(name);
        }

        @Override
        public ItemStack createIcon() {
            return new ItemStack(Registration.PENGUIN.get());
        }


        @Override
        public void fill(NonNullList<ItemStack> items) {
            super.fill(items);
        }
    }

    public static class PlushablesGroup extends ItemGroup {

        public PlushablesGroup(String name) {
            super(name);
        }

        @Override
        public ItemStack createIcon() {
            return new ItemStack(Registration.RECYCLER_ITEM.get());
        }


        @Override
        public void fill(NonNullList<ItemStack> items) {
            super.fill(items);
        }
    }
}


