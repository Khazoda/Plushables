package com.seacroak.plushables.item;

import com.seacroak.plushables.registry.MainRegistry;
import net.minecraft.Util;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.function.Supplier;

public enum CapMaterials implements ArmorMaterial {

  CAP_MATERIAL("cap_material", 5, Util.make(new EnumMap<>(ArmorItem.Type.class), (properties) -> {
    properties.put(ArmorItem.Type.BOOTS, 1);
    properties.put(ArmorItem.Type.LEGGINGS, 2);
    properties.put(ArmorItem.Type.CHESTPLATE, 3);
    properties.put(ArmorItem.Type.HELMET, 1);
  }), 15, SoundEvents.ARMOR_EQUIP_TURTLE, 0.0F, 0.1F, () -> {
    return Ingredient.of(MainRegistry.HEART_OF_GOLD.get());
  });

  public static final StringRepresentable.EnumCodec<ArmorMaterials> CODEC = StringRepresentable.fromEnum(ArmorMaterials::values);
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
  private final LazyLoadedValue<Ingredient> repairIngredient;

  private CapMaterials(String pName, int pDurabilityMultiplier, EnumMap<ArmorItem.Type, Integer> pProtectionFunctionForType, int pEnchantmentValue, SoundEvent pSound, float pToughness, float pKnockbackResistance, Supplier<Ingredient> pRepairIngredient) {
    this.name = pName;
    this.durabilityMultiplier = pDurabilityMultiplier;
    this.protectionFunctionForType = pProtectionFunctionForType;
    this.enchantmentValue = pEnchantmentValue;
    this.sound = pSound;
    this.toughness = pToughness;
    this.knockbackResistance = pKnockbackResistance;
    this.repairIngredient = new LazyLoadedValue<>(pRepairIngredient);
  }

  public int getDurabilityForType(ArmorItem.Type pType) {
    return HEALTH_FUNCTION_FOR_TYPE.get(pType) * this.durabilityMultiplier;
  }

  public int getDefenseForType(ArmorItem.Type pType) {
    return this.protectionFunctionForType.get(pType);
  }

  public int getEnchantmentValue() {
    return this.enchantmentValue;
  }

  public SoundEvent getEquipSound() {
    return this.sound;
  }

  public Ingredient getRepairIngredient() {
    return this.repairIngredient.get();
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
}