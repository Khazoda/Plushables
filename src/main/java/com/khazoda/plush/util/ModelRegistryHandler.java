package com.khazoda.plush.util;

import com.khazoda.plush.common.ModBlocks;
import com.khazoda.plush.common.ModItems;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ModelRegistryHandler {

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {

        /***
         * REGISTER ITEMBLOCKS
         */
        registerBlockModel(ModBlocks.penguin_baby);
        registerBlockModel(ModBlocks.red_panda);
        registerBlockModel(ModBlocks.tacocat);
        registerBlockModel(ModBlocks.tacosaur);
        registerBlockModel(ModBlocks.june);
        registerBlockModel(ModBlocks.bigtater);

        /**
         * REGISTER ITEMS
         */
        registerItemModel(ModItems.heartOfGold);

    }

    private static void registerItemModel(Item item) {

        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));

    }

    private static void registerBlockModel(Block block) {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
    }


}