package com.seacroak.plushables.client.renderer.item;

import com.seacroak.plushables.client.model.item.FroglinCapModel;
import com.seacroak.plushables.item.FroglinCapItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class FroglinCapRenderer extends GeoArmorRenderer<FroglinCapItem> {

  public FroglinCapRenderer() {
    super(new FroglinCapModel());
  }
}
