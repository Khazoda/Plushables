package com.seacroak.plushables.registry;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

public class ItemGroupRegistry {
  public static ItemGroup createItemGroup() {
    return FabricItemGroup.builder()
        .icon(() -> new ItemStack(MainRegistry.PENGUIN_PLUSHABLE))
        .displayName(Text.translatable("itemGroup.plushables.itemGroup"))
        .entries((displayContext, entries) -> {
          entries.add(new ItemStack(MainRegistry.CODEX_ITEM));
          entries.add(new ItemStack(MainRegistry.BASKET_BLOCK));
          entries.add(new ItemStack(MainRegistry.BUILDER_BLOCK));
          entries.add(new ItemStack(MainRegistry.HEART_OF_GOLD));
          entries.add(new ItemStack(MainRegistry.POWERED_HEART));
          entries.add(new ItemStack(MainRegistry.PENGUIN_PLUSHABLE));
          entries.add(new ItemStack(MainRegistry.FOX_PLUSHABLE));
          entries.add(new ItemStack(MainRegistry.FROGLIN_PLUSHABLE));
          entries.add(new ItemStack(MainRegistry.CLUCKY_PLUSHABLE));
          entries.add(new ItemStack(MainRegistry.PIG_PLUSHABLE));
          entries.add(new ItemStack(MainRegistry.TRUFFLES_PLUSHABLE));
          entries.add(new ItemStack(MainRegistry.DJUNGELSKOG_PLUSHABLE));
          entries.add(new ItemStack(MainRegistry.RATTIAM_PLUSHABLE));
          entries.add(new ItemStack(MainRegistry.TRICERATOPS_PLUSHABLE));
          entries.add(new ItemStack(MainRegistry.UNICORN_PLUSHABLE));
          entries.add(new ItemStack(MainRegistry.WHELPLING_PLUSHABLE));
          entries.add(new ItemStack(MainRegistry.RAPTOR_PLUSHABLE));
          entries.add(new ItemStack(MainRegistry.RUPERT_PLUSHABLE));
          entries.add(new ItemStack(MainRegistry.DRAGON_PLUSHABLE));
          entries.add(new ItemStack(MainRegistry.WIZARD_PLUSHABLE));
          entries.add(new ItemStack(MainRegistry.BEAUX_PLUSHABLE));
          entries.add(new ItemStack(MainRegistry.GOBLIN_PLUSHABLE));
          entries.add(new ItemStack(MainRegistry.BIG_TATER_PLUSHABLE));
          entries.add(new ItemStack(MainRegistry.BIG_IRRITATER_PLUSHABLE));
          entries.add(new ItemStack(MainRegistry.OTTER_PLUSHABLE));
          entries.add(new ItemStack(MainRegistry.SHRUMP_PLUSHABLE));
          entries.add(new ItemStack(MainRegistry.OCTOPLUSH_PLUSHABLE));
          entries.add(new ItemStack(MainRegistry.SNAIL_PLUSHABLE));
          entries.add(new ItemStack(MainRegistry.WHALE_PLUSHABLE));
          entries.add(new ItemStack(MainRegistry.ORANGUTAN_PLUSHABLE));
          entries.add(new ItemStack(MainRegistry.GOLDFISH_PLUSHABLE));
          entries.add(new ItemStack(MainRegistry.TRATER_PLUSHABLE));
          entries.add(new ItemStack(MainRegistry.CONDUCTOR_PLUSHABLE));
          entries.add(new ItemStack(MainRegistry.CAPYBARA_PLUSHABLE));
          entries.add(new ItemStack(MainRegistry.ANIMATRONIC_PLUSHABLE));
          entries.add(new ItemStack(MainRegistry.MOOBLOOM_PLUSHABLE));
          entries.add(new ItemStack(MainRegistry.FROGE_PLUSHABLE));
          entries.add(new ItemStack(MainRegistry.OWL_PLUSHABLE));
          entries.add(new ItemStack(MainRegistry.HAMPTER_PLUSHABLE));
          entries.add(new ItemStack(MainRegistry.DORMOUSE_PLUSHABLE));
          entries.add(new ItemStack(MainRegistry.SEA_BUNNY_PLUSHABLE));
          entries.add(new ItemStack(MainRegistry.STATUETTE_PLUSHABLE));
          entries.add(new ItemStack(MainRegistry.MAMMOTH_PLUSHABLE));
          entries.add(new ItemStack(MainRegistry.TIGER_PLUSHABLE));
          entries.add(new ItemStack(MainRegistry.WALRUS_PLUSHABLE));
          entries.add(new ItemStack(MainRegistry.BLAHAJ_PLUSHABLE));


          // Caps
          entries.add(new ItemStack(MainRegistry.FROGLIN_CAP));
          entries.add(new ItemStack(MainRegistry.FOX_CAP));
          entries.add(new ItemStack(MainRegistry.UNICORN_CAP));
          entries.add(new ItemStack(MainRegistry.MUSHROOM_CAP));
          entries.add(new ItemStack(MainRegistry.BEAUX_CAP));
          entries.add(new ItemStack(MainRegistry.TRUFFLES_CAP));

        }).build();
  }

}
