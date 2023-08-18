package com.seacroak.plushables.client.renderer.item;

import com.seacroak.plushables.client.model.item.MushroomCapModel;
import com.seacroak.plushables.item.MushroomCap;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class MushroomCapRenderer extends GeoArmorRenderer<MushroomCap> {

  public MushroomCapRenderer() {
    super(new MushroomCapModel());
  }
}
