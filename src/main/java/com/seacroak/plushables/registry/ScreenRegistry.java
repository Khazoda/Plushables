package com.seacroak.plushables.registry;

import com.seacroak.plushables.gui.BuilderScreen;
import com.seacroak.plushables.gui.BuilderScreenHandler;
import com.seacroak.plushables.util.GenericUtils;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.registry.Registry;

// Screen registry is in ClientRegistry.java
// Screen handlers are registered here

public final class ScreenRegistry {
    public static final ScreenHandlerType<BuilderScreenHandler> BUILDER_SCREEN_HANDLER = new ScreenHandlerType<>(
            BuilderScreenHandler::new);

    public static void init() {
        register("builder", BUILDER_SCREEN_HANDLER);
    }

    @Environment(EnvType.CLIENT)
    public static void initClient() {
        HandledScreens.register(BUILDER_SCREEN_HANDLER, BuilderScreen::new);

    }

    private static void register(String name, ScreenHandlerType<?> type) {
        Registry.register(Registry.SCREEN_HANDLER, GenericUtils.ID(name), type);
    }

}