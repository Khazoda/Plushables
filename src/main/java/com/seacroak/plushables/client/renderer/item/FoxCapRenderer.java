package com.seacroak.plushables.client.renderer.item;

import com.seacroak.plushables.item.FoxCap;
import com.seacroak.plushables.item.FroglinCap;
import com.seacroak.plushables.util.GenericUtils;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class FoxCapRenderer extends GeoArmorRenderer<FoxCap> {

  public FoxCapRenderer() {
    super(new DefaultedItemGeoModel(GenericUtils.ID("armor/cap_fox")));
  }
}
