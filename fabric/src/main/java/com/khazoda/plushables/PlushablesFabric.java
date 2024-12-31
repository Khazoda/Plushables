package com.khazoda.plushables;

import com.khazoda.plushables.loot.LootTableModificationFabric;
import net.fabricmc.api.ModInitializer;

public class PlushablesFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {

        PlushablesCommon.init();
        PlushablesCommon.postInit();
        PlushablesCommon.REGISTRARS.registerAll();
        LootTableModificationFabric.init();

    }
}
