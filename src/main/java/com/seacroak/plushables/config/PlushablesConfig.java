package com.seacroak.plushables.config;

import eu.midnightdust.lib.config.MidnightConfig;

public class PlushablesConfig extends MidnightConfig {

  @Comment(centered = true) public static Comment title;

  @Comment public static Comment betaWarning;
  @Entry public static boolean enableBasket = true;
  @Comment public static Comment spacer;
  @Comment public static Comment experimentalWarning;
  @Entry public static boolean allowAllBlockItemsInBasket = false;


}
