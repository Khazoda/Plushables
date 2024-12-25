package com.khazoda.plushables.registry;

import com.khazoda.plushables.PlushablesCommon;
import com.khazoda.plushables.block.plushable.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class MainRegistry {
  private static final Reggie<Block> BLOCK_REGISTRAR = PlushablesCommon.REGISTRARS.get(Registries.BLOCK);
  public static final Supplier<Block> PLUSHABLE_ANIMATRONIC_BLOCK = BLOCK_REGISTRAR.register("plushable_animatronic",
      PlushableAnimatronicBlock::new);

  /* ==========[ Block Registration ]========== */
  public static final Supplier<Block> PLUSHABLE_BEAUX_BLOCK = BLOCK_REGISTRAR.register("plushable_beaux", PlushableBeauxBlock::new);
  public static final Supplier<Block> PLUSHABLE_BIG_IRRITATER_BLOCK = BLOCK_REGISTRAR.register("plushable_big_irritater", PlushableBigIrritaterBlock::new);
  public static final Supplier<Block> PLUSHABLE_BIG_TATER_BLOCK = BLOCK_REGISTRAR.register("plushable_big_tater", PlushableBigTaterBlock::new);
  public static final Supplier<Block> PLUSHABLE_BLAHAJ_BLOCK = BLOCK_REGISTRAR.register("plushable_blahaj", PlushableBlahajBlock::new);
  public static final Supplier<Block> PLUSHABLE_CLUCKY_BLOCK = BLOCK_REGISTRAR.register("plushable_clucky", PlushableCluckyBlock::new);
  public static final Supplier<Block> PLUSHABLE_CONDUCTOR_BLOCK = BLOCK_REGISTRAR.register("plushable_conductor", PlushableConductorBlock::new);
  public static final Supplier<Block> PLUSHABLE_COOPER_BLOCK = BLOCK_REGISTRAR.register("plushable_cooper", PlushableCooperBlock::new);
  public static final Supplier<Block> PLUSHABLE_DJUNGELSKOG_BLOCK = BLOCK_REGISTRAR.register("plushable_djungelskog", PlushableDjungelskogBlock::new);
  public static final Supplier<Block> PLUSHABLE_DORMOUSE_BLOCK = BLOCK_REGISTRAR.register("plushable_dormouse", PlushableDormouseBlock::new);
  public static final Supplier<Block> PLUSHABLE_DRAGON_BLOCK = BLOCK_REGISTRAR.register("plushable_dragon", PlushableDragonBlock::new);
  public static final Supplier<Block> PLUSHABLE_FOX_BLOCK = BLOCK_REGISTRAR.register("plushable_fox", PlushableFoxBlock::new);
  public static final Supplier<Block> PLUSHABLE_FROGE_BLOCK = BLOCK_REGISTRAR.register("plushable_froge", PlushableFrogeBlock::new);
  public static final Supplier<Block> PLUSHABLE_FROGLIN_BLOCK = BLOCK_REGISTRAR.register("plushable_froglin", PlushableFroglinBlock::new);
  public static final Supplier<Block> PLUSHABLE_GOBLIN_BLOCK = BLOCK_REGISTRAR.register("plushable_goblin", PlushableGoblinBlock::new);
  public static final Supplier<Block> PLUSHABLE_GOLDFISH_BLOCK = BLOCK_REGISTRAR.register("plushable_goldfish", PlushableGoldfishBlock::new);
  public static final Supplier<Block> PLUSHABLE_HAMSTER_BLOCK = BLOCK_REGISTRAR.register("plushable_hamster", PlushableHamsterBlock::new);
  public static final Supplier<Block> PLUSHABLE_MAMMOTH_BLOCK = BLOCK_REGISTRAR.register("plushable_mammoth", PlushableMammothBlock::new);
  public static final Supplier<Block> PLUSHABLE_MOOBLOOM_BLOCK = BLOCK_REGISTRAR.register("plushable_moobloom", PlushableMoobloomBlock::new);
  public static final Supplier<Block> PLUSHABLE_OCTOPLUSHABLE_BLOCK = BLOCK_REGISTRAR.register("plushable_octoplushable", PlushableOctoplushableBlock::new);
  public static final Supplier<Block> PLUSHABLE_ORANGUTAN_BLOCK = BLOCK_REGISTRAR.register("plushable_orangutan", PlushableOrangutanBlock::new);
  public static final Supplier<Block> PLUSHABLE_OTTER_BLOCK = BLOCK_REGISTRAR.register("plushable_otter", PlushableOtterBlock::new);
  public static final Supplier<Block> PLUSHABLE_OWL_BLOCK = BLOCK_REGISTRAR.register("plushable_owl", PlushableOwlBlock::new);
  public static final Supplier<Block> PLUSHABLE_PENGUIN_BLOCK = BLOCK_REGISTRAR.register("plushable_penguin", PlushablePenguinBlock::new);
  public static final Supplier<Block> PLUSHABLE_PIG_BLOCK = BLOCK_REGISTRAR.register("plushable_pig", PlushablePigBlock::new);
  public static final Supplier<Block> PLUSHABLE_POTSY_BLOCK = BLOCK_REGISTRAR.register("plushable_potsy", PlushablePotsyBlock::new);
  public static final Supplier<Block> PLUSHABLE_RAPTOR_BLOCK = BLOCK_REGISTRAR.register("plushable_raptor", PlushableRaptorBlock::new);
  public static final Supplier<Block> PLUSHABLE_RATTIAM_BLOCK = BLOCK_REGISTRAR.register("plushable_rattiam", PlushableRattiamBlock::new);
  public static final Supplier<Block> PLUSHABLE_RUPERT_BLOCK = BLOCK_REGISTRAR.register("plushable_rupert", PlushableRupertBlock::new);
  public static final Supplier<Block> PLUSHABLE_SEA_BUNNY_BLOCK = BLOCK_REGISTRAR.register("plushable_sea_bunny", PlushableSeaBunnyBlock::new);
  public static final Supplier<Block> PLUSHABLE_SHRUMP_BLOCK = BLOCK_REGISTRAR.register("plushable_shrump", PlushableShrumpBlock::new);
  public static final Supplier<Block> PLUSHABLE_SNAIL_BLOCK = BLOCK_REGISTRAR.register("plushable_snail", PlushableSnailBlock::new);
  public static final Supplier<Block> PLUSHABLE_STATUETTE_BLOCK = BLOCK_REGISTRAR.register("plushable_statuette", PlushableStatuetteBlock::new);
  public static final Supplier<Block> PLUSHABLE_TIGER_BLOCK = BLOCK_REGISTRAR.register("plushable_tiger", PlushableTigerBlock::new);
  public static final Supplier<Block> PLUSHABLE_TRATER_BLOCK = BLOCK_REGISTRAR.register("plushable_trater", PlushableTraterBlock::new);
  public static final Supplier<Block> PLUSHABLE_TRICERATOPS_BLOCK = BLOCK_REGISTRAR.register("plushable_triceratops", PlushableTriceratopsBlock::new);
  public static final Supplier<Block> PLUSHABLE_TRUFFLES_BLOCK = BLOCK_REGISTRAR.register("plushable_truffles", PlushableTrufflesBlock::new);
  public static final Supplier<Block> PLUSHABLE_UNICORN_BLOCK = BLOCK_REGISTRAR.register("plushable_unicorn", PlushableUnicornBlock::new);
  public static final Supplier<Block> PLUSHABLE_WALRUS_BLOCK = BLOCK_REGISTRAR.register("plushable_walrus", PlushableWalrusBlock::new);
  public static final Supplier<Block> PLUSHABLE_WHALE_BLOCK = BLOCK_REGISTRAR.register("plushable_whale", PlushableWhaleBlock::new);
  public static final Supplier<Block> PLUSHABLE_WHELPLING_BLOCK = BLOCK_REGISTRAR.register("plushable_whelpling", PlushableWhelplingBlock::new);
  public static final Supplier<Block> PLUSHABLE_WISP_BLOCK = BLOCK_REGISTRAR.register("plushable_wisp", PlushableWispBlock::new);
  public static final Supplier<Block> PLUSHABLE_WIZARD_BLOCK = BLOCK_REGISTRAR.register("plushable_wizard", PlushableWizardBlock::new);
  public static final Supplier<Block> PLUSHABLE_ZIGGY_BLOCK = BLOCK_REGISTRAR.register("plushable_ziggy", PlushableZiggyBlock::new);
  private static final Reggie<Item> ITEM_REGISTRAR = PlushablesCommon.REGISTRARS.get(Registries.ITEM);

  /* ==========[ Item Registration ]========== */
  /* ==========[ BlockItem Registration ]========== */
  public static final Supplier<BlockItem> PLUSHABLE_ANIMATRONIC_ITEM = registerBlockItem("plushable_animatronic", PLUSHABLE_ANIMATRONIC_BLOCK);
  public static final Supplier<BlockItem> PLUSHABLE_BEAUX_ITEM = registerBlockItem("plushable_beaux", PLUSHABLE_BEAUX_BLOCK);
  public static final Supplier<BlockItem> PLUSHABLE_BIG_IRRITATER_ITEM = registerBlockItem("plushable_big_irritater", PLUSHABLE_BIG_IRRITATER_BLOCK);
  public static final Supplier<BlockItem> PLUSHABLE_BIG_TATER_ITEM = registerBlockItem("plushable_big_tater", PLUSHABLE_BIG_TATER_BLOCK);
  public static final Supplier<BlockItem> PLUSHABLE_BLAHAJ_ITEM = registerBlockItem("plushable_blahaj", PLUSHABLE_BLAHAJ_BLOCK);
  public static final Supplier<BlockItem> PLUSHABLE_CLUCKY_ITEM = registerBlockItem("plushable_clucky", PLUSHABLE_CLUCKY_BLOCK);
  public static final Supplier<BlockItem> PLUSHABLE_CONDUCTOR_ITEM = registerBlockItem("plushable_conductor", PLUSHABLE_CONDUCTOR_BLOCK);
  public static final Supplier<BlockItem> PLUSHABLE_COOPER_ITEM = registerBlockItem("plushable_cooper", PLUSHABLE_COOPER_BLOCK);
  public static final Supplier<BlockItem> PLUSHABLE_DJUNGELSKOG_ITEM = registerBlockItem("plushable_djungelskog", PLUSHABLE_DJUNGELSKOG_BLOCK);
  public static final Supplier<BlockItem> PLUSHABLE_DORMOUSE_ITEM = registerBlockItem("plushable_dormouse", PLUSHABLE_DORMOUSE_BLOCK);
  public static final Supplier<BlockItem> PLUSHABLE_DRAGON_ITEM = registerBlockItem("plushable_dragon", PLUSHABLE_DRAGON_BLOCK);
  public static final Supplier<BlockItem> PLUSHABLE_FOX_ITEM = registerBlockItem("plushable_fox", PLUSHABLE_FOX_BLOCK);
  public static final Supplier<BlockItem> PLUSHABLE_FROGE_ITEM = registerBlockItem("plushable_froge", PLUSHABLE_FROGE_BLOCK);
  public static final Supplier<BlockItem> PLUSHABLE_FROGLIN_ITEM = registerBlockItem("plushable_froglin", PLUSHABLE_FROGLIN_BLOCK);
  public static final Supplier<BlockItem> PLUSHABLE_GOBLIN_ITEM = registerBlockItem("plushable_goblin", PLUSHABLE_GOBLIN_BLOCK);
  public static final Supplier<BlockItem> PLUSHABLE_GOLDFISH_ITEM = registerBlockItem("plushable_goldfish", PLUSHABLE_GOLDFISH_BLOCK);
  public static final Supplier<BlockItem> PLUSHABLE_HAMSTER_ITEM = registerBlockItem("plushable_hamster", PLUSHABLE_HAMSTER_BLOCK);
  public static final Supplier<BlockItem> PLUSHABLE_MAMMOTH_ITEM = registerBlockItem("plushable_mammoth", PLUSHABLE_MAMMOTH_BLOCK);
  public static final Supplier<BlockItem> PLUSHABLE_MOOBLOOM_ITEM = registerBlockItem("plushable_moobloom", PLUSHABLE_MOOBLOOM_BLOCK);
  public static final Supplier<BlockItem> PLUSHABLE_OCTOPLUSHABLE_ITEM = registerBlockItem("plushable_octoplushable", PLUSHABLE_OCTOPLUSHABLE_BLOCK);
  public static final Supplier<BlockItem> PLUSHABLE_ORANGUTAN_ITEM = registerBlockItem("plushable_orangutan", PLUSHABLE_ORANGUTAN_BLOCK);
  public static final Supplier<BlockItem> PLUSHABLE_OTTER_ITEM = registerBlockItem("plushable_otter", PLUSHABLE_OTTER_BLOCK);
  public static final Supplier<BlockItem> PLUSHABLE_OWL_ITEM = registerBlockItem("plushable_owl", PLUSHABLE_OWL_BLOCK);
  public static final Supplier<BlockItem> PLUSHABLE_PENGUIN_ITEM = registerBlockItem("plushable_penguin", PLUSHABLE_PENGUIN_BLOCK);
  public static final Supplier<BlockItem> PLUSHABLE_PIG_ITEM = registerBlockItem("plushable_pig", PLUSHABLE_PIG_BLOCK);
  public static final Supplier<BlockItem> PLUSHABLE_POTSY_ITEM = registerBlockItem("plushable_potsy", PLUSHABLE_POTSY_BLOCK);
  public static final Supplier<BlockItem> PLUSHABLE_RAPTOR_ITEM = registerBlockItem("plushable_raptor", PLUSHABLE_RAPTOR_BLOCK);
  public static final Supplier<BlockItem> PLUSHABLE_RATTIAM_ITEM = registerBlockItem("plushable_rattiam", PLUSHABLE_RATTIAM_BLOCK);
  public static final Supplier<BlockItem> PLUSHABLE_RUPERT_ITEM = registerBlockItem("plushable_rupert", PLUSHABLE_RUPERT_BLOCK);
  public static final Supplier<BlockItem> PLUSHABLE_SEA_BUNNY_ITEM = registerBlockItem("plushable_sea_bunny", PLUSHABLE_SEA_BUNNY_BLOCK);
  public static final Supplier<BlockItem> PLUSHABLE_SHRUMP_ITEM = registerBlockItem("plushable_shrump", PLUSHABLE_SHRUMP_BLOCK);
  public static final Supplier<BlockItem> PLUSHABLE_SNAIL_ITEM = registerBlockItem("plushable_snail", PLUSHABLE_SNAIL_BLOCK);
  public static final Supplier<BlockItem> PLUSHABLE_STATUETTE_ITEM = registerBlockItem("plushable_statuette", PLUSHABLE_STATUETTE_BLOCK);
  public static final Supplier<BlockItem> PLUSHABLE_TIGER_ITEM = registerBlockItem("plushable_tiger", PLUSHABLE_TIGER_BLOCK);
  public static final Supplier<BlockItem> PLUSHABLE_TRATER_ITEM = registerBlockItem("plushable_trater", PLUSHABLE_TRATER_BLOCK);
  public static final Supplier<BlockItem> PLUSHABLE_TRICERATOPS_ITEM = registerBlockItem("plushable_triceratops", PLUSHABLE_TRICERATOPS_BLOCK);
  public static final Supplier<BlockItem> PLUSHABLE_TRUFFLES_ITEM = registerBlockItem("plushable_truffles", PLUSHABLE_TRUFFLES_BLOCK);
  public static final Supplier<BlockItem> PLUSHABLE_UNICORN_ITEM = registerBlockItem("plushable_unicorn", PLUSHABLE_UNICORN_BLOCK);
  public static final Supplier<BlockItem> PLUSHABLE_WALRUS_ITEM = registerBlockItem("plushable_walrus", PLUSHABLE_WALRUS_BLOCK);
  public static final Supplier<BlockItem> PLUSHABLE_WHALE_ITEM = registerBlockItem("plushable_whale", PLUSHABLE_WHALE_BLOCK);
  public static final Supplier<BlockItem> PLUSHABLE_WHELPLING_ITEM = registerBlockItem("plushable_whelpling", PLUSHABLE_WHELPLING_BLOCK);
  public static final Supplier<BlockItem> PLUSHABLE_WISP_ITEM = registerBlockItem("plushable_wisp", PLUSHABLE_WISP_BLOCK);
  public static final Supplier<BlockItem> PLUSHABLE_WIZARD_ITEM = registerBlockItem("plushable_wizard", PLUSHABLE_WIZARD_BLOCK);
  public static final Supplier<BlockItem> PLUSHABLE_ZIGGY_ITEM = registerBlockItem("plushable_ziggy", PLUSHABLE_ZIGGY_BLOCK);

  public static void init() {
  }

  private static Supplier<BlockItem> registerBlockItem(String name, Supplier<Block> block) {
    return ITEM_REGISTRAR.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
  }
}
