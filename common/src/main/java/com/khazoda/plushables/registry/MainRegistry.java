package com.khazoda.plushables.registry;

import com.khazoda.plushables.PlushablesCommon;
import com.khazoda.plushables.block.BasePlushable;
import com.khazoda.plushables.block.BasePlushableBlockEntity;
import com.khazoda.plushables.block.plushable.*;
import com.khazoda.plushables.item.PlushableBlockItem;
import com.khazoda.plushables.platform.Services;
import com.khazoda.plushables.registry.helper.Reggie;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class MainRegistry {
  private static final Reggie<Block> BLOCK_REGISTRAR = PlushablesCommon.REGISTRARS.get(Registries.BLOCK);
  private static final Reggie<Item> ITEM_REGISTRAR = PlushablesCommon.REGISTRARS.get(Registries.ITEM);
  private static final Reggie<BlockEntityType<?>> BLOCK_ENTITY_REGISTRAR = PlushablesCommon.REGISTRARS.get(Registries.BLOCK_ENTITY_TYPE);

  /* ==========[ List of All Plushables (used in loot table iteration) ]========== */
  public static final List<PlushableEntry> PLUSHABLE_LIST = new ArrayList<>();

  /* ==========[ Item Registration ]========== */
  public static final Supplier<Item> HEART_OF_GOLD_ITEM = ITEM_REGISTRAR.register("heart_of_gold", () -> new Item(new Item.Properties()));

  /* ==========[ Blocks + BlockItems ]========== */
  public static final PlushableEntry PLUSHABLE_PENGUIN_BLOCK = register("plushable_penguin", PlushablePenguinBlock::new);
  public static final PlushableEntry PLUSHABLE_FROGLIN_BLOCK = register("plushable_froglin", PlushableFroglinBlock::new);
  public static final PlushableEntry PLUSHABLE_FOX_BLOCK = register("plushable_fox", PlushableFoxBlock::new);
  public static final PlushableEntry PLUSHABLE_CLUCKY_BLOCK = register("plushable_clucky", PlushableCluckyBlock::new);
  public static final PlushableEntry PLUSHABLE_PIG_BLOCK = register("plushable_pig", PlushablePigBlock::new);
  public static final PlushableEntry PLUSHABLE_TRUFFLES_BLOCK = register("plushable_truffles", PlushableTrufflesBlock::new);
  public static final PlushableEntry PLUSHABLE_DJUNGELSKOG_BLOCK = register("plushable_djungelskog", PlushableDjungelskogBlock::new);
  public static final PlushableEntry PLUSHABLE_RATTIAM_BLOCK = register("plushable_rattiam", PlushableRattiamBlock::new);
  public static final PlushableEntry PLUSHABLE_TRICERATOPS_BLOCK = register("plushable_triceratops", PlushableTriceratopsBlock::new);
  public static final PlushableEntry PLUSHABLE_UNICORN_BLOCK = register("plushable_unicorn", PlushableUnicornBlock::new);
  public static final PlushableEntry PLUSHABLE_WHELPLING_BLOCK = register("plushable_whelpling", PlushableWhelplingBlock::new);
  public static final PlushableEntry PLUSHABLE_RAPTOR_BLOCK = register("plushable_raptor", PlushableRaptorBlock::new);
  public static final PlushableEntry PLUSHABLE_RUPERT_BLOCK = register("plushable_rupert", PlushableRupertBlock::new);
  public static final PlushableEntry PLUSHABLE_DRAGON_BLOCK = register("plushable_dragon", PlushableDragonBlock::new);
  public static final PlushableEntry PLUSHABLE_WIZARD_BLOCK = register("plushable_wizard", PlushableWizardBlock::new);
  public static final PlushableEntry PLUSHABLE_BEAUX_BLOCK = register("plushable_beaux", PlushableBeauxBlock::new);
  public static final PlushableEntry PLUSHABLE_GOBLIN_BLOCK = register("plushable_goblin", PlushableGoblinBlock::new);
  public static final PlushableEntry PLUSHABLE_BIG_TATER_BLOCK = register("plushable_big_tater", PlushableBigTaterBlock::new);
  public static final PlushableEntry PLUSHABLE_BIG_IRRITATER_BLOCK = register("plushable_big_irritater", PlushableBigIrritaterBlock::new);
  public static final PlushableEntry PLUSHABLE_OTTER_BLOCK = register("plushable_otter", PlushableOtterBlock::new);
  public static final PlushableEntry PLUSHABLE_SHRUMP_BLOCK = register("plushable_shrump", PlushableShrumpBlock::new);
  public static final PlushableEntry PLUSHABLE_OCTOPLUSHABLE_BLOCK = register("plushable_octoplushable", PlushableOctoplushableBlock::new);
  public static final PlushableEntry PLUSHABLE_SNAIL_BLOCK = register("plushable_snail", PlushableSnailBlock::new);
  public static final PlushableEntry PLUSHABLE_WHALE_BLOCK = register("plushable_whale", PlushableWhaleBlock::new);
  public static final PlushableEntry PLUSHABLE_ORANGUTAN_BLOCK = register("plushable_orangutan", PlushableOrangutanBlock::new);
  public static final PlushableEntry PLUSHABLE_GOLDFISH_BLOCK = register("plushable_goldfish", PlushableGoldfishBlock::new);
  public static final PlushableEntry PLUSHABLE_TRATER_BLOCK = register("plushable_trater", PlushableTraterBlock::new);
  public static final PlushableEntry PLUSHABLE_CONDUCTOR_BLOCK = register("plushable_conductor", PlushableConductorBlock::new);
  public static final PlushableEntry PLUSHABLE_ANIMATRONIC_BLOCK = register("plushable_animatronic", PlushableAnimatronicBlock::new);
  public static final PlushableEntry PLUSHABLE_MOOBLOOM_BLOCK = register("plushable_moobloom", PlushableMoobloomBlock::new);
  public static final PlushableEntry PLUSHABLE_FROGE_BLOCK = register("plushable_froge", PlushableFrogeBlock::new);
  public static final PlushableEntry PLUSHABLE_OWL_BLOCK = register("plushable_owl", PlushableOwlBlock::new);
  public static final PlushableEntry PLUSHABLE_HAMSTER_BLOCK = register("plushable_hamster", PlushableHamsterBlock::new);
  public static final PlushableEntry PLUSHABLE_DORMOUSE_BLOCK = register("plushable_dormouse", PlushableDormouseBlock::new);
  public static final PlushableEntry PLUSHABLE_SEA_BUNNY_BLOCK = register("plushable_sea_bunny", PlushableSeaBunnyBlock::new);
  public static final PlushableEntry PLUSHABLE_STATUETTE_BLOCK = register("plushable_statuette", PlushableStatuetteBlock::new);
  public static final PlushableEntry PLUSHABLE_MAMMOTH_BLOCK = register("plushable_mammoth", PlushableMammothBlock::new);
  public static final PlushableEntry PLUSHABLE_TIGER_BLOCK = register("plushable_tiger", PlushableTigerBlock::new);
  public static final PlushableEntry PLUSHABLE_WALRUS_BLOCK = register("plushable_walrus", PlushableWalrusBlock::new);
  public static final PlushableEntry PLUSHABLE_BLAHAJ_BLOCK = register("plushable_blahaj", PlushableBlahajBlock::new);
  public static final PlushableEntry PLUSHABLE_WISP_BLOCK = register("plushable_wisp", PlushableWispBlock::new);
  public static final PlushableEntry PLUSHABLE_COOPER_BLOCK = register("plushable_cooper", PlushableCooperBlock::new);
  public static final PlushableEntry PLUSHABLE_ZIGGY_BLOCK = register("plushable_ziggy", PlushableZiggyBlock::new);
  public static final PlushableEntry PLUSHABLE_POTSY_BLOCK = register("plushable_potsy", PlushablePotsyBlock::new);
  public static final PlushableEntry PLUSHABLE_SNOWIE_BLOCK = register("plushable_snowie", PlushableSnowieBlock::new);
  public static final PlushableEntry PLUSHABLE_RIBBIT_BLOCK = register("plushable_ribbit", PlushableRibbitBlock::new);
  public static final PlushableEntry PLUSHABLE_CREAKY_BLOCK = register("plushable_creaky", PlushableCreakyBlock::new);
  public static final PlushableEntry PLUSHABLE_KWEEBEC_BLOCK = register("plushable_kweebec", PlushableKweebecBlock::new);
  public static final PlushableEntry PLUSHABLE_STONELING_BLOCK = register("plushable_stoneling", PlushableStonelingBlock::new);

  /* ==========[ BlockEntityType Registration ]========== */
  public static final Supplier<BlockEntityType<BasePlushableBlockEntity>> PLUSHABLE_BLOCK_ENTITY = BLOCK_ENTITY_REGISTRAR.register("plushable", () -> Services.PLATFORM.createPlushableBlockEntityType(allPlushableBlocks()));

  public static void init() {
  }

  private static PlushableEntry register(String name, Supplier<BasePlushable> block) {
    Supplier<BasePlushable> blockSupplier = BLOCK_REGISTRAR.register(name, block);
    Supplier<PlushableBlockItem> itemSupplier = ITEM_REGISTRAR.register(name,
        () -> new PlushableBlockItem(blockSupplier.get(), new Item.Properties().stacksTo(16)));
    PlushableEntry entry = new PlushableEntry(blockSupplier, itemSupplier);
    PLUSHABLE_LIST.add(entry);
    return entry;
  }

  private static Block[] allPlushableBlocks() {
    return PLUSHABLE_LIST.stream().map(PlushableEntry::getBlock).toArray(Block[]::new);
  }

  public record PlushableEntry(Supplier<BasePlushable> block, Supplier<PlushableBlockItem> item) {
    public BasePlushable getBlock() {
      return this.block.get();
    }
    public PlushableBlockItem getItem() {
      return this.item.get();
    }
  }
}
