package com.seacroak.plushables.registry;

import com.seacroak.plushables.PlushablesMod;
import com.seacroak.plushables.gui.BuilderScreenHandler;

import net.fabricmc.api.ModInitializer;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

// Screen registry is in ClientRegistry.java
// Screen handlers are registered here

public class ScreenRegistry implements ModInitializer {
    public static final ScreenHandlerType<BuilderScreenHandler> BUILDER_SCREEN_HANDLER = new ScreenHandlerType<>(
            BuilderScreenHandler::new);

    @Override
    public void onInitialize() {
        Registry.register(Registry.SCREEN_HANDLER, new Identifier(PlushablesMod.ModID, "builder"),
                BUILDER_SCREEN_HANDLER);

    }

}