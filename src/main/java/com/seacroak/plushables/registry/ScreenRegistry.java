package com.seacroak.plushables.registry;

import com.seacroak.plushables.PlushablesMod;
import com.seacroak.plushables.gui.BuilderScreenHandler;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.seacroak.plushables.registry.MainRegistry.BUILDER_BLOCK;

// Screen registry is in ClientRegistry.java
// Screen handlers are registered here

public final class ScreenRegistry {

    public static final DeferredRegister<MenuType<?>> SCREEN_HANDLERS =
        DeferredRegister.create(ForgeRegistries.MENU_TYPES, PlushablesMod.MOD_ID);

    public static final RegistryObject<MenuType<BuilderScreenHandler>> BUILDER_SCREEN_HANDLER =
        registerMenuType(BuilderScreenHandler::new, "builder_screen_handler");

    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory, String name) {
        return SCREEN_HANDLERS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus) {
        SCREEN_HANDLERS.register(eventBus);
    }

}