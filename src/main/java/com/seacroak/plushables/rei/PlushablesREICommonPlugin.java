package com.seacroak.plushables.rei;

import com.seacroak.plushables.block.screen.BuilderScreenHandler;
import com.seacroak.plushables.util.GenericUtils;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.DisplaySerializerRegistry;
import me.shedaniel.rei.api.common.plugins.REIServerPlugin;
import me.shedaniel.rei.api.common.transfer.info.MenuInfoRegistry;
import me.shedaniel.rei.api.common.transfer.info.simple.SimpleMenuInfoProvider;
import me.shedaniel.rei.forge.REIPluginCommon;

@REIPluginCommon
public class PlushablesREICommonPlugin implements REIServerPlugin {

    public static final CategoryIdentifier<BuilderDisplay> BUILDER = CategoryIdentifier.of(GenericUtils.ID("builder"));

    @Override
    public void registerMenuInfo(MenuInfoRegistry registry) {
        registry.register(BUILDER, BuilderScreenHandler.class, SimpleMenuInfoProvider.of(BuilderMenuInfo::new));
    }

    @Override
    public void registerDisplaySerializer(DisplaySerializerRegistry registry) {
        registry.register(BUILDER, BuilderDisplay.Serializer.INSTANCE);
    }
}
