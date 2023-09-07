package com.seacroak.plushables.client.renderer.item;

import com.seacroak.plushables.client.model.item.TrufflesCapModel;
import com.seacroak.plushables.item.TrufflesCapItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class TrufflesCapRenderer extends GeoArmorRenderer<TrufflesCapItem> {

  public TrufflesCapRenderer() {
    super(new TrufflesCapModel());
  }
}
