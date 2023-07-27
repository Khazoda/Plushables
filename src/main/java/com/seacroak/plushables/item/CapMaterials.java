package com.seacroak.plushables.item;

import com.seacroak.plushables.registry.MainRegistry;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Lazy;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.Util;

import java.util.EnumMap;
import java.util.function.Supplier;

public enum CapMaterials implements ArmorMaterial {

  CAP_MATERIAL("cap_material", 5, Util.make(new EnumMap<>(ArmorItem.Type.class), (properties) -> {
    properties.put(ArmorItem.Type.BOOTS, 1);
    properties.put(ArmorItem.Type.LEGGINGS, 2);
    properties.put(ArmorItem.Type.CHESTPLATE, 3);
    properties.put(ArmorItem.Type.HELMET, 1);
  }), 15, SoundEvents.ITEM_ARMOR_EQUIP_TURTLE, 0.0F, 0.1F, () -> {
    return Ingredient.ofItems(new ItemConvertible[]{MainRegistry.HEART_OF_GOLD});
  });

  public static final StringIdentifiable.Codec<ArmorMaterials> CODEC = StringIdentifiable.createCodec(ArmorMaterials::values);
  private static final EnumMap<ArmorItem.Type, Integer> HEALTH_FUNCTION_FOR_TYPE = Util.make(new EnumMap<>(ArmorItem.Type.class), (properties) -> {
    properties.put(ArmorItem.Type.BOOTS, 1);
    properties.put(ArmorItem.Type.LEGGINGS, 2);
    properties.put(ArmorItem.Type.CHESTPLATE, 3);
    properties.put(ArmorItem.Type.HELMET, 1);
  });
  private final String name;
  private final int durabilityMultiplier;
  private final EnumMap<ArmorItem.Type, Integer> protectionFunctionForType;
  private final int enchantmentValue;
  private final SoundEvent sound;
  private final float toughness;
  private final float knockbackResistance;
  private final Lazy<Ingredient> repairIngredientSupplier;

  private CapMaterials(String pName, int pDurabilityMultiplier, EnumMap<ArmorItem.Type, Integer> pProtectionFunctionForType, int pEnchantmentValue, SoundEvent pSound, float pToughness, float pKnockbackResistance, Supplier<Ingredient> repairIngredientSupplier) {
    this.name = pName;
    this.durabilityMultiplier = pDurabilityMultiplier;
    this.protectionFunctionForType = pProtectionFunctionForType;
    this.enchantmentValue = pEnchantmentValue;
    this.sound = pSound;
    this.toughness = pToughness;
    this.knockbackResistance = pKnockbackResistance;
    this.repairIngredientSupplier = new Lazy(repairIngredientSupplier);
  }

  public SoundEvent getEquipSound() {
    return this.sound;
  }

  public Ingredient getRepairIngredient() {
    return (Ingredient)this.repairIngredientSupplier.get();
  }

  public String getName() {
    return this.name;
  }

  public float getToughness() {
    return this.toughness;
  }

  public float getKnockbackResistance() {
    return this.knockbackResistance;
  }

  public String getSerializedName() {
    return this.name;
  }


  @Override
  public int getDurability(ArmorItem.Type type) {
    return HEALTH_FUNCTION_FOR_TYPE.get(type) * this.durabilityMultiplier;
  }

  @Override
  public int getProtection(ArmorItem.Type type) {
    return this.protectionFunctionForType.get(type);
  }

  @Override
  public int getEnchantability() {
    return this.enchantmentValue;
  }
}