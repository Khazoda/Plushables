package com.seacroak.plushables.client.renderer.item;

import com.seacroak.plushables.client.model.item.FroglinCapModel;
import com.seacroak.plushables.item.FroglinCap;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class FroglinCapRenderer extends GeoArmorRenderer<FroglinCap> {

  public FroglinCapRenderer() {
    super(new FroglinCapModel());
  }
}
