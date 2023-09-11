package com.seacroak.plushables.client.model.item;

import com.seacroak.plushables.item.UnicornCapItem;
import com.seacroak.plushables.util.GenericUtils;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class UnicornCapModel extends GeoModel<UnicornCapItem> {

  @Override
  public Identifier getModelResource(UnicornCapItem animatable) {
    return GenericUtils.ID("geo/armor/cap_unicorn.geo.json");
  }

  @Override
  public Identifier getTextureResource(UnicornCapItem animatable) {
    return GenericUtils.ID("textures/armor/cap_unicorn.png");
  }

  @Override
  public Identifier getAnimationResource(UnicornCapItem animatable) {
    return GenericUtils.ID("animations/armor/cap_unicorn.animation.json");
  }
}
