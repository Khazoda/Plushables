package com.seacroak.plushables.datagen;

import com.seacroak.plushables.registry.MainRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
  public ModLootTableProvider(FabricDataOutput dataOutput) {
    super(dataOutput);
  }

  @Override
  public void generate() {
    addDrop(MainRegistry.PENGUIN_PLUSHABLE);
    addDrop(MainRegistry.FOX_PLUSHABLE);
    addDrop(MainRegistry.FROGLIN_PLUSHABLE);
    addDrop(MainRegistry.CLUCKY_PLUSHABLE);
    addDrop(MainRegistry.PIG_PLUSHABLE);
    addDrop(MainRegistry.TRUFFLES_PLUSHABLE);
    addDrop(MainRegistry.DJUNGELSKOG_PLUSHABLE);
    addDrop(MainRegistry.RATTIAM_PLUSHABLE);
    addDrop(MainRegistry.TRICERATOPS_PLUSHABLE);
    addDrop(MainRegistry.UNICORN_PLUSHABLE);
    addDrop(MainRegistry.WHELPLING_PLUSHABLE);
    addDrop(MainRegistry.RAPTOR_PLUSHABLE);
    addDrop(MainRegistry.RUPERT_PLUSHABLE);
    addDrop(MainRegistry.DRAGON_PLUSHABLE);
    addDrop(MainRegistry.WIZARD_PLUSHABLE);
    addDrop(MainRegistry.BEAUX_PLUSHABLE);
    addDrop(MainRegistry.GOBLIN_PLUSHABLE);
    addDrop(MainRegistry.BIG_TATER_PLUSHABLE);
    addDrop(MainRegistry.BIG_IRRITATER_PLUSHABLE);
    addDrop(MainRegistry.OTTER_PLUSHABLE);
    addDrop(MainRegistry.SHRUMP_PLUSHABLE);
    addDrop(MainRegistry.OCTOPLUSH_PLUSHABLE);
    addDrop(MainRegistry.SNAIL_PLUSHABLE);
    addDrop(MainRegistry.WHALE_PLUSHABLE);
    addDrop(MainRegistry.ORANGUTAN_PLUSHABLE);
    addDrop(MainRegistry.GOLDFISH_PLUSHABLE);
    addDrop(MainRegistry.TRATER_PLUSHABLE);
    addDrop(MainRegistry.CONDUCTOR_PLUSHABLE);
    addDrop(MainRegistry.CAPYBARA_PLUSHABLE);
    addDrop(MainRegistry.ANIMATRONIC_PLUSHABLE);
    addDrop(MainRegistry.MOOBLOOM_PLUSHABLE);
    addDrop(MainRegistry.FROGE_PLUSHABLE);
    addDrop(MainRegistry.OWL_PLUSHABLE);
    addDrop(MainRegistry.HAMPTER_PLUSHABLE);

  }
}