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
          .displayItems((pParameters, event) -> {
            event.accept(MainRegistry.HEART_OF_GOLD.get());
            event.accept(MainRegistry.POWERED_HEART.get());
            event.accept(MainRegistry.BUILDER_BLOCK.get());
            event.accept(MainRegistry.PENGUIN_PLUSHABLE.get());
            event.accept(MainRegistry.FOX_PLUSHABLE.get());
            event.accept(MainRegistry.FROGLIN_PLUSHABLE.get());
            event.accept(MainRegistry.CLUCKY_BLOCK.get());
            event.accept(MainRegistry.PIG_PLUSHABLE.get());
            event.accept(MainRegistry.TRUFFLES_PLUSHABLE.get());
            event.accept(MainRegistry.DJUNGELSKOG_PLUSHABLE.get());
            event.accept(MainRegistry.RATTIAM_PLUSHABLE.get());
            event.accept(MainRegistry.TRICERATOPS_PLUSHABLE.get());
            event.accept(MainRegistry.UNICORN_PLUSHABLE.get());
            event.accept(MainRegistry.WHELPLING_PLUSHABLE.get());
            event.accept(MainRegistry.RAPTOR_PLUSHABLE.get());
            event.accept(MainRegistry.RUPERT_BLOCK.get());
            event.accept(MainRegistry.DRAGON_BLOCK.get());
            event.accept(MainRegistry.WIZARD_PLUSHABLE.get());
            event.accept(MainRegistry.BEAUX_PLUSHABLE.get());
            event.accept(MainRegistry.GOBLIN_PLUSHABLE.get());
            /* Caps */
            event.accept(MainRegistry.FROGLIN_CAP.get());
          })
          .build());

  public static void register(IEventBus eventBus) {
    PLUSHABLES_GROUP.register(eventBus);
  }
}
