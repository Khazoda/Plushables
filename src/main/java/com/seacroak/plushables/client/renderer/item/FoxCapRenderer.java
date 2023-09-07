package com.seacroak.plushables.client.renderer.item;

import com.seacroak.plushables.client.model.item.FoxCapModel;
import com.seacroak.plushables.item.FoxCapItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class FoxCapRenderer extends GeoArmorRenderer<FoxCapItem> {

  public FoxCapRenderer() {
    super(new FoxCapModel());
  }
}
