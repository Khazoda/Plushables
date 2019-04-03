package com.khazoda.plush.common;

import com.khazoda.plush.common.block.Block_PenguinBaby;
import com.khazoda.plush.common.block.Block_RedPanda;
import com.khazoda.plush.common.block.Block_Tacocat;
import com.khazoda.plush.common.block.Block_Tacosaur;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {

    @GameRegistry.ObjectHolder("plush:penguin_baby")
    public static Block_PenguinBaby penguin_baby;

    @GameRegistry.ObjectHolder("plush:red_panda")
    public static Block_RedPanda red_panda;

    @GameRegistry.ObjectHolder("plush:tacocat")
    public static Block_Tacocat tacocat;

    @GameRegistry.ObjectHolder("plush:tacosaur")
    public static Block_Tacosaur tacosaur;
}
