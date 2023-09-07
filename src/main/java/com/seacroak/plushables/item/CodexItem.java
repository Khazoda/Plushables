package com.seacroak.plushables.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import vazkii.patchouli.api.PatchouliAPI;

public class CodexItem extends BlockItem {

  public CodexItem(Block block, FabricItemSettings settings) {
    super(block, settings);
  }

  @Override
  public ActionResult useOnBlock(ItemUsageContext context) {
    PlayerEntity player = context.getPlayer();
    if (!player.isSneaking()) {
      if (player instanceof ServerPlayerEntity sp) {
        PatchouliAPI.get().openBookGUI(sp, new Identifier("plushables:codex"));
        return ActionResult.SUCCESS;
      }
    } else {
      return super.useOnBlock(context);
    }
    return ActionResult.PASS;
  }

  @Override
  public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
    ItemStack stack = player.getStackInHand(hand);

    if (player instanceof ServerPlayerEntity sp) {
      PatchouliAPI.get().openBookGUI(sp, new Identifier("plushables:codex"));
    }

    if (world.isClient()) {
      return TypedActionResult.success(stack);
    } else {
      return TypedActionResult.consume(stack);
    }
  }
}
