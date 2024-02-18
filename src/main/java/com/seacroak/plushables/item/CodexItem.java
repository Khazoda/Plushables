package com.seacroak.plushables.item;

import com.seacroak.plushables.registry.MainRegistry;
import io.wispforest.lavender.book.LavenderBookItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.world.event.GameEvent;

public class CodexItem extends LavenderBookItem {

  public CodexItem(FabricItemSettings settings) {
    super(settings, new Identifier("plushables:codex"));
  }

  @Override
  public ActionResult useOnBlock(ItemUsageContext context) {
    if (!context.getPlayer().isSneaking()) return ActionResult.PASS;

    var world = context.getWorld();
    var placePos = context.getBlockPos().offset(context.getSide());

    var newState = MainRegistry.CODEX_BLOCK.getDefaultState();
    if (!newState.canPlaceAt(world, placePos)) return ActionResult.PASS;

    world.setBlockState(placePos, newState, Block.REDRAW_ON_MAIN_THREAD | Block.NO_REDRAW | Block.NOTIFY_NEIGHBORS);
    world.emitGameEvent(context.getPlayer(), GameEvent.BLOCK_PLACE, context.getBlockPos());

    world.playSound(
            context.getPlayer(),
            placePos,
            newState.getSoundGroup().getPlaceSound(),
            SoundCategory.BLOCKS,
            (newState.getSoundGroup().getVolume() + 1f) / 2f,
            newState.getSoundGroup().getPitch() * .8f
    );

    if (context.getPlayer() instanceof ServerPlayerEntity player) {
      Criteria.PLACED_BLOCK.trigger(player, placePos, context.getStack());
    }

    if (context.getPlayer() == null || !context.getPlayer().getAbilities().creativeMode) {
      context.getStack().decrement(1);
    }

    return ActionResult.success(world.isClient);
  }
}
