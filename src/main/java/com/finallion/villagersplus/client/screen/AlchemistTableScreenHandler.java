package com.finallion.villagersplus.client.screen;

import com.finallion.villagersplus.init.ModScreen;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;

public class AlchemistTableScreenHandler extends AbstractContainerMenu {
    private final Container inventory;
    private final ContainerData propertyDelegate;
    private final Slot ingredientSlot;

    public AlchemistTableScreenHandler(int syncId, Inventory playerInventory) {
        this(syncId, playerInventory, new SimpleContainer(5), new SimpleContainerData(2));
    }

    public AlchemistTableScreenHandler(int syncId, Inventory playerInventory, Container inventory, ContainerData propertyDelegate) {
        super(ModScreen.ALCHEMIST_TABLE_SCREEN_HANDLER, syncId);
        checkContainerSize(inventory, 5);
        checkContainerDataCount(propertyDelegate, 2);
        this.inventory = inventory;
        this.propertyDelegate = propertyDelegate;
        this.addSlot(new PotionSlot(inventory, 0, 56, 51));
        this.addSlot(new PotionSlot(inventory, 1, 79, 58));
        this.addSlot(new PotionSlot(inventory, 2, 102, 51));
        this.ingredientSlot = this.addSlot(new IngredientSlot(inventory, 3, 79, 17));
        this.addSlot(new FuelSlot(inventory, 4, 17, 17));
        this.addDataSlots(propertyDelegate);

        int i;
        for(i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for(i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }

    }


    public boolean stillValid(Player p_39098_) {
        return this.inventory.stillValid(p_39098_);
    }


    public ItemStack quickMoveStack(Player p_39100_, int p_39101_) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(p_39101_);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if ((p_39101_ < 0 || p_39101_ > 2) && p_39101_ != 3 && p_39101_ != 4) {
                if (AlchemistTableScreenHandler.FuelSlot.matches(itemstack)) {
                    if (this.moveItemStackTo(itemstack1, 4, 5, false) || this.ingredientSlot.mayPlace(itemstack1) && !this.moveItemStackTo(itemstack1, 3, 4, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (this.ingredientSlot.mayPlace(itemstack1)) {
                    if (!this.moveItemStackTo(itemstack1, 3, 4, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (AlchemistTableScreenHandler.PotionSlot.matches(itemstack)) {
                    if (!this.moveItemStackTo(itemstack1, 0, 3, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (p_39101_ >= 5 && p_39101_ < 32) {
                    if (!this.moveItemStackTo(itemstack1, 32, 41, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (p_39101_ >= 32 && p_39101_ < 41) {
                    if (!this.moveItemStackTo(itemstack1, 5, 32, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (!this.moveItemStackTo(itemstack1, 5, 41, false)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (!this.moveItemStackTo(itemstack1, 5, 41, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(itemstack1, itemstack);
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(p_39100_, itemstack1);
        }

        return itemstack;
    }

    public int getFuel() {
        return this.propertyDelegate.get(1);
    }

    public int getBrewTime() {
        return this.propertyDelegate.get(0);
    }

    static class PotionSlot extends Slot {
        public PotionSlot(Container inventory, int i, int j, int k) {
            super(inventory, i, j, k);
        }

        public boolean mayPlaceItem(ItemStack stack) {
            return matches(stack);
        }

        public int getMaxStackSize() {
            return 1;
        }

        public static boolean matches(ItemStack stack) {
            return stack.is(Items.GLASS_BOTTLE) || stack.is(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WATER).getItem());
        }
    }

    private static class IngredientSlot extends Slot {
        public IngredientSlot(Container inventory, int i, int j, int k) {
            super(inventory, i, j, k);
        }

        public boolean mayPlaceItem(ItemStack stack) {
            return matches(stack);
        }

        public int getMaxStackSize() {
            return 1;
        }

        public static boolean matches(ItemStack stack) {
            if (PotionUtils.getPotion(stack) == PotionUtils.getPotion(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WATER))) {
                return false;
            }
            return stack.is(Items.POTION) || stack.is(Items.SPLASH_POTION) || stack.is(Items.LINGERING_POTION);
        }
    }

    private static class FuelSlot extends Slot {
        public FuelSlot(Container inventory, int i, int j, int k) {
            super(inventory, i, j, k);
        }

        public boolean mayPlaceItem(ItemStack stack) {
            return matches(stack);
        }

        public static boolean matches(ItemStack stack) {
            return stack.is(Items.GUNPOWDER);
        }

        public int getMaxStackSize() {
            return 64;
        }
    }
}

