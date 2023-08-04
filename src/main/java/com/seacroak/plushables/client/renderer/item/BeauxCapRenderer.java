package com.seacroak.plushables.client.renderer.item;

import com.seacroak.plushables.client.model.item.BeauxCapModel;
import com.seacroak.plushables.client.model.item.FoxCapModel;
import com.seacroak.plushables.item.BeauxCap;
import com.seacroak.plushables.item.FoxCap;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class BeauxCapRenderer extends GeoArmorRenderer<BeauxCap> {

  public BeauxCapRenderer() {
    super(new BeauxCapModel());
  }
}
