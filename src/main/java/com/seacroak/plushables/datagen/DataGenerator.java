package com.seacroak.plushables.datagen;

import com.seacroak.plushables.datagen.advancements.DataAdvancementsProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class DataGenerator implements DataGeneratorEntrypoint {

  @Override
  public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
    FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
    pack.addProvider(DataAdvancementsProvider::new);
    pack.addProvider(DataLootTableProvider::new);
    pack.addProvider(DataModelProvider::new);
  }
}