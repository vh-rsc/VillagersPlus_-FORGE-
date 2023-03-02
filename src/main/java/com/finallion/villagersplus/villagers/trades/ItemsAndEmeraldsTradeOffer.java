package com.finallion.villagersplus.villagers.trades;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.ItemLike;

import javax.annotation.Nullable;
import java.util.Random;

public class ItemsAndEmeraldsTradeOffer implements VillagerTrades.ItemListing {
    private final ItemStack fromItem;
    private final int fromCount;
    private final int emeraldCost;
    private final ItemStack toItem;
    private final int toCount;
    private final int maxUses;
    private final int villagerXp;
    private final float priceMultiplier;

    public ItemsAndEmeraldsTradeOffer(ItemLike p_35725_, int p_35726_, Item p_35727_, int p_35728_, int p_35729_, int p_35730_) {
        this(p_35725_, p_35726_, 1, p_35727_, p_35728_, p_35729_, p_35730_);
    }

    public ItemsAndEmeraldsTradeOffer(ItemLike p_35717_, int p_35718_, int p_35719_, Item p_35720_, int p_35721_, int p_35722_, int p_35723_) {
        this.fromItem = new ItemStack(p_35717_);
        this.fromCount = p_35718_;
        this.emeraldCost = p_35719_;
        this.toItem = new ItemStack(p_35720_);
        this.toCount = p_35721_;
        this.maxUses = p_35722_;
        this.villagerXp = p_35723_;
        this.priceMultiplier = 0.05F;
    }

    @Nullable
    public MerchantOffer getOffer(Entity p_219696_, Random p_219697_) {
        return new MerchantOffer(new ItemStack(Items.EMERALD, this.emeraldCost), new ItemStack(this.fromItem.getItem(), this.fromCount), new ItemStack(this.toItem.getItem(), this.toCount), this.maxUses, this.villagerXp, this.priceMultiplier);
    }
}
