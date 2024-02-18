package com.seacroak.plushables.datagen.advancements;

import com.seacroak.plushables.registry.MainRegistry;
import com.seacroak.plushables.util.GenericUtils;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.criterion.EnterBlockCriterion;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class PlushablesAdvancements implements Consumer<Consumer<AdvancementEntry>> {
  @Override
  public void accept(Consumer<AdvancementEntry> advancementConsumer) {
    AdvancementEntry rootAdvancement = Advancement.Builder.create()
        .display(
            MainRegistry.PENGUIN_PLUSHABLE,
            Text.translatable("advancement.plushables.root.title"),
            Text.translatable("advancement.plushables.root.description"),
            new Identifier("textures/gui/advancements/backgrounds/adventure.png"), // Background image used
            AdvancementFrame.TASK, // Options: TASK, CHALLENGE, GOAL
            false, // Show toast top right
            false, // Announce to chat
            false // Hidden in the advancement tab
        )
        // The first string used in criterion is the name referenced by other advancements when they want to have 'requirements'
        .criterion("root", EnterBlockCriterion.Conditions.block(Blocks.AIR))
        .rewards(AdvancementRewards.Builder.loot(new Identifier("plushables:grant_plushables_codex")))
        .build(advancementConsumer, "plushables" + "/root");

    AdvancementEntry gotCopperIngotAdvancement = Advancement.Builder.create().parent(rootAdvancement)
        .display(
            Items.COPPER_INGOT,
            Text.translatable("advancement.plushables.got_copper_ingot.title"),
            Text.translatable("advancement.plushables.got_copper_ingot.description"),
            null,
            AdvancementFrame.TASK, // Options: TASK, CHALLENGE, GOAL
            true, // Show toast top right
            false, // Announce to chat
            false // Hidden in the advancement tab
        )
        // The first string used in criterion is the name referenced by other advancements when they want to have 'requirements'
        .criterion("got_copper_ingot", InventoryChangedCriterion.Conditions.items(Items.COPPER_INGOT))
        .rewards(AdvancementRewards.Builder.recipe(GenericUtils.ID("builder_block")))
        .build(advancementConsumer, "plushables" + "/got_copper_ingot");

    AdvancementEntry gotBuilderAdvancement = Advancement.Builder.create().parent(gotCopperIngotAdvancement)
        .display(
            MainRegistry.BUILDER_BLOCK,
            Text.translatable("advancement.plushables.got_builder.title"),
            Text.translatable("advancement.plushables.got_builder.description"),
            null,
            AdvancementFrame.TASK, // Options: TASK, CHALLENGE, GOAL
            false, // Show toast top right
            true, // Announce to chat
            false // Hidden in the advancement tab
        )
        // The first string used in criterion is the name referenced by other advancements when they want to have 'requirements'
        .criterion("got_builder", InventoryChangedCriterion.Conditions.items(MainRegistry.BUILDER_BLOCK))
        .rewards(AdvancementRewards.Builder.recipe(GenericUtils.ID("heart_of_gold")).addRecipe(GenericUtils.ID("powered_heart")))
        .build(advancementConsumer, "plushables" + "/got_builder");

    AdvancementEntry gotHeartAdvancement = Advancement.Builder.create().parent(gotBuilderAdvancement)
        .display(
            MainRegistry.HEART_OF_GOLD,
            Text.translatable("advancement.plushables.got_heart.title"),
            Text.translatable("advancement.plushables.got_heart.description"),
            null,
            AdvancementFrame.TASK, // Options: TASK, CHALLENGE, GOAL
            true, // Show toast top right
            true, // Announce to chat
            false // Hidden in the advancement tab
        )
        // The first string used in criterion is the name referenced by other advancements when they want to have 'requirements'
        .criterion("got_heart", InventoryChangedCriterion.Conditions.items(MainRegistry.HEART_OF_GOLD))
        .build(advancementConsumer, "plushables" + "/got_heart");
  }
}
