package com.seacroak.plushables.registry;

import com.seacroak.plushables.PlushablesMod;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ItemRegistry {

	public static ItemGroup plushablesItemGroup = FabricItemGroupBuilder
			.create(new Identifier(PlushablesMod.ModID, "plushables"))
			.icon(() -> new ItemStack(ItemRegistry.BUILDER)).build();

	public static final BlockItem BUILDER = RegistryUtils.registerItem("builder",
			new BlockItem(BlockRegistry.BUILDER_BLOCK, new Item.Settings().group(plushablesItemGroup)));

	public static final BlockItem PENGUIN = RegistryUtils.registerItem("penguin",
			new BlockItem(BlockRegistry.PENGUIN_BLOCK, new Item.Settings().group(plushablesItemGroup)));

	public static final BlockItem FOX = RegistryUtils.registerItem("fox",
			new BlockItem(BlockRegistry.FOX_BLOCK, new Item.Settings().group(plushablesItemGroup)));
	// public static final JackInTheBoxItem JACK_IN_THE_BOX =
	// RegistryUtils.registerItem("jackintheboxitem",
	// new JackInTheBoxItem(new Item.Settings().group(geckolibItemGroup)));
	// public static final PistolItem PISTOL = RegistryUtils.registerItem("pistol",
	// new PistolItem());
	// public static final PotatoArmorItem POTATO_HEAD =
	// RegistryUtils.registerItem("potato_head", new PotatoArmorItem(
	// ArmorMaterials.DIAMOND, EquipmentSlot.HEAD, new
	// Item.Settings().group(geckolibItemGroup)));
	// public static final PotatoArmorItem POTATO_CHEST =
	// RegistryUtils.registerItem("potato_chest", new PotatoArmorItem(
	// ArmorMaterials.DIAMOND, EquipmentSlot.CHEST, new
	// Item.Settings().group(geckolibItemGroup)));
	// public static final PotatoArmorItem POTATO_LEGGINGS =
	// RegistryUtils.registerItem("potato_leggings",
	// new PotatoArmorItem(ArmorMaterials.DIAMOND, EquipmentSlot.LEGS,
	// new Item.Settings().group(geckolibItemGroup)));
	// public static final PotatoArmorItem POTATO_BOOTS =
	// RegistryUtils.registerItem("potato_boots", new PotatoArmorItem(
	// ArmorMaterials.DIAMOND, EquipmentSlot.FEET, new
	// Item.Settings().group(geckolibItemGroup)));
	// public static final BlockItem HABITAT = RegistryUtils.registerItem("habitat",
	// new BlockItem(BlockRegistry.HABITAT_BLOCK, new
	// Item.Settings().group(geckolibItemGroup)));
	// public static final BlockItem FERTILIZER =
	// RegistryUtils.registerItem("fertilizer",
	// new BlockItem(BlockRegistry.FERTILIZER_BLOCK, new
	// Item.Settings().group(geckolibItemGroup)));

}
