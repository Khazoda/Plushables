package com.khazoda.plushables.registry;

import com.khazoda.plushables.PlushablesCommon;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public class TabRegistry {
  public static final Supplier<CreativeModeTab> PLUSHABLES_TAB = PlushablesCommon.REGISTRARS.get(Registries.CREATIVE_MODE_TAB).register("main", () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0).title(Component.translatable("itemGroup.plushables.main")).icon(() -> MainRegistry.PLUSHABLE_PENGUIN_BLOCK.getItem().getDefaultInstance()).displayItems((parameters, output) -> {
    output.accept(MainRegistry.HEART_OF_GOLD_ITEM.get());
    output.accept(MainRegistry.PLUSHABLE_PENGUIN_BLOCK.getItem());
    output.accept(MainRegistry.PLUSHABLE_FROGLIN_BLOCK.getItem());
    output.accept(MainRegistry.PLUSHABLE_FOX_BLOCK.getItem());
    output.accept(MainRegistry.PLUSHABLE_CLUCKY_BLOCK.getItem());
    output.accept(MainRegistry.PLUSHABLE_PIG_BLOCK.getItem());
    output.accept(MainRegistry.PLUSHABLE_TRUFFLES_BLOCK.getItem());
    output.accept(MainRegistry.PLUSHABLE_DJUNGELSKOG_BLOCK.getItem());
    output.accept(MainRegistry.PLUSHABLE_RATTIAM_BLOCK.getItem());
    output.accept(MainRegistry.PLUSHABLE_TRICERATOPS_BLOCK.getItem());
    output.accept(MainRegistry.PLUSHABLE_UNICORN_BLOCK.getItem());
    output.accept(MainRegistry.PLUSHABLE_WHELPLING_BLOCK.getItem());
    output.accept(MainRegistry.PLUSHABLE_RAPTOR_BLOCK.getItem());
    output.accept(MainRegistry.PLUSHABLE_RUPERT_BLOCK.getItem());
    output.accept(MainRegistry.PLUSHABLE_DRAGON_BLOCK.getItem());
    output.accept(MainRegistry.PLUSHABLE_WIZARD_BLOCK.getItem());
    output.accept(MainRegistry.PLUSHABLE_BEAUX_BLOCK.getItem());
    output.accept(MainRegistry.PLUSHABLE_GOBLIN_BLOCK.getItem());
    output.accept(MainRegistry.PLUSHABLE_BIG_TATER_BLOCK.getItem());
    output.accept(MainRegistry.PLUSHABLE_BIG_IRRITATER_BLOCK.getItem());
    output.accept(MainRegistry.PLUSHABLE_OTTER_BLOCK.getItem());
    output.accept(MainRegistry.PLUSHABLE_SHRUMP_BLOCK.getItem());
    output.accept(MainRegistry.PLUSHABLE_OCTOPLUSHABLE_BLOCK.getItem());
    output.accept(MainRegistry.PLUSHABLE_SNAIL_BLOCK.getItem());
    output.accept(MainRegistry.PLUSHABLE_WHALE_BLOCK.getItem());
    output.accept(MainRegistry.PLUSHABLE_ORANGUTAN_BLOCK.getItem());
    output.accept(MainRegistry.PLUSHABLE_GOLDFISH_BLOCK.getItem());
    output.accept(MainRegistry.PLUSHABLE_TRATER_BLOCK.getItem());
    output.accept(MainRegistry.PLUSHABLE_CONDUCTOR_BLOCK.getItem());
    output.accept(MainRegistry.PLUSHABLE_ANIMATRONIC_BLOCK.getItem());
    output.accept(MainRegistry.PLUSHABLE_MOOBLOOM_BLOCK.getItem());
    output.accept(MainRegistry.PLUSHABLE_FROGE_BLOCK.getItem());
    output.accept(MainRegistry.PLUSHABLE_OWL_BLOCK.getItem());
    output.accept(MainRegistry.PLUSHABLE_HAMSTER_BLOCK.getItem());
    output.accept(MainRegistry.PLUSHABLE_DORMOUSE_BLOCK.getItem());
    output.accept(MainRegistry.PLUSHABLE_SEA_BUNNY_BLOCK.getItem());
    output.accept(MainRegistry.PLUSHABLE_STATUETTE_BLOCK.getItem());
    output.accept(MainRegistry.PLUSHABLE_MAMMOTH_BLOCK.getItem());
    output.accept(MainRegistry.PLUSHABLE_TIGER_BLOCK.getItem());
    output.accept(MainRegistry.PLUSHABLE_WALRUS_BLOCK.getItem());
    output.accept(MainRegistry.PLUSHABLE_BLAHAJ_BLOCK.getItem());
    output.accept(MainRegistry.PLUSHABLE_WISP_BLOCK.getItem());
    output.accept(MainRegistry.PLUSHABLE_COOPER_BLOCK.getItem());
    output.accept(MainRegistry.PLUSHABLE_ZIGGY_BLOCK.getItem());
    output.accept(MainRegistry.PLUSHABLE_POTSY_BLOCK.getItem());
    output.accept(MainRegistry.PLUSHABLE_SNOWIE_BLOCK.getItem());
    output.accept(MainRegistry.PLUSHABLE_RIBBIT_BLOCK.getItem());
    output.accept(MainRegistry.PLUSHABLE_CREAKY_BLOCK.getItem());
    output.accept(MainRegistry.PLUSHABLE_KWEEBEC_BLOCK.getItem());
    output.accept(MainRegistry.PLUSHABLE_STONELING_BLOCK.getItem());

  }).build());

  public static void init() {
  }
}
