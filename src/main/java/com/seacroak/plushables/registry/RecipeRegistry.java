package com.seacroak.plushables.registry;

import com.seacroak.plushables.PlushablesMod;
import com.seacroak.plushables.recipe.BuilderRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class RecipeRegistry {

    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
        DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, PlushablesMod.MOD_ID);

    public static final RegistryObject<RecipeSerializer<BuilderRecipe>> BUILDER_SERIALIZER =
        SERIALIZERS.register("builder", () -> BuilderRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}