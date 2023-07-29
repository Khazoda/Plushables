package com.seacroak.plushables.client.model.item;

import com.seacroak.plushables.item.FoxCap;
import com.seacroak.plushables.util.GenericUtils;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class FoxCapModel extends GeoModel<FoxCap> {

  @Override
  public Identifier getModelResource(FoxCap animatable) {
    return GenericUtils.ID("geo/armor/cap_fox.geo.json");
  }

  @Override
  public Identifier getTextureResource(FoxCap animatable) {
    return GenericUtils.ID("textures/armor/cap_fox.png");
  }

  @Override
  public Identifier getAnimationResource(FoxCap animatable) {
    return GenericUtils.ID("animations/armor/cap_fox.animation.json");
  }
}
