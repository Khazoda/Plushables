package com.khazoda.plush.common;


import com.khazoda.plush.proxy.ProxyCommon;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = Plushables.MODID, name = Plushables.MODNAME, version = Plushables.MODVERSION, useMetadata = true)

public class Plushables {

    public static final String MODID = "plush";
    public static final String MODNAME = "Plushables";
    public static final String MODVERSION = "indev 0.12";

    @SidedProxy(clientSide = "com.khazoda.plush.proxy.ProxyClient", serverSide = "com.khazoda.plush.proxy.ProxyServer")
    public static ProxyCommon proxy;

    @Mod.Instance
    public static Plushables instance;
    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        proxy.preInit(event);

        ModItems.init();
        ModBlocks.init();

    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }
}
