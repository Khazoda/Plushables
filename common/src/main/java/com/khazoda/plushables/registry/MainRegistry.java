package com.khazoda.plushables.registry;

import com.khazoda.plushables.PlushablesCommon;
import com.khazoda.plushables.block.BasePlushable;
import com.khazoda.plushables.block.plushable.*;
import com.khazoda.plushables.item.PlushableBlockItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

import static com.khazoda.plushables.Constants.ID;

public class MainRegistry {
  private static final Reggie<Block> BLOCK_REGISTRAR = PlushablesCommon.REGISTRARS.get(Registries.BLOCK);
  private static final Reggie<Item> ITEM_REGISTRAR = PlushablesCommon.REGISTRARS.get(Registries.ITEM);

  /* ==========[ Item Registration ]========== */
  public static final Supplier<Item> HEART_OF_GOLD_ITEM = ITEM_REGISTRAR.register("heart_of_gold", () -> new Item(new Item.Properties()));

  /* ==========[ Block Registration ]========== */
  public static final Supplier<BasePlushable> PLUSHABLE_ANIMATRONIC_BLOCK = BLOCK_REGISTRAR.register("plushable_animatronic", PlushableAnimatronicBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_BEAUX_BLOCK = BLOCK_REGISTRAR.register("plushable_beaux", PlushableBeauxBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_BIG_IRRITATER_BLOCK = BLOCK_REGISTRAR.register("plushable_big_irritater", PlushableBigIrritaterBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_BIG_TATER_BLOCK = BLOCK_REGISTRAR.register("plushable_big_tater", PlushableBigTaterBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_BLAHAJ_BLOCK = BLOCK_REGISTRAR.register("plushable_blahaj", PlushableBlahajBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_CLUCKY_BLOCK = BLOCK_REGISTRAR.register("plushable_clucky", PlushableCluckyBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_CONDUCTOR_BLOCK = BLOCK_REGISTRAR.register("plushable_conductor", PlushableConductorBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_COOPER_BLOCK = BLOCK_REGISTRAR.register("plushable_cooper", PlushableCooperBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_DJUNGELSKOG_BLOCK = BLOCK_REGISTRAR.register("plushable_djungelskog", PlushableDjungelskogBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_DORMOUSE_BLOCK = BLOCK_REGISTRAR.register("plushable_dormouse", PlushableDormouseBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_DRAGON_BLOCK = BLOCK_REGISTRAR.register("plushable_dragon", PlushableDragonBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_FOX_BLOCK = BLOCK_REGISTRAR.register("plushable_fox", PlushableFoxBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_FROGE_BLOCK = BLOCK_REGISTRAR.register("plushable_froge", PlushableFrogeBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_FROGLIN_BLOCK = BLOCK_REGISTRAR.register("plushable_froglin", PlushableFroglinBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_GOBLIN_BLOCK = BLOCK_REGISTRAR.register("plushable_goblin", PlushableGoblinBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_GOLDFISH_BLOCK = BLOCK_REGISTRAR.register("plushable_goldfish", PlushableGoldfishBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_HAMSTER_BLOCK = BLOCK_REGISTRAR.register("plushable_hamster", PlushableHamsterBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_MAMMOTH_BLOCK = BLOCK_REGISTRAR.register("plushable_mammoth", PlushableMammothBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_MOOBLOOM_BLOCK = BLOCK_REGISTRAR.register("plushable_moobloom", PlushableMoobloomBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_OCTOPLUSHABLE_BLOCK = BLOCK_REGISTRAR.register("plushable_octoplushable", PlushableOctoplushableBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_ORANGUTAN_BLOCK = BLOCK_REGISTRAR.register("plushable_orangutan", PlushableOrangutanBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_OTTER_BLOCK = BLOCK_REGISTRAR.register("plushable_otter", PlushableOtterBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_OWL_BLOCK = BLOCK_REGISTRAR.register("plushable_owl", PlushableOwlBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_PENGUIN_BLOCK = BLOCK_REGISTRAR.register("plushable_penguin", PlushablePenguinBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_PIG_BLOCK = BLOCK_REGISTRAR.register("plushable_pig", PlushablePigBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_POTSY_BLOCK = BLOCK_REGISTRAR.register("plushable_potsy", PlushablePotsyBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_RAPTOR_BLOCK = BLOCK_REGISTRAR.register("plushable_raptor", PlushableRaptorBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_RATTIAM_BLOCK = BLOCK_REGISTRAR.register("plushable_rattiam", PlushableRattiamBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_RUPERT_BLOCK = BLOCK_REGISTRAR.register("plushable_rupert", PlushableRupertBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_SEA_BUNNY_BLOCK = BLOCK_REGISTRAR.register("plushable_sea_bunny", PlushableSeaBunnyBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_SHRUMP_BLOCK = BLOCK_REGISTRAR.register("plushable_shrump", PlushableShrumpBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_SNAIL_BLOCK = BLOCK_REGISTRAR.register("plushable_snail", PlushableSnailBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_STATUETTE_BLOCK = BLOCK_REGISTRAR.register("plushable_statuette", PlushableStatuetteBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_TIGER_BLOCK = BLOCK_REGISTRAR.register("plushable_tiger", PlushableTigerBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_TRATER_BLOCK = BLOCK_REGISTRAR.register("plushable_trater", PlushableTraterBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_TRICERATOPS_BLOCK = BLOCK_REGISTRAR.register("plushable_triceratops", PlushableTriceratopsBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_TRUFFLES_BLOCK = BLOCK_REGISTRAR.register("plushable_truffles", PlushableTrufflesBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_UNICORN_BLOCK = BLOCK_REGISTRAR.register("plushable_unicorn", PlushableUnicornBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_WALRUS_BLOCK = BLOCK_REGISTRAR.register("plushable_walrus", PlushableWalrusBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_WHALE_BLOCK = BLOCK_REGISTRAR.register("plushable_whale", PlushableWhaleBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_WHELPLING_BLOCK = BLOCK_REGISTRAR.register("plushable_whelpling", PlushableWhelplingBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_WISP_BLOCK = BLOCK_REGISTRAR.register("plushable_wisp", PlushableWispBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_WIZARD_BLOCK = BLOCK_REGISTRAR.register("plushable_wizard", PlushableWizardBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_ZIGGY_BLOCK = BLOCK_REGISTRAR.register("plushable_ziggy", PlushableZiggyBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_SNOWIE_BLOCK = BLOCK_REGISTRAR.register("plushable_snowie", PlushableSnowieBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_RIBBIT_BLOCK = BLOCK_REGISTRAR.register("plushable_ribbit", PlushableRibbitBlock::new);

  /* ==========[ BlockItem Registration ]========== */
  public static final Supplier<PlushableBlockItem> PLUSHABLE_ANIMATRONIC_ITEM = register("plushable_animatronic", PLUSHABLE_ANIMATRONIC_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_BEAUX_ITEM = register("plushable_beaux", PLUSHABLE_BEAUX_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_BIG_IRRITATER_ITEM = register("plushable_big_irritater", PLUSHABLE_BIG_IRRITATER_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_BIG_TATER_ITEM = register("plushable_big_tater", PLUSHABLE_BIG_TATER_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_BLAHAJ_ITEM = register("plushable_blahaj", PLUSHABLE_BLAHAJ_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_CLUCKY_ITEM = register("plushable_clucky", PLUSHABLE_CLUCKY_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_CONDUCTOR_ITEM = register("plushable_conductor", PLUSHABLE_CONDUCTOR_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_COOPER_ITEM = register("plushable_cooper", PLUSHABLE_COOPER_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_DJUNGELSKOG_ITEM = register("plushable_djungelskog", PLUSHABLE_DJUNGELSKOG_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_DORMOUSE_ITEM = register("plushable_dormouse", PLUSHABLE_DORMOUSE_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_DRAGON_ITEM = register("plushable_dragon", PLUSHABLE_DRAGON_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_FOX_ITEM = register("plushable_fox", PLUSHABLE_FOX_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_FROGE_ITEM = register("plushable_froge", PLUSHABLE_FROGE_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_FROGLIN_ITEM = register("plushable_froglin", PLUSHABLE_FROGLIN_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_GOBLIN_ITEM = register("plushable_goblin", PLUSHABLE_GOBLIN_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_GOLDFISH_ITEM = register("plushable_goldfish", PLUSHABLE_GOLDFISH_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_HAMSTER_ITEM = register("plushable_hamster", PLUSHABLE_HAMSTER_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_MAMMOTH_ITEM = register("plushable_mammoth", PLUSHABLE_MAMMOTH_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_MOOBLOOM_ITEM = register("plushable_moobloom", PLUSHABLE_MOOBLOOM_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_OCTOPLUSHABLE_ITEM = register("plushable_octoplushable", PLUSHABLE_OCTOPLUSHABLE_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_ORANGUTAN_ITEM = register("plushable_orangutan", PLUSHABLE_ORANGUTAN_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_OTTER_ITEM = register("plushable_otter", PLUSHABLE_OTTER_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_OWL_ITEM = register("plushable_owl", PLUSHABLE_OWL_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_PENGUIN_ITEM = register("plushable_penguin", PLUSHABLE_PENGUIN_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_PIG_ITEM = register("plushable_pig", PLUSHABLE_PIG_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_POTSY_ITEM = register("plushable_potsy", PLUSHABLE_POTSY_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_RAPTOR_ITEM = register("plushable_raptor", PLUSHABLE_RAPTOR_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_RATTIAM_ITEM = register("plushable_rattiam", PLUSHABLE_RATTIAM_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_RUPERT_ITEM = register("plushable_rupert", PLUSHABLE_RUPERT_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_SEA_BUNNY_ITEM = register("plushable_sea_bunny", PLUSHABLE_SEA_BUNNY_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_SHRUMP_ITEM = register("plushable_shrump", PLUSHABLE_SHRUMP_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_SNAIL_ITEM = register("plushable_snail", PLUSHABLE_SNAIL_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_STATUETTE_ITEM = register("plushable_statuette", PLUSHABLE_STATUETTE_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_TIGER_ITEM = register("plushable_tiger", PLUSHABLE_TIGER_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_TRATER_ITEM = register("plushable_trater", PLUSHABLE_TRATER_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_TRICERATOPS_ITEM = register("plushable_triceratops", PLUSHABLE_TRICERATOPS_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_TRUFFLES_ITEM = register("plushable_truffles", PLUSHABLE_TRUFFLES_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_UNICORN_ITEM = register("plushable_unicorn", PLUSHABLE_UNICORN_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_WALRUS_ITEM = register("plushable_walrus", PLUSHABLE_WALRUS_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_WHALE_ITEM = register("plushable_whale", PLUSHABLE_WHALE_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_WHELPLING_ITEM = register("plushable_whelpling", PLUSHABLE_WHELPLING_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_WISP_ITEM = register("plushable_wisp", PLUSHABLE_WISP_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_WIZARD_ITEM = register("plushable_wizard", PLUSHABLE_WIZARD_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_ZIGGY_ITEM = register("plushable_ziggy", PLUSHABLE_ZIGGY_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_SNOWIE_ITEM = register("plushable_snowie", PLUSHABLE_SNOWIE_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_RIBBIT_ITEM = register("plushable_ribbit", PLUSHABLE_RIBBIT_BLOCK);


  public static void init() {
  }

  private static Supplier<PlushableBlockItem> register(String name, Supplier<BasePlushable> block) {
    return ITEM_REGISTRAR.register(name, () -> new PlushableBlockItem(block.get(), new Item.Properties().attributes(ItemAttributeModifiers.builder().add(Attributes.ARMOR, new AttributeModifier(ID("plushie_head_wearable"), 1.0D, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.HEAD).build())));
  }
}
