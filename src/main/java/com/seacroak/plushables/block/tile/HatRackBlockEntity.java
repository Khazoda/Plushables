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

import java.util.List;

public class HatRackBlockEntity extends BlockEntity {
  private final DefaultedList<ItemStack> hats = DefaultedList.ofSize(2, ItemStack.EMPTY);

  public HatRackBlockEntity(BlockPos pos, BlockState state) {
    super(TileRegistry.HAT_RACK_TILE, pos, state);
  }

  public boolean setLeftHat(PlayerEntity player, ItemStack item) {
    this.hats.set(0, item);
    if (this.hats.get(0) != ItemStack.EMPTY && this.hats.get(0) == item){
      NbtCompound nbtTag = new NbtCompound();
      writeNbt(nbtTag);
      sync();
      return true;
    }
    return false;
  }

  public boolean setRightHat(PlayerEntity player, ItemStack item) {
    this.hats.set(1, item);
    if (this.hats.get(1) != ItemStack.EMPTY && this.hats.get(1) == item) {
      NbtCompound nbtTag = new NbtCompound();
      writeNbt(nbtTag);
      sync();
      return true;
    }
    return false;
  }

  public DefaultedList<ItemStack> getHats() {
    return this.hats;
  }

  /* Data Serialization */
  @Override
  public void readNbt(NbtCompound nbt) {
    super.readNbt(nbt);
    Inventories.readNbt(nbt, hats);
  }

  @Override
  public void writeNbt(NbtCompound nbt) {
    Inventories.writeNbt(nbt, hats);
    return;
  }

  public static void tick(World world, BlockPos pos, BlockState state, HatRackBlockEntity be) {
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
