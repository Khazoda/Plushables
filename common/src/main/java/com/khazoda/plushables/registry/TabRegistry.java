package com.khazoda.plushables.registry;

import com.khazoda.plushables.PlushablesCommon;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public class TabRegistry {
  public static final Supplier<CreativeModeTab> PLUSHABLES_TAB = PlushablesCommon.REGISTRARS.get(Registries.CREATIVE_MODE_TAB).register("main", () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0).title(Component.translatable("itemGroup.plushables.main")).icon(() -> MainRegistry.PLUSHABLE_PENGUIN_ITEM.get().getDefaultInstance()).displayItems((parameters, output) -> {
    output.accept(MainRegistry.HEART_OF_GOLD_ITEM.get());
    output.accept(MainRegistry.PLUSHABLE_PENGUIN_ITEM.get());
    output.accept(MainRegistry.PLUSHABLE_FROGLIN_ITEM.get());
    output.accept(MainRegistry.PLUSHABLE_FOX_ITEM.get());
    output.accept(MainRegistry.PLUSHABLE_CLUCKY_ITEM.get());
    output.accept(MainRegistry.PLUSHABLE_PIG_ITEM.get());
    output.accept(MainRegistry.PLUSHABLE_TRUFFLES_ITEM.get());
    output.accept(MainRegistry.PLUSHABLE_DJUNGELSKOG_ITEM.get());
    output.accept(MainRegistry.PLUSHABLE_RATTIAM_ITEM.get());
    output.accept(MainRegistry.PLUSHABLE_TRICERATOPS_ITEM.get());
    output.accept(MainRegistry.PLUSHABLE_UNICORN_ITEM.get());
    output.accept(MainRegistry.PLUSHABLE_WHELPLING_ITEM.get());
    output.accept(MainRegistry.PLUSHABLE_RAPTOR_ITEM.get());
    output.accept(MainRegistry.PLUSHABLE_RUPERT_ITEM.get());
    output.accept(MainRegistry.PLUSHABLE_DRAGON_ITEM.get());
    output.accept(MainRegistry.PLUSHABLE_WIZARD_ITEM.get());
    output.accept(MainRegistry.PLUSHABLE_BEAUX_ITEM.get());
    output.accept(MainRegistry.PLUSHABLE_GOBLIN_ITEM.get());
    output.accept(MainRegistry.PLUSHABLE_BIG_TATER_ITEM.get());
    output.accept(MainRegistry.PLUSHABLE_BIG_IRRITATER_ITEM.get());
    output.accept(MainRegistry.PLUSHABLE_OTTER_ITEM.get());
    output.accept(MainRegistry.PLUSHABLE_SHRUMP_ITEM.get());
    output.accept(MainRegistry.PLUSHABLE_OCTOPLUSHABLE_ITEM.get());
    output.accept(MainRegistry.PLUSHABLE_SNAIL_ITEM.get());
    output.accept(MainRegistry.PLUSHABLE_WHALE_ITEM.get());
    output.accept(MainRegistry.PLUSHABLE_ORANGUTAN_ITEM.get());
    output.accept(MainRegistry.PLUSHABLE_GOLDFISH_ITEM.get());
    output.accept(MainRegistry.PLUSHABLE_TRATER_ITEM.get());
    output.accept(MainRegistry.PLUSHABLE_CONDUCTOR_ITEM.get());
    output.accept(MainRegistry.PLUSHABLE_ANIMATRONIC_ITEM.get());
    output.accept(MainRegistry.PLUSHABLE_MOOBLOOM_ITEM.get());
    output.accept(MainRegistry.PLUSHABLE_FROGE_ITEM.get());
    output.accept(MainRegistry.PLUSHABLE_OWL_ITEM.get());
    output.accept(MainRegistry.PLUSHABLE_HAMSTER_ITEM.get());
    output.accept(MainRegistry.PLUSHABLE_DORMOUSE_ITEM.get());
    output.accept(MainRegistry.PLUSHABLE_SEA_BUNNY_ITEM.get());
    output.accept(MainRegistry.PLUSHABLE_STATUETTE_ITEM.get());
    output.accept(MainRegistry.PLUSHABLE_MAMMOTH_ITEM.get());
    output.accept(MainRegistry.PLUSHABLE_TIGER_ITEM.get());
    output.accept(MainRegistry.PLUSHABLE_WALRUS_ITEM.get());
    output.accept(MainRegistry.PLUSHABLE_BLAHAJ_ITEM.get());
    output.accept(MainRegistry.PLUSHABLE_WISP_ITEM.get());
    output.accept(MainRegistry.PLUSHABLE_COOPER_ITEM.get());
    output.accept(MainRegistry.PLUSHABLE_ZIGGY_ITEM.get());
    output.accept(MainRegistry.PLUSHABLE_POTSY_ITEM.get());
    output.accept(MainRegistry.PLUSHABLE_SNOWIE_ITEM.get());
    output.accept(MainRegistry.PLUSHABLE_RIBBIT_ITEM.get());
    output.accept(MainRegistry.PLUSHABLE_CREAKY_ITEM.get());
    output.accept(MainRegistry.PLUSHABLE_KWEEBEC_ITEM.get());
    output.accept(MainRegistry.PLUSHABLE_STONELING_ITEM.get());

  }).build());
  public static void init() {
  }
}
