package com.seacroak.plushables.datagen.advancements;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;

import java.util.function.Consumer;

public class DataAdvancementsProvider extends FabricAdvancementProvider {
  public DataAdvancementsProvider(FabricDataOutput dataGenerator) {
    super(dataGenerator);
  }

  @Override
  public void generateAdvancement(Consumer<Advancement> consumer) {
    new PlushablesAdvancements().accept(consumer);
  }
}