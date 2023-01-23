package com.finallion.villagersplus.blockentities;

import com.finallion.villagersplus.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;

public class OceanographerTableBlockEntity extends BlockEntity implements Container, WorldlyContainer {
    private NonNullList<ItemStack> inventory;
    private static final int[] SLOTS = new int[]{0, 1, 2, 3, 4};

    public OceanographerTableBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlocks.OCEANOGRAPHER_TABLE_BLOCK_ENTITY, pos, state);
        this.inventory = NonNullList.withSize(5, ItemStack.EMPTY);
    }

    public int getContainerSize() {
        return this.inventory.size();
    }

    public CompoundTag getUpdateTag() {
        CompoundTag compoundtag = new CompoundTag();
        ContainerHelper.saveAllItems(compoundtag, this.inventory, true);
        return compoundtag;
    }

    public boolean insertCoral(ItemStack coral, int slot) {
        ItemStack itemStack = (ItemStack)this.inventory.get(slot);
        if (itemStack.isEmpty()) {
            this.inventory.set(slot, coral.split(1));
            this.markUpdated();
            return true;
        }
        return false;
    }

    public NonNullList<ItemStack> getItems() {
        return inventory;
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

    private void markUpdated() {
        this.setChanged();
        this.getLevel().sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
    }

    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public void load(CompoundTag nbt) {
        super.load(nbt);
        this.inventory = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(nbt, this.inventory);
    }

    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        ContainerHelper.saveAllItems(nbt, this.inventory);
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

    public void clearContent() {
        this.inventory.clear();
    }

    public int[] getSlotsForFace(Direction side) {
        return SLOTS;
    }

    public boolean canPlaceItemThroughFace(int slot, ItemStack stack, @Nullable Direction dir) {
        return this.canPlaceItem(slot, stack);
    }

    public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction dir) {
        return false;
    }



}
