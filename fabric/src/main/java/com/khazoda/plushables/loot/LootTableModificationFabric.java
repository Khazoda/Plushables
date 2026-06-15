package com.khazoda.plushables.loot;

import com.khazoda.plushables.registry.MainRegistry;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import static com.khazoda.plushables.registry.LootTableRegistry.DUNGEON_LOOT_TABLES_FABRIC;
import static com.khazoda.plushables.registry.LootTableRegistry.VILLAGE_LOOT_TABLES_FABRIC;

public class LootTableModificationFabric {
  public static void init() {
    LootTableEvents.MODIFY.register((id, tableBuilder, source, registryLookup) -> {
      if (BuiltInLootTables.RUINED_PORTAL.equals(id)) {
        LootPool.Builder pool = LootPool.lootPool()
            .setRolls(ConstantValue.exactly(1.0F))
            .when(LootItemRandomChanceCondition.randomChance(1f))
            .add(LootItem.lootTableItem(MainRegistry.HEART_OF_GOLD_ITEM.get()).setWeight(1));
        tableBuilder.withPool(pool);
      }
      if (VILLAGE_LOOT_TABLES_FABRIC.contains(id)) {
        LootPool.Builder pool = LootPool.lootPool()
            .setRolls(ConstantValue.exactly(1.0F))
            .when(LootItemRandomChanceCondition.randomChance(0.75f));
        for (MainRegistry.PlushableEntry plushie : MainRegistry.PLUSHABLE_LIST) {
          pool.add(LootItem.lootTableItem(plushie.getItem()).setWeight(1));
        }
        tableBuilder.withPool(pool);
      } else if (DUNGEON_LOOT_TABLES_FABRIC.contains(id)) {
        LootPool.Builder pool = LootPool.lootPool()
            .setRolls(ConstantValue.exactly(1.0F))
            .when(LootItemRandomChanceCondition.randomChance(0.75f))
            .add(LootItem.lootTableItem(MainRegistry.HEART_OF_GOLD_ITEM.get()).setWeight(1));
        tableBuilder.withPool(pool);
      }
    });
  }
}