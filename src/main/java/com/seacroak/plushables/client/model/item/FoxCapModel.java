package com.seacroak.plushables.client.model.item;

import com.seacroak.plushables.item.FoxCapItem;
import com.seacroak.plushables.util.GenericUtils;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class FoxCapModel extends GeoModel<FoxCapItem> {

  @Override
  public Identifier getModelResource(FoxCapItem animatable) {
    return GenericUtils.ID("geo/armor/cap_fox.geo.json");
  }

  @Override
  public Identifier getTextureResource(FoxCapItem animatable) {
    return GenericUtils.ID("textures/armor/cap_fox.png");
  }

  @Override
  public Identifier getAnimationResource(FoxCapItem animatable) {
    return GenericUtils.ID("animations/armor/cap_fox.animation.json");
  }
}
