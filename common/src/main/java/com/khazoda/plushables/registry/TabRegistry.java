package com.khazoda.plushables.registry;

import com.khazoda.plushables.PlushablesCommon;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;

import java.util.function.Supplier;

public class TabRegistry {
  public static final Supplier<CreativeModeTab> PLUSHABLES_TAB = PlushablesCommon.REGISTRARS.get(Registries.CREATIVE_MODE_TAB)
      .register("main", () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
          .title(Component.translatable("itemGroup.plushables.itemGroup"))
          .icon(() -> MainRegistry.PLUSHABLE_PENGUIN_ITEM.get().getDefaultInstance())
          .displayItems((parameters, output) -> {
            output.accept(MainRegistry.PLUSHABLE_PENGUIN_ITEM.get());
          })
          .build());

  public static void init() {
  }
}
