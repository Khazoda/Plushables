package com.khazoda.plush.common;

import com.khazoda.plush.common.block.Block_PenguinBaby;
import com.khazoda.plush.common.item.Item_HeartOfGold;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber
public class ModItems {

    public static Item heartOfGold;

    /**
     * Creative Tab Initialisation
     */
    public static final CreativeTabs tabPlushables = new CreativeTabs("tabPlushables") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(heartOfGold);
        }

        @Override
        public boolean hasSearchBar() {
            return true;
        }
    };

    public static void init() {
        heartOfGold = new Item_HeartOfGold("heartofgold");

        tabPlushables.setBackgroundImageName("item_search.png");
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        final Item[] items = {
                heartOfGold
        };

        for (Item item : items) { //Sets Creative Tab for all blocks
            item.setCreativeTab(ModItems.tabPlushables);
        }

        final IForgeRegistry<Item> registry = event.getRegistry();
        registry.registerAll(items);

    }


}
