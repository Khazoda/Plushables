package com.khazoda.plushables.block;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.world.item.ItemStack;

import java.util.Set;

import static net.minecraft.core.component.DataComponents.*;

final class StoredItemComponentAllowlist {
  private static final Set<DataComponentType<?>> ALLOWED_COMPONENTS = Set.of(
      ATTRIBUTE_MODIFIERS,
      BANNER_PATTERNS,
      BASE_COLOR,
      BLOCK_STATE,
      CAN_BREAK,
      CAN_PLACE_ON,
      CUSTOM_MODEL_DATA,
      CUSTOM_NAME,
      DAMAGE,
      DYED_COLOR,
      ENCHANTMENT_GLINT_OVERRIDE,
      ENCHANTMENTS,
      FIREWORK_EXPLOSION,
      FIREWORKS,
      HIDE_ADDITIONAL_TOOLTIP,
      HIDE_TOOLTIP,
      INSTRUMENT,
      ITEM_NAME,
      JUKEBOX_PLAYABLE,
      LODESTONE_TRACKER,
      LORE,
      MAP_COLOR,
      MAP_DECORATIONS,
      MAP_ID,
      MAP_POST_PROCESSING,
      NOTE_BLOCK_SOUND,
      OMINOUS_BOTTLE_AMPLIFIER,
      POT_DECORATIONS,
      POTION_CONTENTS,
      RARITY,
      RECIPES,
      REPAIR_COST,
      STORED_ENCHANTMENTS,
      SUSPICIOUS_STEW_EFFECTS,
      TRIM,
      UNBREAKABLE,
      WRITABLE_BOOK_CONTENT,
      WRITTEN_BOOK_CONTENT
  );

  private StoredItemComponentAllowlist() {
  }

  static boolean allows(ItemStack stack) {
    return allows(stack, null);
  }

  static boolean allows(ItemStack stack, DataComponentType<?> extraAllowedComponent) {
    return stack.getComponentsPatch().entrySet().stream()
        .allMatch(entry -> ALLOWED_COMPONENTS.contains(entry.getKey()) || entry.getKey() == extraAllowedComponent);
  }
}
