package com.seacroak.plushables.registry;

import com.seacroak.plushables.PlushablesMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ItemGroupRegistry {
  public static final DeferredRegister<CreativeModeTab> PLUSHABLES_GROUP =
      DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PlushablesMod.MOD_ID);

  public static final RegistryObject<CreativeModeTab> PLUSHABLES_TAB = PLUSHABLES_GROUP.register("plushables_tab",
      () -> CreativeModeTab.builder().icon(() -> new ItemStack(MainRegistry.HEART_OF_GOLD.get()))
          .title(Component.translatable("itemGroup.plushables.creative_tab"))
          .displayItems((pParameters, pOutput) -> {
            pOutput.accept(MainRegistry.HEART_OF_GOLD.get());
            pOutput.accept(MainRegistry.POWERED_HEART.get());
            pOutput.accept(MainRegistry.PENGUIN_PLUSHABLE.get());

          })
          .build());

  public static void register(IEventBus eventBus) {
    PLUSHABLES_GROUP.register(eventBus);
  }
}
