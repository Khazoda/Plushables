package com.khazoda.plushables;

import com.khazoda.plushables.platform.Services;
import com.khazoda.plushables.registry.MainRegistry;
import com.khazoda.plushables.registry.Reginald;
import com.khazoda.plushables.registry.TabRegistry;


public class PlushablesCommon {
  public static final Reginald REGISTRARS = new Reginald();

  public static void init() {
    MainRegistry.init();
    TabRegistry.init();

    if (Services.PLATFORM.isModLoaded("plushables")) Constants.LOG.info("- Plushables Loaded -");
  }

  public static void postInit() {
  }
}