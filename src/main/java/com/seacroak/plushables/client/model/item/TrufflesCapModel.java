package com.seacroak.plushables.client.model.item;

import com.seacroak.plushables.item.FoxCap;
import com.seacroak.plushables.item.TrufflesCap;
import com.seacroak.plushables.util.GenericUtils;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class TrufflesCapModel extends GeoModel<TrufflesCap> {

  @Override
  public Identifier getModelResource(TrufflesCap animatable) {
    return GenericUtils.ID("geo/armor/cap_truffles.geo.json");
  }

  @Override
  public Identifier getTextureResource(TrufflesCap animatable) {
    return GenericUtils.ID("textures/armor/cap_truffles.png");
  }

  @Override
  public Identifier getAnimationResource(TrufflesCap animatable) {
    return GenericUtils.ID("animations/armor/cap_truffles.animation.json");
  }
}
