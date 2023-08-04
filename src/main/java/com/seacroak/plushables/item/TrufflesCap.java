package com.seacroak.plushables.item;


import com.seacroak.plushables.client.renderer.item.BeauxCapRenderer;
import com.seacroak.plushables.client.renderer.item.TrufflesCapRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.ItemStack;
import software.bernie.geckolib.animatable.client.RenderProvider;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

import java.util.function.Consumer;

public class TrufflesCap extends CapArmorItem{
  public TrufflesCap() {
    super(ArmorMaterials.LEATHER, Type.HELMET);
  }

  @Override
  public void createRenderer(Consumer<Object> consumer) {
    consumer.accept(new RenderProvider() {
      private GeoArmorRenderer<?> renderer;

      @Override
      public BipedEntityModel<LivingEntity> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, BipedEntityModel<LivingEntity> original) {
        if (this.renderer == null)
          this.renderer = new TrufflesCapRenderer();
        this.renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);

        return this.renderer;
      }
    });
  }
}
