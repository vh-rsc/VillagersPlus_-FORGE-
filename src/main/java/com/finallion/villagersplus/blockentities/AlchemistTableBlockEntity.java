package com.finallion.villagersplus.blockentities;

import com.finallion.villagersplus.blocks.AlchemistTableBlock;
import com.finallion.villagersplus.client.screen.AlchemistTableScreenHandler;
import com.finallion.villagersplus.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class AlchemistTableBlockEntity extends BaseContainerBlockEntity implements WorldlyContainer {
    private static final int[] TOP_SLOTS = new int[]{3};
    private static final int[] BOTTOM_SLOTS = new int[]{0, 1, 2, 3};
    private static final int[] SIDE_SLOTS = new int[]{0, 1, 2, 4};
    private NonNullList<ItemStack> inventory = NonNullList.withSize(5, ItemStack.EMPTY);
    private boolean[] slotsEmptyLastTick;
    private Item itemBrewing;
    int brewTime;
    int fuel;
    protected final ContainerData propertyDelegate = new ContainerData() {
        public int get(int index) {
            switch(index) {
                case 0:
                    return AlchemistTableBlockEntity.this.brewTime;
                case 1:
                    return AlchemistTableBlockEntity.this.fuel;
                default:
                    return 0;
            }
        }

        public void set(int index, int value) {
            switch(index) {
                case 0:
                    AlchemistTableBlockEntity.this.brewTime = value;
                    break;
                case 1:
                    AlchemistTableBlockEntity.this.fuel = value;
            }

        }

        public int getCount() {
            return 2;
        }
    };
    public static List<ItemStack> potions = new ArrayList<>(){
        {
            add(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WATER));
            add(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.MUNDANE));
            add(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.AWKWARD));
            add(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.FIRE_RESISTANCE));
            add(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WATER_BREATHING));
            add(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.HARMING));
            add(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.HEALING));
            add(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.INVISIBILITY));
            add(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.LEAPING));
            add(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.NIGHT_VISION));
            add(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.POISON));
            add(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.REGENERATION));
            add(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.SLOW_FALLING));
            add(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.SLOWNESS));
            add(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.STRENGTH));
            add(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.SWIFTNESS));
            add(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WEAKNESS));
            add(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.TURTLE_MASTER));
            add(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.LONG_FIRE_RESISTANCE));
            add(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.LONG_INVISIBILITY));
            add(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.LONG_NIGHT_VISION));
            add(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.LONG_LEAPING));
            add(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.LONG_POISON));
            add(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.LONG_REGENERATION));
            add(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.LONG_SLOW_FALLING));
            add(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.LONG_SLOWNESS));
            add(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.LONG_STRENGTH));
            add(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.LONG_SWIFTNESS));
            add(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.LONG_TURTLE_MASTER));
            add(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.LONG_WATER_BREATHING));
            add(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.LONG_WEAKNESS));
            add(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.STRONG_HARMING));
            add(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.STRONG_HEALING));
            add(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.STRONG_LEAPING));
            add(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.STRONG_POISON));
            add(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.STRONG_REGENERATION));
            add(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.STRONG_SLOWNESS));
            add(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.STRONG_STRENGTH));
            add(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.STRONG_SWIFTNESS));
            add(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.STRONG_TURTLE_MASTER));

            add(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), Potions.WATER));
            add(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), Potions.MUNDANE));
            add(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), Potions.AWKWARD));
            add(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), Potions.FIRE_RESISTANCE));
            add(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), Potions.WATER_BREATHING));
            add(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), Potions.HARMING));
            add(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), Potions.HEALING));
            add(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), Potions.INVISIBILITY));
            add(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), Potions.LEAPING));
            add(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), Potions.NIGHT_VISION));
            add(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), Potions.POISON));
            add(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), Potions.REGENERATION));
            add(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), Potions.SLOW_FALLING));
            add(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), Potions.SLOWNESS));
            add(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), Potions.STRENGTH));
            add(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), Potions.SWIFTNESS));
            add(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), Potions.WEAKNESS));
            add(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), Potions.TURTLE_MASTER));
            add(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), Potions.LONG_FIRE_RESISTANCE));
            add(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), Potions.LONG_INVISIBILITY));
            add(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), Potions.LONG_NIGHT_VISION));
            add(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), Potions.LONG_LEAPING));
            add(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), Potions.LONG_POISON));
            add(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), Potions.LONG_REGENERATION));
            add(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), Potions.LONG_SLOW_FALLING));
            add(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), Potions.LONG_SLOWNESS));
            add(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), Potions.LONG_STRENGTH));
            add(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), Potions.LONG_SWIFTNESS));
            add(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), Potions.LONG_TURTLE_MASTER));
            add(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), Potions.LONG_WATER_BREATHING));
            add(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), Potions.LONG_WEAKNESS));
            add(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), Potions.STRONG_HARMING));
            add(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), Potions.STRONG_HEALING));
            add(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), Potions.STRONG_LEAPING));
            add(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), Potions.STRONG_POISON));
            add(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), Potions.STRONG_REGENERATION));
            add(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), Potions.STRONG_SLOWNESS));
            add(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), Potions.STRONG_STRENGTH));
            add(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), Potions.STRONG_SWIFTNESS));
            add(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), Potions.STRONG_TURTLE_MASTER));
        }
    };


    public AlchemistTableBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlocks.ALCHEMIST_TABLE_BLOCK_ENTITY.get(), pos, state);
    }


    protected Component getDefaultName() {
        return new TranslatableComponent("container.alchemist_table");
    }

    public int getContainerSize() {
        return this.inventory.size();
    }

    public boolean isEmpty() {
        Iterator var1 = this.inventory.iterator();

        ItemStack itemStack;
        do {
            if (!var1.hasNext()) {
                return true;
            }

            itemStack = (ItemStack)var1.next();
        } while(itemStack.isEmpty());

        return false;
    }


    public static void tick(Level world, BlockPos pos, BlockState state, AlchemistTableBlockEntity blockEntity) {
        ItemStack itemStack = (ItemStack)blockEntity.inventory.get(4);
        if (blockEntity.fuel <= 0 && itemStack.is(Items.GUNPOWDER)) {
            blockEntity.fuel = 20;
            itemStack.shrink(1);
            setChanged(world, pos, state);
        }

        if (blockEntity.fuel <= 0) {
            state = (BlockState)state.setValue(AlchemistTableBlock.HAS_FUEL, false);
        } else {
            state = (BlockState)state.setValue(AlchemistTableBlock.HAS_FUEL, true);
        }

        boolean bl = canCraft(blockEntity.inventory);
        boolean bl2 = blockEntity.brewTime > 0;
        ItemStack itemStack2 = (ItemStack)blockEntity.inventory.get(3);

        if (!itemStack2.isEmpty()) {
            state = (BlockState)state.setValue(AlchemistTableBlock.BOTTLE_PROPERTIES[3], true);
        } else {
            state = (BlockState)state.setValue(AlchemistTableBlock.BOTTLE_PROPERTIES[3], false);
        }


        if (bl2) {
            --blockEntity.brewTime;
            boolean bl3 = blockEntity.brewTime == 0;
            state = (BlockState)state.setValue(AlchemistTableBlock.IS_BREWING, true);
            if (world.random.nextInt(8) == 0) {
                world.playSound(null, pos, SoundEvents.FIRE_AMBIENT, SoundSource.BLOCKS, 0.5F, 0.5F);
            }

            if (bl3 && bl) {
                craft(world, pos, blockEntity.inventory);
                setChanged(world, pos, state);
            } else if (!bl || !itemStack2.is(blockEntity.itemBrewing)) {
                blockEntity.brewTime = 0;
                setChanged(world, pos, state);
            }
        } else if (bl && blockEntity.fuel > 0) {
            blockEntity.fuel -= 20;
            blockEntity.brewTime = 400;
            blockEntity.itemBrewing = itemStack2.getItem();
            setChanged(world, pos, state);
        }

        if (blockEntity.brewTime <= 0) {
            state = (BlockState)state.setValue(AlchemistTableBlock.IS_BREWING, false);
        }

        world.setBlock(pos, state, 2);

        boolean[] bls = blockEntity.getSlotsEmpty();
        if (!Arrays.equals(bls, blockEntity.slotsEmptyLastTick)) {
            blockEntity.slotsEmptyLastTick = bls;
            BlockState blockState = state;
            if (!(state.getBlock() instanceof AlchemistTableBlock)) {
                return;
            }

            for(int i = 0; i < AlchemistTableBlock.BOTTLE_PROPERTIES.length - 1; ++i) {
                blockState = (BlockState)blockState.setValue(AlchemistTableBlock.BOTTLE_PROPERTIES[i], bls[i]);
            }

            world.setBlock(pos, blockState, 2);
        }

    }

    private boolean[] getSlotsEmpty() {
        boolean[] bls = new boolean[3];

        for(int i = 0; i < 3; ++i) {
            if (!((ItemStack)this.inventory.get(i)).isEmpty()) {
                bls[i] = true;
            }
        }

        return bls;
    }

    private static boolean canCraft(NonNullList<ItemStack> slots) {
        ItemStack itemStack = (ItemStack)slots.get(3);
        if (itemStack.isEmpty()) {
            return false;
        } else if (!(itemStack.is(Items.LINGERING_POTION) || itemStack.is(Items.SPLASH_POTION) || itemStack.is(Items.POTION))) {
            return false;
        } else {
            for(int i = 0; i < 3; ++i) {
                ItemStack itemStack2 = (ItemStack)slots.get(i);
                if (!itemStack2.isEmpty() /*&& BrewingRecipeRegistry.hasRecipe(itemStack2, itemStack)*/) {
                    return true;
                }
            }

            return false;
        }
    }

    private static void craft(Level world, BlockPos pos, NonNullList<ItemStack> slots) {
        // ingredient slot
        ItemStack itemStack = (ItemStack)slots.get(3);

        if (world.getRandom().nextInt(3) == 0) {
            for(int i = 0; i < 3; ++i) {
                if (!slots.get(i).isEmpty()) {
                    slots.set(i, potions.get(world.random.nextInt(potions.size())));
                }
            }
        } else {
            world.explode(null, pos.getX(), pos.getY(), pos.getZ(), 3.0F, Explosion.BlockInteraction.NONE);
            slots.set(0, ItemStack.EMPTY);
            slots.set(1, ItemStack.EMPTY);
            slots.set(2, ItemStack.EMPTY);
            slots.set(4, ItemStack.EMPTY);
        }

        itemStack.shrink(1);

        slots.set(3, itemStack);
        world.levelEvent(1035, pos, 0);
    }

    public void load(CompoundTag nbt) {
        super.load(nbt);
        this.inventory = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(nbt, this.inventory);
        this.brewTime = nbt.getShort("BrewTime");
        this.fuel = nbt.getByte("Fuel");
    }

    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        nbt.putShort("BrewTime", (short)this.brewTime);
        ContainerHelper.saveAllItems(nbt, this.inventory);
        nbt.putByte("Fuel", (byte)this.fuel);
    }

    public ItemStack getItem(int slot) {
        return slot >= 0 && slot < this.inventory.size() ? (ItemStack)this.inventory.get(slot) : ItemStack.EMPTY;
    }

    public ItemStack removeItem(int slot, int amount) {
        return ContainerHelper.removeItem(this.inventory, slot, amount);
    }

    public ItemStack removeItemNoUpdate(int slot) {
        return ContainerHelper.takeItem(this.inventory, slot);
    }

    public void setItem(int slot, ItemStack stack) {
        if (slot >= 0 && slot < this.inventory.size()) {
            this.inventory.set(slot, stack);
        }

    }

    public boolean stillValid(Player player) {
        if (this.level.getBlockEntity(this.worldPosition) != this) {
            return false;
        } else {
            return !(player.distanceToSqr((double)this.worldPosition.getX() + 0.5D, (double)this.worldPosition.getY() + 0.5D, (double)this.worldPosition.getZ() + 0.5D) > 64.0D);
        }
    }

    public boolean canPlaceItem(int slot, ItemStack stack) {
        if (slot == 3) {
            return stack.is(Items.POTION) || stack.is(Items.SPLASH_POTION) || stack.is(Items.LINGERING_POTION) || stack.is(Items.GLASS_BOTTLE);
        } else if (slot == 4) { // fuel
            return stack.is(Items.GUNPOWDER);
        } else {
            return stack.is(Items.GLASS_BOTTLE) && this.getItem(slot).isEmpty();
        }
    }

    public int[] getSlotsForFace(Direction side) {
        if (side == Direction.UP) {
            return TOP_SLOTS;
        } else {
            return side == Direction.DOWN ? BOTTOM_SLOTS : SIDE_SLOTS;
        }
    }

    public boolean canPlaceItemThroughFace(int slot, ItemStack stack, @Nullable Direction dir) {
        return this.canPlaceItem(slot, stack);
    }

    public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction dir) {
        return slot != 3 || stack.is(Items.GLASS_BOTTLE);
    }

    public void clearContent() {
        this.inventory.clear();
    }

    protected AbstractContainerMenu createMenu(int syncId, Inventory playerInventory) {
        return new AlchemistTableScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }


}
