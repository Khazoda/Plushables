package com.khazoda.plushables.datagen.provider;

import com.khazoda.plushables.registry.MainRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.resources.ResourceLocation;

import java.util.concurrent.CompletableFuture;

public class PlushablesBlockStateProvider implements DataProvider {
  private final PackOutput.PathProvider pathProvider;

  public PlushablesBlockStateProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
    this.pathProvider = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "blockstates");
  }

  @Override
  public CompletableFuture<?> run(CachedOutput output) {
    return CompletableFuture.allOf(MainRegistry.PLUSHABLE_LIST.stream().map(plushable -> {
      ResourceLocation blockId = BuiltInRegistries.BLOCK.getKey(plushable.getBlock());
      return DataProvider.saveStable(output, MultiVariantGenerator.multiVariant(plushable.getBlock(), Variant.variant().with(VariantProperties.MODEL, blockId.withPrefix("block/"))).get(), this.pathProvider.json(blockId));
    }).toArray(CompletableFuture[]::new));
  }

  @Override
  public String getName() {
    return "Plushables Blockstates";
  }
}
