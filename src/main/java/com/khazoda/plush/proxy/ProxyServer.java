package com.khazoda.plush.proxy;

import com.khazoda.plush.eventhandlers.EventHandlerServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class ProxyServer extends ProxyCommon {
    @Override
    public void registerEvents()
    {
        FMLCommonHandler.instance().bus().register(new EventHandlerServer());
        MinecraftForge.EVENT_BUS.register(new EventHandlerServer());
    }
}
