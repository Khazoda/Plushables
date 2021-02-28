package net.readycheck.plushables.common.entities.froglin;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.readycheck.plushables.common.Plushables;
import net.readycheck.plushables.common.blocks.penguin.Penguin;

public class StartupCommon {

//    public static FroglinEntity froglin;  // this holds the unique instance of your block
//
//    @SubscribeEvent
//    public static void onRegisterEntitiesEvent(final RegistryEvent.Register<EntityEntry> entityRegisterEvent) {
//        entityRegisterEvent.getRegistry().register(FROGLIN.get());
//
//    }
//    public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, Plushables.MOD_ID);
//
//    //    Entity Types
//    public static final RegistryObject<EntityType<FroglinEntity>> FROGLIN = ENTITY_TYPES.register("froglin",
//            () -> EntityType.Builder.create(FroglinEntity::new, EntityClassification.CREATURE)
//                    .size(1.0f, 1.0f)
//                    .build(new ResourceLocation(Plushables.MOD_ID, "froglin").toString()));


//    @SubscribeEvent
//    public static void onEntitiesRegistration(final RegistryEvent.Register<EntityEntry> entityRegisterEvent) {
//        froglin = (Froglin) (new Froglin().setRegistryName("plushables", "froglin"));
//        entityRegisterEvent.getRegistry().register(froglin);
//    }

}
