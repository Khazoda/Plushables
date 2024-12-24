package com.khazoda.plushables.registry;

import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.function.Supplier;

import static com.khazoda.plushables.Constants.ID;

/* Reggie is a registrar for most Minecraft registry types. Reggie is Reginald's little brother */
public class Reggie<T> {
  private final ResourceKey<? extends Registry<? extends T>> key;
  private final Map<ResourceLocation, Supplier<? extends T>> registryEntries = new Object2ObjectLinkedOpenHashMap<>();

  public Reggie(ResourceKey<? extends Registry<? extends T>> key) {
    this.key = key;
  }

  public <T2 extends T> Supplier<T2> register(String path, Supplier<T2> supplier) {
    ResourceLocation name = ID(path);
    if (registryEntries.containsKey(name))
      throw new IllegalArgumentException("<! Can't register " + name + " twice !>");

    Supplier<T2> memoized = new Supplier<>() {
      @Nullable
      private T2 cacheVal;

      @Override
      public T2 get() {
        T2 val = this.cacheVal;
        if (val == null) {
          this.cacheVal = val = supplier.get();
        }
        return val;
      }
    };
    registryEntries.put(name, memoized);
    return memoized;
  }

  public void registerAll(Registry<? super T> registry) {
    if (key != registry.key()) return;
    for (var entry : registryEntries.entrySet()) {
      Registry.register(registry, entry.getKey(), entry.getValue().get());
    }
  }
}
