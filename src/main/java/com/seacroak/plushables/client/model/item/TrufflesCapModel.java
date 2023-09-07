package com.seacroak.plushables.client.model.item;

import com.seacroak.plushables.item.TrufflesCapItem;
import com.seacroak.plushables.util.GenericUtils;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class TrufflesCapModel extends GeoModel<TrufflesCapItem> {

  @Override
  public Identifier getModelResource(TrufflesCapItem animatable) {
    return GenericUtils.ID("geo/armor/cap_truffles.geo.json");
  }

  @Override
  public Identifier getTextureResource(TrufflesCapItem animatable) {
    return GenericUtils.ID("textures/armor/cap_truffles.png");
  }

  @Override
  public Identifier getAnimationResource(TrufflesCapItem animatable) {
    return GenericUtils.ID("animations/armor/cap_truffles.animation.json");
  }
}
