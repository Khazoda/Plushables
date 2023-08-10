package com.seacroak.plushables.block.tile;

import com.seacroak.plushables.registry.TileRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class BasketBlockEntity extends BlockEntity {
  public static final int stack_size = 4;
  //  private DefaultedList<ItemStack> plushStack = DefaultedList.ofSize(4, ItemStack.EMPTY);
  private ItemStack[] plushStack = {ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY,};
  private int top_pointer = 0;

  public BasketBlockEntity(BlockPos pos, BlockState state) {
    super(TileRegistry.BASKET_TILE, pos, state);
  }

  public static void tick(World world, BlockPos pos, BlockState state, BasketBlockEntity be) {
  }

  public boolean pushPlush(ItemStack in) {
    if (top_pointer > stack_size - 1) return false;
    if (top_pointer == -1) top_pointer += 1;
    plushStack[top_pointer] = in;
    top_pointer += 1;
    sync();
    return true;
  }

  public boolean popPlush() {
    if (top_pointer < 0) return false;
    if (top_pointer == 4) top_pointer -= 1;
    plushStack[top_pointer] = ItemStack.EMPTY;
    top_pointer -= 1;
    sync();
    return true;
  }

  public ItemStack[] getPlushStack() {
    return this.plushStack;
  }

  /* Data Serialization */
  @Override
  protected void writeNbt(NbtCompound nbt) {
    super.writeNbt(nbt);

    NbtList nbtList = new NbtList();
    for (ItemStack plush : plushStack
    ) {
      NbtCompound itemNbt = new NbtCompound();
      plush.writeNbt(itemNbt);
      nbtList.add(itemNbt);
    }
    nbt.put("plushStack", nbtList);
  }

  @Override
  public void readNbt(NbtCompound nbt) {
    super.readNbt(nbt);
    NbtList nbtList = nbt.getList("plushStack", 10);
    for (int i = 0; i < nbtList.size(); i++) {
      NbtCompound itemNbt = nbtList.getCompound(i);
      ItemStack itemStack = ItemStack.fromNbt(itemNbt);
      plushStack[i] = itemStack;
    }
    sync();
  }

  public void sync() {
    markDirty();
    if (world == null) return;
    world.updateListeners(pos, this.getCachedState(), this.getCachedState(), 3);
  }

  @Nullable
  @Override
  public Packet<ClientPlayPacketListener> toUpdatePacket() {
    return BlockEntityUpdateS2CPacket.create(this);
  }

  @Override
  public NbtCompound toInitialChunkDataNbt() {
    return createNbt();
  }


}



