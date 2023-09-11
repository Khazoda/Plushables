package com.seacroak.plushables.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;

public class DataModelProvider extends FabricModelProvider {
  public DataModelProvider(FabricDataOutput output) {
    super(output);
  }

  @Override
  public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
  }

  @Override
  public void generateItemModels(ItemModelGenerator itemModelGenerator) {
//    itemModelGenerator.register(MainRegistry.HEART_OF_GOLD, Models.GENERATED);
//    itemModelGenerator.register(MainRegistry.POWERED_HEART, Models.GENERATED);
  }
}