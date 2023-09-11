package com.seacroak.plushables.registry.client;

import com.seacroak.plushables.block.screen.BuilderScreen;
import com.seacroak.plushables.block.screen.BuilderScreenHandler;
import com.seacroak.plushables.util.GenericUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerType;
public final class ScreenRegistry {
    public static final ScreenHandlerType<BuilderScreenHandler> BUILDER_SCREEN_HANDLER = new ScreenHandlerType<>(
            BuilderScreenHandler::new, FeatureFlags.DEFAULT_ENABLED_FEATURES);

    public static void init() {
        register("builder", BUILDER_SCREEN_HANDLER);
    }

    @Environment(EnvType.CLIENT)
    public static void initClient() {
        HandledScreens.register(BUILDER_SCREEN_HANDLER, BuilderScreen::new);

    }

    private static void register(String name, ScreenHandlerType<?> type) {
        Registry.register(Registries.SCREEN_HANDLER, GenericUtils.ID(name), type);
    }

}