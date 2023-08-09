package com.seacroak.plushables.block.tile;

import com.seacroak.plushables.registry.TileRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
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

  public BasketBlockEntity(BlockPos pos, BlockState state) {
    super(TileRegistry.HAT_RACK_TILE, pos, state);
    this.topIndex = 0;
  }

  public boolean addToStack(PlayerEntity player, ItemStack item) {
    System.out.println(topIndex);
    player.getActiveItem().setCount(player.getActiveItem().getCount() - 1);
    this.plushieStack.set(topIndex, item);
    if (this.plushieStack.get(topIndex) != ItemStack.EMPTY && this.plushieStack.get(topIndex) == item) {
      if(this.topIndex != 3) this.topIndex += 1;
      NbtCompound nbtTag = new NbtCompound();
      writeNbt(nbtTag);
      sync();
      return true;
    }
    return false;
  }

  public boolean removeFromStack(PlayerEntity player) {
    if (topIndex == 0 && this.plushieStack.get(0) == ItemStack.EMPTY) return false;
    player.giveItemStack(plushieStack.get(topIndex));
    this.plushieStack.set(topIndex, ItemStack.EMPTY);
    if (this.plushieStack.get(topIndex) == ItemStack.EMPTY) {
      if(this.topIndex != 0) this.topIndex -= 1;
      NbtCompound nbtTag = new NbtCompound();
      writeNbt(nbtTag);
      sync();
      return true;
    }
    return false;
  }

  public DefaultedList<ItemStack> getPlushieStack() {
    return this.plushieStack;
  }

  /* Data Serialization */
  @Override
  public void readNbt(NbtCompound nbt) {
    super.readNbt(nbt);
    Inventories.readNbt(nbt, this.plushieStack);
    this.topIndex = nbt.getInt("topIndex");
  }

  @Override
  public void writeNbt(NbtCompound nbt) {
    Inventories.writeNbt(nbt, this.plushieStack);
    nbt.putInt("topIndex", this.topIndex);
    return;
  }

  public static void tick(World world, BlockPos pos, BlockState state, BasketBlockEntity be) {
    if (world.isClient) return;
  }

  @Nullable
  @Override
  public Packet<ClientPlayPacketListener> toUpdatePacket() {
    return BlockEntityUpdateS2CPacket.create(this, BlockEntity::toInitialChunkDataNbt);
  }

  @Override
  public NbtCompound toInitialChunkDataNbt() {
    NbtCompound nbtTag = new NbtCompound();
    writeNbt(nbtTag);
    return nbtTag;
  }

  protected void sync() {
    markDirty();
    world.updateListeners(getPos(), getCachedState(), getCachedState(), 3);
  }
}
