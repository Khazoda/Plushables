package com.khazoda.plushables.datagen;

import com.khazoda.plushables.Constants;
import com.khazoda.plushables.datagen.provider.PlushablesLootTableProvider;
import com.khazoda.plushables.datagen.provider.PlushablesRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import org.jetbrains.annotations.Nullable;

public class PlushablesDataGenerator implements DataGeneratorEntrypoint {
  @Override
  public void onInitializeDataGenerator(FabricDataGenerator generator) {
    var pack = generator.createPack();
    pack.addProvider(PlushablesLootTableProvider::new);
    pack.addProvider(PlushablesRecipeProvider::new);
  }

  @Override
  public @Nullable String getEffectiveModId() {
    return Constants.MOD_ID;
  }
}