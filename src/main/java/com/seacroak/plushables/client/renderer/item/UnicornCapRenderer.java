package com.seacroak.plushables.client.renderer.item;

import com.seacroak.plushables.client.model.item.UnicornCapModel;
import com.seacroak.plushables.item.UnicornCapItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class UnicornCapRenderer extends GeoArmorRenderer<UnicornCapItem> {

  public UnicornCapRenderer() {
    super(new UnicornCapModel());
  }
}
