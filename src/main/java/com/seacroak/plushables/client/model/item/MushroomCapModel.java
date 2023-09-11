package com.seacroak.plushables.client.model.item;

import com.seacroak.plushables.item.MushroomCapItem;
import com.seacroak.plushables.util.GenericUtils;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class MushroomCapModel extends GeoModel<MushroomCapItem> {

  @Override
  public Identifier getModelResource(MushroomCapItem animatable) {
    return GenericUtils.ID("geo/armor/cap_mushroom.geo.json");
  }

  @Override
  public Identifier getTextureResource(MushroomCapItem animatable) {
    return GenericUtils.ID("textures/armor/cap_mushroom.png");
  }

  @Override
  public Identifier getAnimationResource(MushroomCapItem animatable) {
    return GenericUtils.ID("animations/armor/cap_mushroom.animation.json");
  }
}
