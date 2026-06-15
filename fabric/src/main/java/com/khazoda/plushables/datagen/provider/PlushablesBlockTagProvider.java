package com.khazoda.plushables.datagen.provider;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.khazoda.plushables.Constants;
import com.khazoda.plushables.registry.MainRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;

import java.util.concurrent.CompletableFuture;

public class PlushablesBlockTagProvider implements DataProvider {
  public static final ResourceLocation ALL_PLUSHABLE_BLOCKS = Constants.ID("all_plushables");
  private final PackOutput.PathProvider pathProvider;

  public PlushablesBlockTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
    this.pathProvider = output.createPathProvider(PackOutput.Target.DATA_PACK, "tags/block");
  }

  @Override
  public CompletableFuture<?> run(CachedOutput output) {
    JsonObject tag = new JsonObject();
    tag.addProperty("replace", false);

    JsonArray values = new JsonArray();

    for (var plushable : MainRegistry.PLUSHABLE_LIST) {
      values.add(BuiltInRegistries.BLOCK.getKey(plushable.getBlock()).toString());
    }

    tag.add("values", values);
    return DataProvider.saveStable(output, tag, this.pathProvider.json(ALL_PLUSHABLE_BLOCKS));
  }

  @Override
  public String getName() {
    return "Plushables Block Tags";
  }
}
