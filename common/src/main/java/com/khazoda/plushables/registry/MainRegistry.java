package com.khazoda.plushables.registry;

import com.khazoda.plushables.PlushablesCommon;
import com.khazoda.plushables.block.BasePlushable;
import com.khazoda.plushables.block.plushable.*;
import com.khazoda.plushables.item.PlushableBlockItem;
import com.khazoda.plushables.registry.helper.Reggie;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.Equippable;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.khazoda.plushables.Constants.ID;

public class MainRegistry {
  private static final Reggie<Block> BLOCK_REGISTRAR = PlushablesCommon.REGISTRARS.get(Registries.BLOCK);
  private static final Reggie<Item> ITEM_REGISTRAR = PlushablesCommon.REGISTRARS.get(Registries.ITEM);

  /* ==========[ List of All Plushables (used in loot table iteration) ]========== */
  public static final List<Supplier<PlushableBlockItem>> PLUSHABLE_LIST = new ArrayList<>();

  /* ==========[ Item Registration ]========== */
  public static final Supplier<Item> HEART_OF_GOLD_ITEM = ITEM_REGISTRAR.register("heart_of_gold", () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ID("heart_of_gold")))));

  /* ==========[ Block Registration ]========== */
  public static final Supplier<BasePlushable> PLUSHABLE_PENGUIN_BLOCK = registerBlock("plushable_penguin", PlushablePenguinBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_FROGLIN_BLOCK = registerBlock("plushable_froglin", PlushableFroglinBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_FOX_BLOCK = registerBlock("plushable_fox", PlushableFoxBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_CLUCKY_BLOCK = registerBlock("plushable_clucky", PlushableCluckyBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_PIG_BLOCK = registerBlock("plushable_pig", PlushablePigBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_TRUFFLES_BLOCK = registerBlock("plushable_truffles", PlushableTrufflesBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_DJUNGELSKOG_BLOCK = registerBlock("plushable_djungelskog", PlushableDjungelskogBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_RATTIAM_BLOCK = registerBlock("plushable_rattiam", PlushableRattiamBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_TRICERATOPS_BLOCK = registerBlock("plushable_triceratops", PlushableTriceratopsBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_UNICORN_BLOCK = registerBlock("plushable_unicorn", PlushableUnicornBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_WHELPLING_BLOCK = registerBlock("plushable_whelpling", PlushableWhelplingBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_RAPTOR_BLOCK = registerBlock("plushable_raptor", PlushableRaptorBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_RUPERT_BLOCK = registerBlock("plushable_rupert", PlushableRupertBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_DRAGON_BLOCK = registerBlock("plushable_dragon", PlushableDragonBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_WIZARD_BLOCK = registerBlock("plushable_wizard", PlushableWizardBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_BEAUX_BLOCK = registerBlock("plushable_beaux", PlushableBeauxBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_GOBLIN_BLOCK = registerBlock("plushable_goblin", PlushableGoblinBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_BIG_TATER_BLOCK = registerBlock("plushable_big_tater", PlushableBigTaterBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_BIG_IRRITATER_BLOCK = registerBlock("plushable_big_irritater", PlushableBigIrritaterBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_OTTER_BLOCK = registerBlock("plushable_otter", PlushableOtterBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_SHRUMP_BLOCK = registerBlock("plushable_shrump", PlushableShrumpBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_OCTOPLUSHABLE_BLOCK = registerBlock("plushable_octoplushable", PlushableOctoplushableBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_SNAIL_BLOCK = registerBlock("plushable_snail", PlushableSnailBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_WHALE_BLOCK = registerBlock("plushable_whale", PlushableWhaleBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_ORANGUTAN_BLOCK = registerBlock("plushable_orangutan", PlushableOrangutanBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_GOLDFISH_BLOCK = registerBlock("plushable_goldfish", PlushableGoldfishBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_TRATER_BLOCK = registerBlock("plushable_trater", PlushableTraterBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_CONDUCTOR_BLOCK = registerBlock("plushable_conductor", PlushableConductorBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_ANIMATRONIC_BLOCK = registerBlock("plushable_animatronic", PlushableAnimatronicBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_MOOBLOOM_BLOCK = registerBlock("plushable_moobloom", PlushableMoobloomBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_FROGE_BLOCK = registerBlock("plushable_froge", PlushableFrogeBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_OWL_BLOCK = registerBlock("plushable_owl", PlushableOwlBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_HAMSTER_BLOCK = registerBlock("plushable_hamster", PlushableHamsterBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_DORMOUSE_BLOCK = registerBlock("plushable_dormouse", PlushableDormouseBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_SEA_BUNNY_BLOCK = registerBlock("plushable_sea_bunny", PlushableSeaBunnyBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_STATUETTE_BLOCK = registerBlock("plushable_statuette", PlushableStatuetteBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_MAMMOTH_BLOCK = registerBlock("plushable_mammoth", PlushableMammothBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_TIGER_BLOCK = registerBlock("plushable_tiger", PlushableTigerBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_WALRUS_BLOCK = registerBlock("plushable_walrus", PlushableWalrusBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_BLAHAJ_BLOCK = registerBlock("plushable_blahaj", PlushableBlahajBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_WISP_BLOCK = registerBlock("plushable_wisp", PlushableWispBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_COOPER_BLOCK = registerBlock("plushable_cooper", PlushableCooperBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_ZIGGY_BLOCK = registerBlock("plushable_ziggy", PlushableZiggyBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_POTSY_BLOCK = registerBlock("plushable_potsy", PlushablePotsyBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_SNOWIE_BLOCK = registerBlock("plushable_snowie", PlushableSnowieBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_RIBBIT_BLOCK = registerBlock("plushable_ribbit", PlushableRibbitBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_CREAKY_BLOCK = registerBlock("plushable_creaky", PlushableCreakyBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_KWEEBEC_BLOCK = registerBlock("plushable_kweebec", PlushableKweebecBlock::new);
  public static final Supplier<BasePlushable> PLUSHABLE_STONELING_BLOCK = registerBlock("plushable_stoneling", PlushableStonelingBlock::new);

  /* ==========[ BlockItem Registration ]========== */
  public static final Supplier<PlushableBlockItem> PLUSHABLE_PENGUIN_ITEM = registerItem("plushable_penguin", PLUSHABLE_PENGUIN_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_FROGLIN_ITEM = registerItem("plushable_froglin", PLUSHABLE_FROGLIN_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_FOX_ITEM = registerItem("plushable_fox", PLUSHABLE_FOX_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_CLUCKY_ITEM = registerItem("plushable_clucky", PLUSHABLE_CLUCKY_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_PIG_ITEM = registerItem("plushable_pig", PLUSHABLE_PIG_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_TRUFFLES_ITEM = registerItem("plushable_truffles", PLUSHABLE_TRUFFLES_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_DJUNGELSKOG_ITEM = registerItem("plushable_djungelskog", PLUSHABLE_DJUNGELSKOG_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_RATTIAM_ITEM = registerItem("plushable_rattiam", PLUSHABLE_RATTIAM_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_TRICERATOPS_ITEM = registerItem("plushable_triceratops", PLUSHABLE_TRICERATOPS_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_UNICORN_ITEM = registerItem("plushable_unicorn", PLUSHABLE_UNICORN_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_WHELPLING_ITEM = registerItem("plushable_whelpling", PLUSHABLE_WHELPLING_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_RAPTOR_ITEM = registerItem("plushable_raptor", PLUSHABLE_RAPTOR_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_RUPERT_ITEM = registerItem("plushable_rupert", PLUSHABLE_RUPERT_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_DRAGON_ITEM = registerItem("plushable_dragon", PLUSHABLE_DRAGON_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_WIZARD_ITEM = registerItem("plushable_wizard", PLUSHABLE_WIZARD_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_BEAUX_ITEM = registerItem("plushable_beaux", PLUSHABLE_BEAUX_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_GOBLIN_ITEM = registerItem("plushable_goblin", PLUSHABLE_GOBLIN_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_BIG_TATER_ITEM = registerItem("plushable_big_tater", PLUSHABLE_BIG_TATER_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_BIG_IRRITATER_ITEM = registerItem("plushable_big_irritater", PLUSHABLE_BIG_IRRITATER_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_OTTER_ITEM = registerItem("plushable_otter", PLUSHABLE_OTTER_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_SHRUMP_ITEM = registerItem("plushable_shrump", PLUSHABLE_SHRUMP_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_OCTOPLUSHABLE_ITEM = registerItem("plushable_octoplushable", PLUSHABLE_OCTOPLUSHABLE_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_SNAIL_ITEM = registerItem("plushable_snail", PLUSHABLE_SNAIL_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_WHALE_ITEM = registerItem("plushable_whale", PLUSHABLE_WHALE_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_ORANGUTAN_ITEM = registerItem("plushable_orangutan", PLUSHABLE_ORANGUTAN_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_GOLDFISH_ITEM = registerItem("plushable_goldfish", PLUSHABLE_GOLDFISH_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_TRATER_ITEM = registerItem("plushable_trater", PLUSHABLE_TRATER_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_CONDUCTOR_ITEM = registerItem("plushable_conductor", PLUSHABLE_CONDUCTOR_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_ANIMATRONIC_ITEM = registerItem("plushable_animatronic", PLUSHABLE_ANIMATRONIC_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_MOOBLOOM_ITEM = registerItem("plushable_moobloom", PLUSHABLE_MOOBLOOM_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_FROGE_ITEM = registerItem("plushable_froge", PLUSHABLE_FROGE_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_OWL_ITEM = registerItem("plushable_owl", PLUSHABLE_OWL_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_HAMSTER_ITEM = registerItem("plushable_hamster", PLUSHABLE_HAMSTER_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_DORMOUSE_ITEM = registerItem("plushable_dormouse", PLUSHABLE_DORMOUSE_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_SEA_BUNNY_ITEM = registerItem("plushable_sea_bunny", PLUSHABLE_SEA_BUNNY_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_STATUETTE_ITEM = registerItem("plushable_statuette", PLUSHABLE_STATUETTE_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_MAMMOTH_ITEM = registerItem("plushable_mammoth", PLUSHABLE_MAMMOTH_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_TIGER_ITEM = registerItem("plushable_tiger", PLUSHABLE_TIGER_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_WALRUS_ITEM = registerItem("plushable_walrus", PLUSHABLE_WALRUS_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_BLAHAJ_ITEM = registerItem("plushable_blahaj", PLUSHABLE_BLAHAJ_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_WISP_ITEM = registerItem("plushable_wisp", PLUSHABLE_WISP_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_COOPER_ITEM = registerItem("plushable_cooper", PLUSHABLE_COOPER_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_ZIGGY_ITEM = registerItem("plushable_ziggy", PLUSHABLE_ZIGGY_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_POTSY_ITEM = registerItem("plushable_potsy", PLUSHABLE_POTSY_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_SNOWIE_ITEM = registerItem("plushable_snowie", PLUSHABLE_SNOWIE_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_RIBBIT_ITEM = registerItem("plushable_ribbit", PLUSHABLE_RIBBIT_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_CREAKY_ITEM = registerItem("plushable_creaky", PLUSHABLE_CREAKY_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_KWEEBEC_ITEM = registerItem("plushable_kweebec", PLUSHABLE_KWEEBEC_BLOCK);
  public static final Supplier<PlushableBlockItem> PLUSHABLE_STONELING_ITEM = registerItem("plushable_stoneling", PLUSHABLE_STONELING_BLOCK);

  public static void init() {
  }

  private static Supplier<BasePlushable> registerBlock(String path, Function<BlockBehaviour.Properties, BasePlushable> block) {
      return BLOCK_REGISTRAR.register(path, () -> block.apply(BasePlushable.defaultSettings.setId(ResourceKey.create(Registries.BLOCK, ID(path)))));
  }
  
  private static Supplier<PlushableBlockItem> registerItem(String path, Supplier<BasePlushable> block) {
    Supplier<PlushableBlockItem> plushSupplier = ITEM_REGISTRAR.register(path, () -> new PlushableBlockItem(block.get(), new Item.Properties()
        .setId(ResourceKey.create(Registries.ITEM, ID(path)))
        .useBlockDescriptionPrefix()
        .stacksTo(16)
        .component(DataComponents.EQUIPPABLE, Equippable.builder(EquipmentSlot.HEAD).build())
    ));
    PLUSHABLE_LIST.add(plushSupplier);
    return plushSupplier;
  }
}
