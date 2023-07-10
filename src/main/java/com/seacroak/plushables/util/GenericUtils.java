package com.seacroak.plushables.util;

import com.seacroak.plushables.PlushablesMod;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class GenericUtils {
  @NotNull
  public static ResourceLocation ID(@NotNull String path) {
    return new ResourceLocation(PlushablesMod.MOD_ID, path);
  }
}
