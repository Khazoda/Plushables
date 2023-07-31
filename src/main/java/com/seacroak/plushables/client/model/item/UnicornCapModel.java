package com.seacroak.plushables.client.model.item;

import com.seacroak.plushables.item.UnicornCap;
import com.seacroak.plushables.util.GenericUtils;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class UnicornCapModel extends GeoModel<UnicornCap> {

  @Override
  public Identifier getModelResource(UnicornCap animatable) {
    return GenericUtils.ID("geo/armor/cap_unicorn.geo.json");
  }

  @Override
  public Identifier getTextureResource(UnicornCap animatable) {
    return GenericUtils.ID("textures/armor/cap_unicorn.png");
  }

  @Override
  public Identifier getAnimationResource(UnicornCap animatable) {
    return GenericUtils.ID("animations/armor/cap_unicorn.animation.json");
  }
}
