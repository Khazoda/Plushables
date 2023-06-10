package com.seacroak.plushables.block.tile;

import java.util.Optional;

import com.seacroak.plushables.gui.BuilderInventory;
import com.seacroak.plushables.gui.BuilderScreenHandler;
import com.seacroak.plushables.recipe.BuilderRecipe;
import com.seacroak.plushables.registry.MainRegistry;
import com.seacroak.plushables.registry.SoundRegistry;
import com.seacroak.plushables.registry.TileRegistry;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.LocalRandom;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class BuilderTileEntity extends BlockEntity
		implements GeoBlockEntity, NamedScreenHandlerFactory, BuilderInventory {

	static Random rand;
	private static boolean shouldHop;

	// Recipe / Inventory Code
	private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(4, ItemStack.EMPTY);
	protected final PropertyDelegate propertyDelegate;
	private int progress = 0;
	private int maxProgress = 63;

	public BuilderTileEntity(BlockPos pos, BlockState state) {

		super(TileRegistry.BUILDER_TILE, pos, state);
		shouldHop = false;
		rand = new LocalRandom(100);
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
		return this.getCachedState().getBlock().getName();
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

		return match.isPresent()
				&& canInsertAmountIntoOutputSlot(inventory)
				&& canInsertItemIntoOutputSlot(inventory, match.get().getOutput(entity.world.getRegistryManager()));
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
			entity.removeStack(3, 1);
			entity.setStack(2, new ItemStack(match.get().getOutput(entity.world.getRegistryManager()).getItem(),
					entity.getStack(2).getCount() + 1));

			if (!world.isClient()) {
				// ! TODO implement networked solution for syncing hop animation across clients
				// ! (currently works in SP but not MP as code is only ran on server)
				shouldHop = true;
				// System.out.println(rand.nextBetween(0, 20));
				// One in 20 chance to spawn an Allay
				if (rand.nextBetween(0, 20) == 13) {
					EntityType.FIREWORK_ROCKET
							.spawn((ServerWorld) world, null, null, entity.pos.add(0, 2, 0),
									SpawnReason.TRIGGERED, true, true);

					EntityType.ALLAY
							.spawn((ServerWorld) world, null, null, entity.pos.add(0, 2, 0),
									SpawnReason.TRIGGERED, true, true)
							.equipStack(EquipmentSlot.MAINHAND, new ItemStack(MainRegistry.FROGLIN_PLUSHABLE));
				} else {
					world.playSound(null, entity.getPos(), SoundRegistry.BUILDER_DING,
							SoundCategory.BLOCKS, 100f, 1f);
					world.playSound(null, entity.getPos(), SoundEvents.BLOCK_MOSS_PLACE,
							SoundCategory.BLOCKS, 0.7f, 1f);
				}

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
	private final AnimatableInstanceCache instanceCache = GeckoLibUtil.createInstanceCache(this);

	private <E extends BlockEntity & GeoAnimatable> PlayState builderIdlePredicate(AnimationState<E> event) {
		AnimationController<?> controller = event.getController();
		controller.setAnimation(RawAnimation.begin().thenPlay("animation.builder.idle"));
		return PlayState.CONTINUE;
	}

	private <E extends BlockEntity & GeoAnimatable> PlayState cubePredicateSpin(AnimationState<E> event) {
		AnimationController<?> controller = event.getController();
		controller.transitionLength(0);

		controller.setAnimation(RawAnimation.begin().thenPlay("animation.builder.cube_spin"));
		// controller.markNeedsReload();
		// .addAnimation("Botarium.anim.idle", true));

		return PlayState.CONTINUE;
	}

	private <E extends BlockEntity & GeoAnimatable> PlayState cubePredicateHop(AnimationState<E> event) {
		AnimationController<?> controller = event.getController();
		// controller.transitionLengthTicks = 0;

		if (shouldHop) {
			controller.setAnimation(RawAnimation.begin().thenPlay("animation.builder.edgecubes_jump"));
			if (controller.getAnimationState() == AnimationController.State.STOPPED) {
				shouldHop = false;
			}
			// .addAnimation("fertilizer.animation.idle", true));
		} else {
			// controller.setAnimation(new AnimationBuilder());
			controller.forceAnimationReset();
			// .addAnimation("Botarium.anim.idle", true));
		}
		return PlayState.CONTINUE;
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(
				new AnimationController<BuilderTileEntity>(this, "controller", 0, this::builderIdlePredicate));

		controllers.add(
				new AnimationController<BuilderTileEntity>(this, "cube_spin_controller", 0,
						this::cubePredicateSpin));
		controllers.add(
				new AnimationController<BuilderTileEntity>(this, "cube__hop_controller", 0,
						this::cubePredicateHop));
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.instanceCache;
	}

}
