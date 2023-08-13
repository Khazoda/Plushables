package com.seacroak.plushables.client.renderer.item;

import com.seacroak.plushables.client.model.item.FoxCapModel;
import com.seacroak.plushables.item.FoxCap;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class FoxCapRenderer extends GeoArmorRenderer<FoxCap> {

  public FoxCapRenderer() {
    super(new FoxCapModel());
  }
}
