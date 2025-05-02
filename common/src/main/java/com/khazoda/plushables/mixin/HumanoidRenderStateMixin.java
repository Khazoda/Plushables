package com.khazoda.plushables.mixin;

import com.khazoda.plushables.duck.IHumanoidRenderState;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(HumanoidRenderState.class)
public abstract class HumanoidRenderStateMixin implements IHumanoidRenderState {
  @Unique
  ItemStack plushables$mainItem;
  @Unique
  ItemStack plushables$offhandItem;

  @Override
  public void plushables$setMainHandItem(ItemStack item) {
    plushables$mainItem = item;
  }

  @Override
  public void plushables$setOffHandItem(ItemStack item) {
    plushables$offhandItem = item;
  }

  @Override
  public ItemStack plushables$getMainHandItem() {
    return plushables$mainItem;
  }

  @Override
  public ItemStack plushables$getOffHandItem() {
    return plushables$offhandItem;
  }
}
