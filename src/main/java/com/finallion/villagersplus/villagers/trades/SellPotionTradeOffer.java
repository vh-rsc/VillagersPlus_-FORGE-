package com.finallion.villagersplus.villagers.trades;


import net.minecraft.core.Registry;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class SellPotionTradeOffer implements VillagerTrades.ItemListing {
    private final ItemStack sell;
    private final int price;
    private final int maxUses;
    private final int experience;
    private final Item secondBuy;
    private final float priceMultiplier;

    public SellPotionTradeOffer(Item potionItem, Item potionEffectItem, int price, int maxUses, int experience) {
        this.sell = new ItemStack(potionEffectItem);
        this.price = price;
        this.maxUses = maxUses;
        this.experience = experience;
        this.secondBuy = potionItem;
        this.priceMultiplier = 0.05F;
    }


    public MerchantOffer getOffer(Entity entity, Random random) {
        ItemStack itemStack = new ItemStack(Items.EMERALD, this.price);
        List<Potion> list = (List) Registry.POTION.stream().filter((potionx) -> {
            return !potionx.getEffects().isEmpty() && PotionBrewing.isBrewablePotion(potionx);
        }).collect(Collectors.toList());


        Potion potion = (Potion)list.get(random.nextInt(list.size()));
        ItemStack itemStack2 = PotionUtils.setPotion(new ItemStack(this.sell.getItem(), 1), potion);
        return new MerchantOffer(itemStack, PotionUtils.setPotion(new ItemStack(this.secondBuy, 1), Potions.WATER), itemStack2, this.maxUses, this.experience, this.priceMultiplier);
    }
}
