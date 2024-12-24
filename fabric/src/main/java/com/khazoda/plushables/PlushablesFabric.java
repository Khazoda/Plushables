package com.khazoda.plushables;

import net.fabricmc.api.ModInitializer;

public class PlushablesFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {

        PlushablesCommon.init();
        PlushablesCommon.postInit();
        PlushablesCommon.REGISTRARS.registerAll();

        Constants.LOG.info("+ Plushables Fabric Loaded +");
    }
}
