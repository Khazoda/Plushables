package com.seacroak.plushables.registry;

import com.seacroak.plushables.PlushablesMod;
import com.seacroak.plushables.block.*;
import com.seacroak.plushables.item.FroglinCap;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class MainRegistry {
  /* Registries */
  public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, PlushablesMod.MOD_ID);
  public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, PlushablesMod.MOD_ID);

  /* Complex Plushables */
  public static final RegistryObject<Block> BUILDER_BLOCK = registerBlock("builder_block",  () -> new BuilderBlock(), new Item.Properties().stacksTo(64));
  public static final RegistryObject<Block> CLUCKY_BLOCK = registerBlock("clucky_plushable",  () -> new CluckyBlock(), new Item.Properties().stacksTo(8));
  public static final RegistryObject<Block> RUPERT_BLOCK = registerBlock("rupert_plushable",  () -> new RupertBlock(), new Item.Properties().stacksTo(8));
  public static final RegistryObject<Block> DRAGON_BLOCK = registerBlock("dragon_plushable",  () -> new DragonBlock(), new Item.Properties().stacksTo(8));

  /* Simple Plushables */
  public static final RegistryObject<Block> PENGUIN_PLUSHABLE = registerBlock("penguin_plushable", () -> new PenguinBlock(), new Item.Properties().stacksTo(8));
  public static final RegistryObject<Block> FROGLIN_PLUSHABLE = registerBlock("froglin_plushable", ()-> new FroglinBlock(), new Item.Properties().stacksTo(8));
  public static final RegistryObject<Block> FOX_PLUSHABLE = registerBlock("fox_plushable",() -> new FoxBlock(), new Item.Properties().stacksTo(8));
  public static final RegistryObject<Block> PIG_PLUSHABLE = registerBlock("pig_plushable",() -> new PigBlock(), new Item.Properties().stacksTo(8));
  public static final RegistryObject<Block> TRUFFLES_PLUSHABLE = registerBlock("truffles_plushable", () -> new TrufflesBlock(), new Item.Properties().stacksTo(8));
  public static final RegistryObject<Block> DJUNGELSKOG_PLUSHABLE = registerBlock("djungelskog_plushable",() -> new DjungelskogBlock(), new Item.Properties().stacksTo(8));
  public static final RegistryObject<Block> RATTIAM_PLUSHABLE = registerBlock("rattiam_plushable",() -> new RattiamBlock(), new Item.Properties().stacksTo(8));
  public static final RegistryObject<Block> TRICERATOPS_PLUSHABLE = registerBlock("triceratops_plushable",() -> new TriceratopsBlock(), new Item.Properties().stacksTo(8));
  public static final RegistryObject<Block> UNICORN_PLUSHABLE = registerBlock("unicorn_plushable",() -> new UnicornBlock(), new Item.Properties().stacksTo(8));
  public static final RegistryObject<Block> WHELPLING_PLUSHABLE = registerBlock("whelpling_plushable",() -> new WhelplingBlock(), new Item.Properties().stacksTo(8));
  public static final RegistryObject<Block> RAPTOR_PLUSHABLE = registerBlock("raptor_plushable",() -> new RaptorBlock(), new Item.Properties().stacksTo(8));
  public static final RegistryObject<Block> WIZARD_PLUSHABLE = registerBlock("wizard_plushable",() -> new WizardBlock(), new Item.Properties().stacksTo(8));
  public static final RegistryObject<Block> BEAUX_PLUSHABLE = registerBlock("beaux_plushable",() -> new BeauxBlock(), new Item.Properties().stacksTo(8));
  public static final RegistryObject<Block> GOBLIN_PLUSHABLE = registerBlock("goblin_plushable",() -> new GoblinBlock(), new Item.Properties().stacksTo(8));

  /* Items */
  public static final RegistryObject<Item> HEART_OF_GOLD = ITEMS.register("heart_of_gold", () -> new Item(new Item.Properties()));
  public static final RegistryObject<Item> POWERED_HEART = ITEMS.register("powered_heart", () -> new Item(new Item.Properties()));

  // Caps
  public static final RegistryObject<FroglinCap> FROGLIN_CAP = ITEMS.register("cap_froglin",
      () -> new FroglinCap(ArmorMaterials.DIAMOND, ArmorItem.Type.HELMET,new Item.Properties().stacksTo(1)));

  //  Block Register Generic Helper Function
  private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, Item.Properties itemProperties) {
    RegistryObject<T> toReturn = BLOCKS.register(name, block);
    registerBlockItem(name, toReturn, itemProperties);
    return toReturn;
  }

  //  Block Item Register Generic Helper Function
  private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, Item.Properties itemProperties) {
    return ITEMS.register(name, () -> new BlockItem(block.get(),itemProperties));
  }

  //  Register All
  public static void register(IEventBus eventBus) {
    ITEMS.register(eventBus);
    BLOCKS.register(eventBus);
  }

}
