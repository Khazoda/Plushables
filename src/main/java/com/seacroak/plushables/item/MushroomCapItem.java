package com.seacroak.plushables.item;


import com.seacroak.plushables.client.renderer.item.MushroomCapRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.ItemStack;
import software.bernie.geckolib.animatable.client.RenderProvider;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

import java.util.function.Consumer;

public class MushroomCapItem extends CapArmorItem{
  public MushroomCapItem() {
    super(ArmorMaterials.LEATHER, Type.HELMET);
  }

  @Override
  public void createRenderer(Consumer<Object> consumer) {
    consumer.accept(new RenderProvider() {
      private GeoArmorRenderer<?> renderer;

      @Override
      public BipedEntityModel<LivingEntity> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, BipedEntityModel<LivingEntity> original) {
        if (this.renderer == null)
          this.renderer = new MushroomCapRenderer();
        this.renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);

        return this.renderer;
      }
    });
  }
}
