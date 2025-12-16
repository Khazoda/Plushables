package com.khazoda.plushables.loot;

import com.khazoda.plushables.Constants;
import com.khazoda.plushables.item.PlushableBlockItem;
import com.khazoda.plushables.registry.MainRegistry;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.LootTableLoadEvent;

import java.util.function.Supplier;

import static com.khazoda.plushables.registry.LootTableRegistry.DUNGEON_LOOT_TABLES_NORGE;
import static com.khazoda.plushables.registry.LootTableRegistry.VILLAGE_LOOT_TABLES_NORGE;

public class LootTableModificationNorge {
  @EventBusSubscriber(modid = Constants.MOD_ID)
  public static class EventHandler {
    @SubscribeEvent
    public static void onLootTableLoadEvent(LootTableLoadEvent event) {
      if (BuiltInLootTables.RUINED_PORTAL.identifier().equals(event.getName())) {
        LootPool.Builder pool = LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(LootItemRandomChanceCondition.randomChance(1f)).add(LootItem.lootTableItem(MainRegistry.HEART_OF_GOLD_ITEM.get()).setWeight(1));
        event.getTable().addPool(pool.build());
      }
      if (VILLAGE_LOOT_TABLES_NORGE.contains(event.getName())) {
        LootPool.Builder pool = LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(LootItemRandomChanceCondition.randomChance(0.75f));
        for (Supplier<PlushableBlockItem> plushie : MainRegistry.PLUSHABLE_LIST) {
          pool.add(LootItem.lootTableItem(plushie.get()).setWeight(1));
        }
        event.getTable().addPool(pool.build());
      } else if (DUNGEON_LOOT_TABLES_NORGE.contains(event.getName())) {
        LootPool.Builder pool = LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(LootItemRandomChanceCondition.randomChance(0.75f)).add(LootItem.lootTableItem(MainRegistry.HEART_OF_GOLD_ITEM.get()).setWeight(1));
        event.getTable().addPool(pool.build());
      }
    }
  }
}
