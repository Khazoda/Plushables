package net.readycheck.plushables.common;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.readycheck.plushables.common.Plushables;

public class ClientRegistryHandler {

//    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Plushables.MOD_ID);
//    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Plushables.MOD_ID);
//
//    public static void init() {
//        // attach DeferredRegister to the event bus
//        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
//        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
//    }
//
//
//
//    public static final RegistryObject<Item> HEART_OF_GOLD = RegistryHandler.ITEMS.register("heart_of_gold", () -> new Item(new Item.Properties().maxStackSize(16).group(ItemGroup.MISC)));
//
//    public static final RegistryObject<Block> BIG_TATO = RegistryHandler.BLOCKS.register("big_tato", () -> new Block(Block.Properties.create(Material.ROCK)));
//    public static final RegistryObject<Item> BIG_TATO_ITEM = RegistryHandler.ITEMS.register("big_tato", () -> new BlockItem(BIG_TATO.get(), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)));

    private final IEventBus eventBus;

    /**
     * @param eventBus an instance of the mod event bus
     */
    public ClientRegistryHandler(IEventBus eventBus) {
        this.eventBus = eventBus;
    }

    /**
     * Register client only events. This method must only be called when it is certain that the mod is
     * is executing code on the client side and not the dedicated server.
     */
    public void registerClientOnlyEvents() {

        eventBus.register(net.readycheck.plushables.common.blocks.recycler.StartupClientOnly.class);

//        ===========
        eventBus.register(net.readycheck.plushables.tools.debugging.StartupClientOnly.class);
    }
}

