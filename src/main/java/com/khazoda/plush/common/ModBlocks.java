package com.khazoda.plush.common;

import com.khazoda.plush.common.block.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

@GameRegistry.ObjectHolder(Plushables.MODID)
@Mod.EventBusSubscriber
public class ModBlocks {


    public static Block penguin_baby;
    public static Block red_panda;
    public static Block tacocat;
    public static Block tacosaur;
    public static Block june;
    public static Block bigtater;

    public static void init() {

        penguin_baby = new Block_PenguinBaby("penguin_baby", Material.ROCK).setBoundingBox(2, 0, 2, 14, 15, 14);
        red_panda = new Block_RedPanda("red_panda", Material.ROCK).setBoundingBox(2, 0, 2, 14, 15, 14);
        tacocat = new Block_Tacocat("tacocat", Material.ROCK).setBoundingBox(6, 0, 0, 11, 13, 16);
        tacosaur = new Block_Tacosaur("tacosaur", Material.ROCK).setBoundingBox(6, 0, 1, 11, 12, 15);
        june = new Block_June("june",Material.ROCK).setBoundingBox(4,0,4,10,16,11);
        bigtater = new Block_BigTater("bigtater",Material.CLOTH).setBoundingBox(2,0,2,14,16,14);
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        final Block[] blocks = {
                penguin_baby,
                red_panda,
                tacocat,
                tacosaur,
                june,
                bigtater
        };

        for (Block block : blocks) { //Sets Creative Tab for all blocks
            block.setCreativeTab(ModItems.tabPlushables);
        }

        final IForgeRegistry<Block> registry = event.getRegistry();
        registry.registerAll(blocks);
    }

    @SubscribeEvent
    public static void registerItemBlocks(RegistryEvent.Register<Item> event) {
        final Item[] items = {
                new ItemBlock(penguin_baby).setRegistryName(penguin_baby.getRegistryName()),
                new ItemBlock(red_panda).setRegistryName(red_panda.getRegistryName()),
                new ItemBlock(tacocat).setRegistryName(tacocat.getRegistryName()),
                new ItemBlock(tacosaur).setRegistryName(tacosaur.getRegistryName()),
                new ItemBlock(june).setRegistryName(june.getRegistryName()),
                new ItemBlock(bigtater).setRegistryName(bigtater.getRegistryName())

        };

        for (Item item : items) { //Sets Creative Tab for all items
            item.setCreativeTab(ModItems.tabPlushables);
        }

        final IForgeRegistry<Item> registry = event.getRegistry();
        registry.registerAll(items);
    }

}
