package com.khazoda.plushables;

import com.khazoda.plushables.platform.Services;
import com.khazoda.plushables.registry.MainRegistry;
import com.khazoda.plushables.registry.Reginald;
import com.khazoda.plushables.registry.TabRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Items;


public class PlushablesCommon {
    public static final Reginald REGISTRARS = new Reginald();

    public static void init() {
        MainRegistry.init();
        TabRegistry.init();

        Constants.LOG.info("Hello from Common init on {}! we are currently in a {} environment!", Services.PLATFORM.getPlatformName(), Services.PLATFORM.getEnvironmentName());
        Constants.LOG.info("The ID for diamonds is {}", BuiltInRegistries.ITEM.getKey(Items.DIAMOND));
        if (Services.PLATFORM.isModLoaded("plushables")) {
            Constants.LOG.info("Hello to Plushables");
        }
    }

    public static void postInit() {
    }
}