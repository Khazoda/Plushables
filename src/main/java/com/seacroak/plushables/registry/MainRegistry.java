package com.seacroak.plushables.registry;

import com.seacroak.plushables.block.*;
import com.seacroak.plushables.item.CapArmorItem;
import com.seacroak.plushables.item.FoxCap;
import com.seacroak.plushables.item.FroglinCap;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.Item;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public final class MainRegistry {
  // Item Settings
  static final FabricItemSettings defaultItemSettings = new FabricItemSettings().maxCount(64);
  static final FabricItemSettings plushableItemSettings = new FabricItemSettings().maxCount(8).equipmentSlot(stack -> EquipmentSlot.HEAD);

  // Complex Plushables
  public static final BuilderBlock BUILDER_BLOCK = register("builder_block", new BuilderBlock(),
      defaultItemSettings);
  public static final CluckyBlock CLUCKY_BLOCK = register("clucky_plushable", new CluckyBlock(),
      plushableItemSettings);
  public static final RupertBlock RUPERT_BLOCK = register("rupert_plushable", new RupertBlock(),
      plushableItemSettings);
  public static final DragonBlock DRAGON_BLOCK = register("dragon_plushable", new DragonBlock(),
      plushableItemSettings);


  // Simple Plushables
  public static final PenguinBlock PENGUIN_PLUSHABLE = register("penguin_plushable", new PenguinBlock(),
      plushableItemSettings);
  public static final FroglinBlock FROGLIN_PLUSHABLE = register("froglin_plushable", new FroglinBlock(),
      plushableItemSettings);
  public static final FoxBlock FOX_PLUSHABLE = register("fox_plushable", new FoxBlock(),
      plushableItemSettings);
  public static final PigBlock PIG_PLUSHABLE = register("pig_plushable", new PigBlock(),
      plushableItemSettings);
  public static final TrufflesBlock TRUFFLES_PLUSHABLE = register("truffles_plushable", new TrufflesBlock(),
      plushableItemSettings);
  public static final DjungelskogBlock DJUNGELSKOG_PLUSHABLE = register("djungelskog_plushable", new DjungelskogBlock(),
      plushableItemSettings);
  public static final RattiamBlock RATTIAM_PLUSHABLE = register("rattiam_plushable", new RattiamBlock(),
      plushableItemSettings);
  public static final TriceratopsBlock TRICERATOPS_PLUSHABLE = register("triceratops_plushable", new TriceratopsBlock(),
      plushableItemSettings);
  public static final UnicornBlock UNICORN_PLUSHABLE = register("unicorn_plushable", new UnicornBlock(),
      plushableItemSettings);
  public static final WhelplingBlock WHELPLING_PLUSHABLE = register("whelpling_plushable", new WhelplingBlock(),
      plushableItemSettings);
  public static final RaptorBlock RAPTOR_PLUSHABLE = register("raptor_plushable", new RaptorBlock(),
      plushableItemSettings);
  public static final WizardBlock WIZARD_PLUSHABLE = register("wizard_plushable", new WizardBlock(),
      plushableItemSettings);
  public static final BeauxBlock BEAUX_PLUSHABLE = register("beaux_plushable", new BeauxBlock(),
      plushableItemSettings);
  public static final GoblinBlock GOBLIN_PLUSHABLE = register("goblin_plushable", new GoblinBlock(),
      plushableItemSettings);

  /* ITEMS */
  public static final Item HEART_OF_GOLD = register("heart_of_gold");
  public static final Item POWERED_HEART = register("powered_heart");

  // Caps
  public static final Item FROGLIN_CAP = registerCap("cap_froglin",new FroglinCap());
  public static final Item FOX_CAP = registerCap("cap_fox",new FoxCap());



  public static void init() {
  }

  private static <B extends Block> B register(String name, B block, FabricItemSettings itemSettings) {
    return RegistryHelper.registerBlock(name, block, itemSettings);
  }

  private static <B extends Block> B register(String name, B block) {
    return RegistryHelper.registerBlock(name, block, defaultItemSettings);
  }

  private static Item register(String name) {
    return RegistryHelper.registerItem(name, new Item(defaultItemSettings));
  }

  private static Item registerCap(String name, CapArmorItem capType) { return RegistryHelper.registerItem(name, capType);}

  private static Item register(String name, FabricItemSettings itemSettings) {
    return RegistryHelper.registerItem(name, new Item(itemSettings));
  }
}
