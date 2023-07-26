package com.seacroak.plushables.client.model.item;

import com.seacroak.plushables.item.CapArmorItem;
import com.seacroak.plushables.item.FroglinCap;
import com.seacroak.plushables.util.GenericUtils;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class FroglinCapModel extends GeoModel<FroglinCap> {

  @Override
  public ResourceLocation getModelResource(FroglinCap animatable) {
    return GenericUtils.ID("geo/cap_froglin.geo.json");
  }

  @Override
  public ResourceLocation getTextureResource(FroglinCap animatable) {
    return GenericUtils.ID("textures/armor/cap_froglin.png");
  }

  @Override
  public ResourceLocation getAnimationResource(FroglinCap animatable) {
    return GenericUtils.ID("animations/cap_froglin.animation.json");
  }
}
