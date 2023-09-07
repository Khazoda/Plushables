package com.seacroak.plushables.client.model.item;

import com.seacroak.plushables.item.BeauxCapItem;
import com.seacroak.plushables.util.GenericUtils;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class BeauxCapModel extends GeoModel<BeauxCapItem> {

  @Override
  public Identifier getModelResource(BeauxCapItem animatable) {
    return GenericUtils.ID("geo/armor/cap_beaux.geo.json");
  }

  @Override
  public Identifier getTextureResource(BeauxCapItem animatable) {
    return GenericUtils.ID("textures/armor/cap_beaux.png");
  }

  @Override
  public Identifier getAnimationResource(BeauxCapItem animatable) {
    return GenericUtils.ID("animations/armor/cap_beaux.animation.json");
  }
}
