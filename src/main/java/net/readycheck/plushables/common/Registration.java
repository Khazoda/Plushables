package net.readycheck.plushables.common;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tags.ItemTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.readycheck.plushables.common.blocks.lukepeculier.Lukepeculier;
import net.readycheck.plushables.common.blocks.penguin.Penguin;
import net.readycheck.plushables.common.blocks.recycler.Recycler;
import net.readycheck.plushables.common.blocks.recycler.RecyclerTileEntity;
import net.readycheck.plushables.common.blocks.recycler.RecyclerTileRenderer;
import net.readycheck.plushables.common.entities.froglin.FroglinEntity;
import net.readycheck.plushables.common.items.spawn_eggs.ModSpawnEggItem;

import static net.readycheck.plushables.common.Plushables.MOD_ID;

public class Registration {
    private static final IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    private static final DeferredRegister<TileEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MOD_ID);
    private static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, MOD_ID);
    private static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, MOD_ID);

    public static void init() {
        BLOCKS.register(eventBus);
        ITEMS.register(eventBus);
        TILES.register(eventBus);
        CONTAINERS.register(eventBus);
        ENTITIES.register(eventBus);
    }

    public static void setupCreatures() {
        GlobalEntityTypeAttributes.put(Registration.FROGLIN.get(), FroglinEntity.returnCustomAttributes().create());

    }

    //  BLOCKS
    private static final AbstractBlock.Properties plushieProperties = AbstractBlock.Properties.create(Material.WOOL).hardnessAndResistance(0.5f).harvestLevel(1).notSolid();
    //  Recycler
    public static final RegistryObject<Recycler> RECYCLER = BLOCKS.register("recycler", Recycler::new);
    public static final RegistryObject<Item> RECYCLER_ITEM = ITEMS.register("recycler",
            () -> new BlockItem(RECYCLER.get(), new Item.Properties().group(Plushables.PlushablesGroup)));
    public static final RegistryObject<TileEntityType<RecyclerTileEntity>> RECYCLER_TILE = TILES.register("recycler", () -> TileEntityType.Builder.create(RecyclerTileEntity::new, RECYCLER.get()).build(null));

    //    PLUSHIES
    //    Penguin
    public static final RegistryObject<Penguin> PENGUIN = BLOCKS.register("penguin", () -> new Penguin(plushieProperties));
    public static final RegistryObject<Item> PENGUIN_ITEM = ITEMS.register("penguin",
            () -> new BlockItem(PENGUIN.get(), new Item.Properties().group(Plushables.PlushieGroup)));
    //    Lukepeculier
    public static final RegistryObject<Lukepeculier> LUKEPECULIER = BLOCKS.register("lukepeculier", () -> new Lukepeculier(plushieProperties));
    public static final RegistryObject<Item> LUKEPECULIER_ITEM = ITEMS.register("lukepeculier",
            () -> new BlockItem(LUKEPECULIER.get(), new Item.Properties().group(Plushables.PlushieGroup)));


    //    ENTITIES
    //    Froglin
    public static final RegistryObject<EntityType<FroglinEntity>> FROGLIN = ENTITIES.register("froglin", () -> EntityType.Builder.create(FroglinEntity::new, EntityClassification.CREATURE)
            .size(.5f, .5f)
            .setShouldReceiveVelocityUpdates(false)
            .build("froglin"));

    //    EGGS
    public static final RegistryObject<Item> FROGLIN_EGG = ITEMS.register("froglin_spawn_egg",
            () -> new ModSpawnEggItem(FROGLIN, 0x1B793E, 0x5A6109, new Item.Properties().group(Plushables.PlushablesGroup)));


    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onPostRegisterEntities(final RegistryEvent.Register<EntityType<?>> event) {
        ModSpawnEggItem.initUnaddedEggs();
    }

}
