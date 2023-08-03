package com.seacroak.plushables.client.model.item;

import com.seacroak.plushables.item.MushroomCap;
import com.seacroak.plushables.util.GenericUtils;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class MushroomCapModel extends GeoModel<MushroomCap> {

  @Override
  public Identifier getModelResource(MushroomCap animatable) {
    return GenericUtils.ID("geo/armor/cap_mushroom.geo.json");
  }

  @Override
  public Identifier getTextureResource(MushroomCap animatable) {
    return GenericUtils.ID("textures/armor/cap_mushroom.png");
  }

  @Override
  public Identifier getAnimationResource(MushroomCap animatable) {
    return GenericUtils.ID("animations/armor/cap_mushroom.animation.json");
  }
}
