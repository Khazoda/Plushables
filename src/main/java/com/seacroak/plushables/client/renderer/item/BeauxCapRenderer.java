package com.seacroak.plushables.client.renderer.item;

import com.seacroak.plushables.client.model.item.BeauxCapModel;
import com.seacroak.plushables.item.BeauxCap;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class BeauxCapRenderer extends GeoArmorRenderer<BeauxCap> {

  public BeauxCapRenderer() {
    super(new BeauxCapModel());
  }
}
