package com.khazoda.plushables;


import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

@Mod(Constants.MOD_ID)
public class PlushablesNorge {

    public PlushablesNorge(IEventBus eventBus) {

        PlushablesCommon.init();
        eventBus.addListener(this::onInit);
        eventBus.addListener(this::onRegister);

        Constants.LOG.info("+ Plushables NeoForge Loaded +");
    }

    private void onInit(FMLCommonSetupEvent event) {
        event.enqueueWork(PlushablesCommon::postInit);
    }

    private void onRegister(RegisterEvent event) {
        PlushablesCommon.REGISTRARS.register(event.getRegistry());
    }
}