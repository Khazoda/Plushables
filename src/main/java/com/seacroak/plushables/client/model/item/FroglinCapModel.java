package com.seacroak.plushables.client.model.item;

import com.seacroak.plushables.item.FroglinCap;
import com.seacroak.plushables.util.GenericUtils;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class FroglinCapModel extends GeoModel<FroglinCap> {

  @Override
  public Identifier getModelResource(FroglinCap animatable) {
    return GenericUtils.ID("geo/cap_froglin.geo.json");
  }

  @Override
  public Identifier getTextureResource(FroglinCap animatable) {
    return GenericUtils.ID("textures/armor/cap_froglin.png");
  }

  @Override
  public Identifier getAnimationResource(FroglinCap animatable) {
    return GenericUtils.ID("animations/cap_froglin.animation.json");
  }
}
