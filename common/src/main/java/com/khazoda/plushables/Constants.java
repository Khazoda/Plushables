package com.khazoda.plushables;

import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Constants {

  public static final String MOD_ID = "plushables";
  public static final String MOD_NAME = "Plushables";
  public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

  public static Identifier ID(String path) {
    return Identifier.fromNamespaceAndPath(MOD_ID, path);
  }
}