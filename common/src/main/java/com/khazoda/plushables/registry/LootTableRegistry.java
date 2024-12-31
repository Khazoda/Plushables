package com.khazoda.plushables.registry;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
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
  public static final List<ResourceLocation> VILLAGE_LOOT_TABLES_NORGE = List.of(
      BuiltInLootTables.VILLAGE_DESERT_HOUSE.location(),
      BuiltInLootTables.VILLAGE_PLAINS_HOUSE.location(),
      BuiltInLootTables.VILLAGE_SAVANNA_HOUSE.location(),
      BuiltInLootTables.VILLAGE_SNOWY_HOUSE.location(),
      BuiltInLootTables.VILLAGE_TAIGA_HOUSE.location(),
      BuiltInLootTables.SPAWN_BONUS_CHEST.location(),
      BuiltInLootTables.VILLAGE_ARMORER.location(),
      BuiltInLootTables.VILLAGE_BUTCHER.location(),
      BuiltInLootTables.VILLAGE_TANNERY.location(),
      BuiltInLootTables.VILLAGE_TEMPLE.location(),
      BuiltInLootTables.VILLAGE_TOOLSMITH.location(),
      BuiltInLootTables.VILLAGE_WEAPONSMITH.location()
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
  public static final List<ResourceLocation> DUNGEON_LOOT_TABLES_NORGE = List.of(
      BuiltInLootTables.ABANDONED_MINESHAFT.location(),
      BuiltInLootTables.BASTION_HOGLIN_STABLE.location(),
      BuiltInLootTables.BASTION_TREASURE.location(),
      BuiltInLootTables.BURIED_TREASURE.location(),
      BuiltInLootTables.DESERT_PYRAMID.location(),
      BuiltInLootTables.END_CITY_TREASURE.location(),
      BuiltInLootTables.JUNGLE_TEMPLE.location(),
      BuiltInLootTables.NETHER_BRIDGE.location(),
      BuiltInLootTables.PILLAGER_OUTPOST.location(),
      BuiltInLootTables.RUINED_PORTAL.location(),
      BuiltInLootTables.SHIPWRECK_TREASURE.location(),
      BuiltInLootTables.STRONGHOLD_CORRIDOR.location(),
      BuiltInLootTables.UNDERWATER_RUIN_BIG.location(),
      BuiltInLootTables.WOODLAND_MANSION.location(),
      BuiltInLootTables.IGLOO_CHEST.location(),
      BuiltInLootTables.ANCIENT_CITY_ICE_BOX.location(),
      BuiltInLootTables.ANCIENT_CITY.location(),
      BuiltInLootTables.SIMPLE_DUNGEON.location()
  );
}
