package com.khazoda.plushables.datagen.provider;

import com.khazoda.plushables.registry.MainRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class PlushablesBlockLootTableProvider extends FabricBlockLootTableProvider {
  public PlushablesBlockLootTableProvider(FabricDataOutput dataOutput,
                                          CompletableFuture<HolderLookup.Provider> registryLookup) {
    super(dataOutput, registryLookup);
  }

  @Override
  public void generate() {
    dropSelf(MainRegistry.PLUSHABLE_ANIMATRONIC_BLOCK.get());
    dropSelf(MainRegistry.PLUSHABLE_BEAUX_BLOCK.get());
    dropSelf(MainRegistry.PLUSHABLE_BIG_IRRITATER_BLOCK.get());
    dropSelf(MainRegistry.PLUSHABLE_BIG_TATER_BLOCK.get());
    dropSelf(MainRegistry.PLUSHABLE_BLAHAJ_BLOCK.get());
    dropSelf(MainRegistry.PLUSHABLE_CLUCKY_BLOCK.get());
    dropSelf(MainRegistry.PLUSHABLE_CONDUCTOR_BLOCK.get());
    dropSelf(MainRegistry.PLUSHABLE_COOPER_BLOCK.get());
    dropSelf(MainRegistry.PLUSHABLE_DJUNGELSKOG_BLOCK.get());
    dropSelf(MainRegistry.PLUSHABLE_DORMOUSE_BLOCK.get());
    dropSelf(MainRegistry.PLUSHABLE_DRAGON_BLOCK.get());
    dropSelf(MainRegistry.PLUSHABLE_FOX_BLOCK.get());
    dropSelf(MainRegistry.PLUSHABLE_FROGE_BLOCK.get());
    dropSelf(MainRegistry.PLUSHABLE_FROGLIN_BLOCK.get());
    dropSelf(MainRegistry.PLUSHABLE_GOBLIN_BLOCK.get());
    dropSelf(MainRegistry.PLUSHABLE_GOLDFISH_BLOCK.get());
    dropSelf(MainRegistry.PLUSHABLE_HAMSTER_BLOCK.get());
    dropSelf(MainRegistry.PLUSHABLE_MAMMOTH_BLOCK.get());
    dropSelf(MainRegistry.PLUSHABLE_MOOBLOOM_BLOCK.get());
    dropSelf(MainRegistry.PLUSHABLE_OCTOPLUSHABLE_BLOCK.get());
    dropSelf(MainRegistry.PLUSHABLE_ORANGUTAN_BLOCK.get());
    dropSelf(MainRegistry.PLUSHABLE_OTTER_BLOCK.get());
    dropSelf(MainRegistry.PLUSHABLE_OWL_BLOCK.get());
    dropSelf(MainRegistry.PLUSHABLE_PENGUIN_BLOCK.get());
    dropSelf(MainRegistry.PLUSHABLE_PIG_BLOCK.get());
    dropSelf(MainRegistry.PLUSHABLE_POTSY_BLOCK.get());
    dropSelf(MainRegistry.PLUSHABLE_RAPTOR_BLOCK.get());
    dropSelf(MainRegistry.PLUSHABLE_RATTIAM_BLOCK.get());
    dropSelf(MainRegistry.PLUSHABLE_RUPERT_BLOCK.get());
    dropSelf(MainRegistry.PLUSHABLE_SEA_BUNNY_BLOCK.get());
    dropSelf(MainRegistry.PLUSHABLE_SHRUMP_BLOCK.get());
    dropSelf(MainRegistry.PLUSHABLE_SNAIL_BLOCK.get());
    dropSelf(MainRegistry.PLUSHABLE_STATUETTE_BLOCK.get());
    dropSelf(MainRegistry.PLUSHABLE_TIGER_BLOCK.get());
    dropSelf(MainRegistry.PLUSHABLE_TRATER_BLOCK.get());
    dropSelf(MainRegistry.PLUSHABLE_TRICERATOPS_BLOCK.get());
    dropSelf(MainRegistry.PLUSHABLE_TRUFFLES_BLOCK.get());
    dropSelf(MainRegistry.PLUSHABLE_UNICORN_BLOCK.get());
    dropSelf(MainRegistry.PLUSHABLE_WALRUS_BLOCK.get());
    dropSelf(MainRegistry.PLUSHABLE_WHALE_BLOCK.get());
    dropSelf(MainRegistry.PLUSHABLE_WHELPLING_BLOCK.get());
    dropSelf(MainRegistry.PLUSHABLE_WISP_BLOCK.get());
    dropSelf(MainRegistry.PLUSHABLE_WIZARD_BLOCK.get());
    dropSelf(MainRegistry.PLUSHABLE_ZIGGY_BLOCK.get());
    dropSelf(MainRegistry.PLUSHABLE_SNOWIE_BLOCK.get());
    dropSelf(MainRegistry.PLUSHABLE_RIBBIT_BLOCK.get());
    dropSelf(MainRegistry.PLUSHABLE_CREAKY_BLOCK.get());
    dropSelf(MainRegistry.PLUSHABLE_KWEEBEC_BLOCK.get());

  }
}