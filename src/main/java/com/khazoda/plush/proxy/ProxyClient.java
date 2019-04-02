package com.khazoda.plush.proxy;

import com.khazoda.plush.eventhandlers.EventHandlerClient;
import com.khazoda.plush.eventhandlers.EventHandlerServer;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import static com.khazoda.plush.common.Plushables.MODID;

public class ProxyClient extends ProxyCommon {
    @Override
    public void registerEvents()
    {
        super.registerEvents();
        FMLCommonHandler.instance().bus().register(new EventHandlerClient());
        MinecraftForge.EVENT_BUS.register(new EventHandlerServer());
    }

    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);

        OBJLoader.INSTANCE.addDomain(MODID);
    }

}
