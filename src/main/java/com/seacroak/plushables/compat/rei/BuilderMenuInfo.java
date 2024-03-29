package com.seacroak.plushables.compat.rei;

import com.seacroak.plushables.block.screen.BuilderScreenHandler;
import me.shedaniel.rei.api.common.transfer.info.MenuInfoContext;
import me.shedaniel.rei.api.common.transfer.info.simple.SimplePlayerInventoryMenuInfo;
import me.shedaniel.rei.api.common.transfer.info.stack.SlotAccessor;

import java.util.List;

public record BuilderMenuInfo(BuilderDisplay display)
        implements SimplePlayerInventoryMenuInfo<BuilderScreenHandler, BuilderDisplay> {

    @Override
    public Iterable<SlotAccessor> getInputSlots(MenuInfoContext<BuilderScreenHandler, ?, BuilderDisplay> context) {
        return List.of(
                SlotAccessor.fromSlot(context.getMenu().getSlot(0)),
                SlotAccessor.fromSlot(context.getMenu().getSlot(1)),
                SlotAccessor.fromSlot(context.getMenu().getSlot(2)));
    }

    @Override
    public BuilderDisplay getDisplay() {
        return this.display;
    }
}
