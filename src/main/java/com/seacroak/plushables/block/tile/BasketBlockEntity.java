package com.seacroak.plushables.block.tile;

import com.seacroak.plushables.registry.TileRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.inventory.Inventories;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class BasketBlockEntity extends BlockEntity {
  private int bing = 0;

  public BasketBlockEntity(BlockPos pos, BlockState state) {
    super(TileRegistry.BASKET_TILE, pos, state);
  }

  public static void tick(World world, BlockPos pos, BlockState state, BasketBlockEntity be) {
  }

  public void setBing(int bing) {
    this.bing = bing;
    sync();
  }

  public int getBing() {
    return this.bing;
  }

  /* Data Serialization */
  @Override
  protected void writeNbt(NbtCompound nbt) {
    super.writeNbt(nbt);
    nbt.putInt("bing", bing);
  }

  @Override
  public void readNbt(NbtCompound nbt) {
    super.readNbt(nbt);
    bing = nbt.getInt("bing");
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

