package com.khazoda.plushables.registry;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.List;

public class LootTableRegistry {
  /* ==========[  Plushie Loot Tables  ]========== */
  public static final List<ResourceKey<LootTable>> VILLAGE_LOOT_TABLES_FABRIC = List.of(
      BuiltInLootTables.VILLAGE_DESERT_HOUSE,
      BuiltInLootTables.VILLAGE_PLAINS_HOUSE,
      BuiltInLootTables.VILLAGE_SAVANNA_HOUSE,
      BuiltInLootTables.VILLAGE_SNOWY_HOUSE,
      BuiltInLootTables.VILLAGE_TAIGA_HOUSE,
      BuiltInLootTables.SPAWN_BONUS_CHEST,
      BuiltInLootTables.VILLAGE_ARMORER,
      BuiltInLootTables.VILLAGE_BUTCHER,
      BuiltInLootTables.VILLAGE_TANNERY,
      BuiltInLootTables.VILLAGE_TEMPLE,
      BuiltInLootTables.VILLAGE_TOOLSMITH,
      BuiltInLootTables.VILLAGE_WEAPONSMITH
  );
  public static final List<Identifier> VILLAGE_LOOT_TABLES_NORGE = List.of(
      BuiltInLootTables.VILLAGE_DESERT_HOUSE.identifier(),
      BuiltInLootTables.VILLAGE_PLAINS_HOUSE.identifier(),
      BuiltInLootTables.VILLAGE_SAVANNA_HOUSE.identifier(),
      BuiltInLootTables.VILLAGE_SNOWY_HOUSE.identifier(),
      BuiltInLootTables.VILLAGE_TAIGA_HOUSE.identifier(),
      BuiltInLootTables.SPAWN_BONUS_CHEST.identifier(),
      BuiltInLootTables.VILLAGE_ARMORER.identifier(),
      BuiltInLootTables.VILLAGE_BUTCHER.identifier(),
      BuiltInLootTables.VILLAGE_TANNERY.identifier(),
      BuiltInLootTables.VILLAGE_TEMPLE.identifier(),
      BuiltInLootTables.VILLAGE_TOOLSMITH.identifier(),
      BuiltInLootTables.VILLAGE_WEAPONSMITH.identifier()
  );

  /* ==========[  Heart of Gold Loot Tables]========== */
  public static final List<ResourceKey<LootTable>> DUNGEON_LOOT_TABLES_FABRIC = List.of(
      BuiltInLootTables.ABANDONED_MINESHAFT,
      BuiltInLootTables.BASTION_HOGLIN_STABLE,
      BuiltInLootTables.BASTION_TREASURE,
      BuiltInLootTables.BURIED_TREASURE,
      BuiltInLootTables.DESERT_PYRAMID,
      BuiltInLootTables.END_CITY_TREASURE,
      BuiltInLootTables.JUNGLE_TEMPLE,
      BuiltInLootTables.NETHER_BRIDGE,
      BuiltInLootTables.PILLAGER_OUTPOST,
      BuiltInLootTables.RUINED_PORTAL,
      BuiltInLootTables.SHIPWRECK_TREASURE,
      BuiltInLootTables.STRONGHOLD_CORRIDOR,
      BuiltInLootTables.UNDERWATER_RUIN_BIG,
      BuiltInLootTables.WOODLAND_MANSION,
      BuiltInLootTables.IGLOO_CHEST,
      BuiltInLootTables.ANCIENT_CITY_ICE_BOX,
      BuiltInLootTables.ANCIENT_CITY,
      BuiltInLootTables.SIMPLE_DUNGEON
  );
  public static final List<Identifier> DUNGEON_LOOT_TABLES_NORGE = List.of(
      BuiltInLootTables.ABANDONED_MINESHAFT.identifier(),
      BuiltInLootTables.BASTION_HOGLIN_STABLE.identifier(),
      BuiltInLootTables.BASTION_TREASURE.identifier(),
      BuiltInLootTables.BURIED_TREASURE.identifier(),
      BuiltInLootTables.DESERT_PYRAMID.identifier(),
      BuiltInLootTables.END_CITY_TREASURE.identifier(),
      BuiltInLootTables.JUNGLE_TEMPLE.identifier(),
      BuiltInLootTables.NETHER_BRIDGE.identifier(),
      BuiltInLootTables.PILLAGER_OUTPOST.identifier(),
      BuiltInLootTables.RUINED_PORTAL.identifier(),
      BuiltInLootTables.SHIPWRECK_TREASURE.identifier(),
      BuiltInLootTables.STRONGHOLD_CORRIDOR.identifier(),
      BuiltInLootTables.UNDERWATER_RUIN_BIG.identifier(),
      BuiltInLootTables.WOODLAND_MANSION.identifier(),
      BuiltInLootTables.IGLOO_CHEST.identifier(),
      BuiltInLootTables.ANCIENT_CITY_ICE_BOX.identifier(),
      BuiltInLootTables.ANCIENT_CITY.identifier(),
      BuiltInLootTables.SIMPLE_DUNGEON.identifier()
  );
}
