package com.seacroak.plushables.rei;

import com.seacroak.plushables.recipe.BuilderRecipe;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.display.DisplaySerializer;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;

import java.util.List;

public record BuilderDisplay(List<EntryIngredient> inputs, List<EntryIngredient> output) implements Display {

    public BuilderDisplay(BuilderRecipe recipe) {
        this(
                recipe.getRecipeItems().stream().map(EntryIngredients::ofIngredient).toList(),
                List.of(EntryIngredients.of(recipe.getResultItem(null)))
        );
    }

    @Override
    public List<EntryIngredient> getInputEntries() {
        return this.inputs;
    }

    @Override
    public List<EntryIngredient> getOutputEntries() {
        return this.output;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return PlushablesREICommonPlugin.BUILDER;
    }

    public enum Serializer implements DisplaySerializer<BuilderDisplay> {
        INSTANCE;

        @Override
        public CompoundTag save(CompoundTag tag, BuilderDisplay display) {
            var inputList = new ListTag();
            display.inputs.forEach(entryStacks -> inputList.add(entryStacks.saveIngredient()));
            tag.put("Inputs", inputList);
            tag.put("Output", display.output.get(0).saveIngredient());
            return tag;
        }

        @Override
        public BuilderDisplay read(CompoundTag tag) {
            var inputs = tag.getList("Inputs", Tag.TAG_LIST);
            var output = tag.getList("Output", Tag.TAG_COMPOUND);

            return new BuilderDisplay(
                inputs.stream().map(nbt -> EntryIngredient.read((ListTag) nbt)).toList(),
                List.of(EntryIngredient.read(output))
            );
        }
    }
}
