package com.seacroak.plushables.block.tile;

import com.seacroak.plushables.recipe.BuilderRecipe;
import com.seacroak.plushables.registry.TileRegistry;

import java.util.List;
import java.util.Optional;

import javax.annotation.Nullable;

import com.seacroak.plushables.gui.BuilderScreenHandler;
import com.seacroak.plushables.gui.BuilderInventory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.SmeltingRecipe;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class BuilderTileEntity extends BlockEntity
		implements IAnimatable, NamedScreenHandlerFactory, BuilderInventory {

	// Recipe / Inventory Code
	private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);
	protected final PropertyDelegate propertyDelegate;
	private int progress = 0;
	private int maxProgress = 63;

	public BuilderTileEntity(BlockPos pos, BlockState state) {
		super(TileRegistry.BUILDER_TILE, pos, state);
		this.propertyDelegate = new PropertyDelegate() {
			public int get(int index) {
				switch (index) {
					case 0:
						return BuilderTileEntity.this.progress;
					case 1:
						return BuilderTileEntity.this.maxProgress;
					default:
						return 0;
				}
			}

			public void set(int index, int value) {
				switch (index) {
					case 0:
						BuilderTileEntity.this.progress = value;
						break;
					case 1:
						BuilderTileEntity.this.maxProgress = value;
						break;
				}
			}

			public int size() {
				return 2;
			}
		};
	}

	@Override
	public Text getDisplayName() {
		return Text.literal("Builder");
	}

	@Nullable
	@Override
	public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
		return new BuilderScreenHandler(syncId, inv, this, this.propertyDelegate);
	}

	@Override
	public DefaultedList<ItemStack> getItems() {
		return inventory;
	}

	@Override
	public void readNbt(NbtCompound nbt) {
		super.readNbt(nbt);
		Inventories.readNbt(nbt, inventory);
	}

	@Override
	public void writeNbt(NbtCompound nbt) {
		Inventories.writeNbt(nbt, inventory);
		return;
	}

	public static void tick(World world, BlockPos pos, BlockState state, BuilderTileEntity entity) {

		if (hasRecipe(entity)) {

			entity.progress++;
			if (entity.progress > entity.maxProgress) {
				craftItem(entity);
			}
		} else {
			entity.resetProgress();
		}
	}

	private static boolean hasRecipe(BuilderTileEntity entity) {
		World world = entity.world;
		SimpleInventory inventory = new SimpleInventory(entity.inventory.size());
		for (int i = 0; i < entity.inventory.size(); i++) {
			inventory.setStack(i, entity.getStack(i));

		}

		Optional<BuilderRecipe> match = world.getRecipeManager()
				.getFirstMatch(BuilderRecipe.Type.INSTANCE, inventory, world);

		List<BuilderRecipe> allPlushablesMatches = world.getRecipeManager()
				.getAllMatches(BuilderRecipe.Type.INSTANCE, inventory, world);

		return match.isPresent()
				&& canInsertAmountIntoOutputSlot(inventory)
				&& canInsertItemIntoOutputSlot(inventory, match.get().getOutput());
	}

	private static void craftItem(BuilderTileEntity entity) {
		World world = entity.world;
		SimpleInventory inventory = new SimpleInventory(entity.inventory.size());
		for (int i = 0; i < entity.inventory.size(); i++) {
			inventory.setStack(i, entity.getStack(i));
		}

		Optional<BuilderRecipe> match = world.getRecipeManager()
				.getFirstMatch(BuilderRecipe.Type.INSTANCE, inventory, world);

		if (match.isPresent()) {
			entity.removeStack(0, 1);
			entity.removeStack(1, 1);
			entity.setStack(2, new ItemStack(match.get().getOutput().getItem(),
					entity.getStack(2).getCount() + 1));

			if (!world.isClient()) {
				EntityType.LIGHTNING_BOLT.spawn((ServerWorld) world, null, null, null, entity.pos,
						SpawnReason.TRIGGERED, true, true);
			}

			entity.resetProgress();
		}
	}

	private void resetProgress() {
		this.progress = 0;
	}

	private static boolean canInsertItemIntoOutputSlot(SimpleInventory inventory, ItemStack output) {
		return inventory.getStack(2).getItem() == output.getItem() || inventory.getStack(2).isEmpty();
	}

	private static boolean canInsertAmountIntoOutputSlot(SimpleInventory inventory) {
		return inventory.getStack(2).getMaxCount() > inventory.getStack(2).getCount();
	}

	// Animation Code
	private final AnimationFactory factory = new AnimationFactory(this);

	private <E extends BlockEntity & IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		AnimationController<?> controller = event.getController();
		controller.setAnimation(new AnimationBuilder().addAnimation("animation.builder.idle", true));
		return PlayState.CONTINUE;
	}

	@Override
	public void registerControllers(AnimationData data) {
		data.addAnimationController(new AnimationController<BuilderTileEntity>(this, "controller", 0, this::predicate));
	}

	@Override
	public AnimationFactory getFactory() {
		return this.factory;
	}

}
