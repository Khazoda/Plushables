package com.seacroak.plushables.block.tile;

import com.seacroak.plushables.registry.TileRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class BasketBlockEntity extends BlockEntity {
  private final DefaultedList<ItemStack> plushieStack = DefaultedList.ofSize(4, ItemStack.EMPTY);
  private int topIndex;
  private boolean isEmpty = true;

  public BasketBlockEntity(BlockPos pos, BlockState state) {
    super(TileRegistry.HAT_RACK_TILE, pos, state);
    this.topIndex = 0;
    if (this.plushieStack.get(0) != ItemStack.EMPTY) this.isEmpty = false;
  }

  public void addToStack(PlayerEntity player, ItemStack item) {

    this.plushieStack.set(topIndex, new ItemStack(Items.ANDESITE));
    if (this.topIndex != 3) this.topIndex += 1;

//    this.isEmpty = false;
//    player.getActiveItem().setCount(player.getActiveItem().getCount() - 1);
//    this.plushieStack.set(topIndex, item);
//    this.isEmpty = false;
//    if (this.plushieStack.get(topIndex) != ItemStack.EMPTY && this.plushieStack.get(topIndex) == item) {
//      if (this.topIndex != 3) this.topIndex += 1;
//      sync();
//      return true;
//    }
//    return false;
  }

  public void removeFromStack(PlayerEntity player) {
        this.plushieStack.set(topIndex, ItemStack.EMPTY);
      if (this.topIndex != 0) this.topIndex -= 1;


//    if (topIndex == 0 && this.plushieStack.get(0) == ItemStack.EMPTY) {
//      this.isEmpty = true;
//      return false;
//    }
//    player.giveItemStack(plushieStack.get(topIndex));
//    this.plushieStack.set(topIndex, ItemStack.EMPTY);
//
//    if (this.plushieStack.get(topIndex) == ItemStack.EMPTY) {
//      if (this.topIndex != 0) this.topIndex -= 1;
//      sync();
//      return true;
//    }
//    return false;
  }

  public DefaultedList<ItemStack> getPlushieStack() {
    System.out.println(this.topIndex);
    System.out.println(this.plushieStack.get(this.topIndex));
    return this.plushieStack;
  }

  public boolean isEmpty() {
    return this.isEmpty;
  }

  /* Data Serialization */
  @Override
  public void readNbt(NbtCompound nbt) {
    Inventories.readNbt(nbt, this.plushieStack);
    topIndex = nbt.getInt("topIndex");
    isEmpty = nbt.getBoolean("isEmpty");
  }

  @Override
  protected void writeNbt(NbtCompound compound) {
    NbtCompound nbt = new NbtCompound();
    Inventories.writeNbt(nbt, plushieStack);
    nbt.putInt("topIndex", topIndex);
    nbt.putBoolean("isEmpty", isEmpty);
  }

  public static void tick(World world, BlockPos pos, BlockState state, BasketBlockEntity be) {
    if (world.isClient) return;
  }

  @Nullable
  @Override
  public Packet<ClientPlayPacketListener> toUpdatePacket() {
    return BlockEntityUpdateS2CPacket.create(this);
  }

  @Override
  public NbtCompound toInitialChunkDataNbt() {
    NbtCompound nbt = new NbtCompound();
    return nbt;
  }

  public void sync() {
    markDirty();
    world.updateNeighbors(pos, world.getBlockState(pos).getBlock());
    world.updateListeners(pos, world.getBlockState(pos), getCachedState(), Block.NOTIFY_LISTENERS);
  }
}
