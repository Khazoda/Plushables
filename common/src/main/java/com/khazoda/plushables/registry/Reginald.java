package com.khazoda.plushables.registry;

import com.khazoda.plushables.Constants;
import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;

import java.util.Map;

/* Reginald registers reggie. Reggie registers Minecraft stuff. Reginald is Reggie's big brother. */
public class Reginald {
  private final Map<ResourceKey<? extends Registry<?>>, Reggie<?>> registrars = new Object2ObjectLinkedOpenHashMap<>();

  @SuppressWarnings("unchecked")
  public <T> Reggie<T> get(ResourceKey<? extends Registry<? super T>> key) {
    return (Reggie<T>) registrars.computeIfAbsent(key, Reggie::new);
  }

  @SuppressWarnings("unchecked")
  public void register(Registry<?> registry) {
    Reggie<?> registrar = registrars.get(registry.key());
    if (registrar == null) return;

    registrar.registerAll((Registry<? super Object>) registry);
  }

  @SuppressWarnings("unchecked")
  public void registerAll() {
    for (var entry : registrars.entrySet()) {
      Registry<Object> registry = ((Registry<Registry<Object>>) BuiltInRegistries.REGISTRY).get((ResourceKey<Registry<Object>>) entry.getKey());
      if (registry == null) {
        Constants.LOG.error("Attempted to register items for {} but there is no registry with that key", entry.getKey());
        continue;
      }
      entry.getValue().registerAll(registry);
    }
  }
}