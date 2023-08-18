package com.seacroak.plushables.config;

import eu.midnightdust.lib.config.MidnightConfig;

public class PlushablesConfig extends MidnightConfig {

  @Comment(centered = true) public static Comment title;

  @Comment public static Comment betaWarning;
  @Comment public static Comment spacer;
  @Entry public static boolean enable_baskets = true;
  @Comment public static Comment spacer1;
  @Comment public static Comment experimentalWarning;
  @Comment public static Comment spacer2;
  @Entry public static boolean allow_all_block_items_in_baskets = false;


}
