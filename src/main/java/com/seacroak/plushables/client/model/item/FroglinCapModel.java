package com.seacroak.plushables.client.model.item;

import com.seacroak.plushables.item.FroglinCapItem;
import com.seacroak.plushables.util.GenericUtils;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class FroglinCapModel extends GeoModel<FroglinCapItem> {

  @Override
  public Identifier getModelResource(FroglinCapItem animatable) {
    return GenericUtils.ID("geo/armor/cap_froglin.geo.json");
  }

  @Override
  public Identifier getTextureResource(FroglinCapItem animatable) {
    return GenericUtils.ID("textures/armor/cap_froglin.png");
  }

  @Override
  public Identifier getAnimationResource(FroglinCapItem animatable) {
    return GenericUtils.ID("animations/armor/cap_froglin.animation.json");
  }
}
