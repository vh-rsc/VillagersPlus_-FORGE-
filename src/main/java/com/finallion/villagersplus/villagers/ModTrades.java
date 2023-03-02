package com.finallion.villagersplus.villagers;

import com.finallion.villagersplus.VillagersPlus;
import com.finallion.villagersplus.init.ModStructures;
import com.finallion.villagersplus.villagers.trades.BuyTradeOffer;
import com.finallion.villagersplus.villagers.trades.ItemsAndEmeraldsTradeOffer;
import com.finallion.villagersplus.villagers.trades.SellPotionTradeOffer;
import com.finallion.villagersplus.villagers.trades.SellTradeOffer;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = VillagersPlus.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModTrades {

    @SubscribeEvent
    public static void registerTrades(VillagerTradesEvent event) {
        Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

        if (event.getType() == ModProfessions.HORTICULTURIST.get()) {
            trades.get(1).add(new SellTradeOffer(Items.OXEYE_DAISY, 2, 1, 1));
            trades.get(1).add(new SellTradeOffer(Items.POPPY, 2, 1, 1));
            trades.get(1).add(new SellTradeOffer(Items.ALLIUM, 2, 1, 1));
            trades.get(1).add(new SellTradeOffer(Items.AZURE_BLUET, 2, 1, 1));
            trades.get(1).add(new SellTradeOffer(Items.CORNFLOWER, 2, 1, 1));
            trades.get(1).add(new SellTradeOffer(Items.LILY_OF_THE_VALLEY, 2, 1, 1));
            trades.get(1).add(new SellTradeOffer(Items.DANDELION, 2, 1, 1));
            trades.get(1).add(new BuyTradeOffer(Items.WHEAT_SEEDS, 1, 8, 1));
            trades.get(1).add(new BuyTradeOffer(Items.BEETROOT_SEEDS, 1, 12, 1));
            trades.get(1).add(new BuyTradeOffer(Items.MELON_SEEDS, 1, 12, 2));
            trades.get(1).add(new BuyTradeOffer(Items.PUMPKIN_SEEDS, 1, 12, 2));

            trades.get(2).add(new SellTradeOffer(Items.SPRUCE_LEAVES, 4, 2, 3));
            trades.get(2).add(new SellTradeOffer(Items.BIRCH_LEAVES, 4, 2, 3));
            trades.get(2).add(new SellTradeOffer(Items.ACACIA_LEAVES, 4, 2, 3));
            trades.get(2).add(new SellTradeOffer(Items.AZALEA_LEAVES, 4, 2, 3));
            trades.get(2).add(new SellTradeOffer(Items.DARK_OAK_LEAVES, 4, 2, 3));
            trades.get(2).add(new SellTradeOffer(Items.JUNGLE_LEAVES, 4, 2, 3));
            //trades.get(2).add(new SellTradeOffer(Items.MANGROVE_LEAVES, 4, 2, 3));
            trades.get(2).add(new SellTradeOffer(Items.OAK_LEAVES, 4, 2, 3));
            trades.get(2).add(new ItemsAndEmeraldsTradeOffer(Items.STICK, 3, Items.DEAD_BUSH, 1, 8, 4));
            trades.get(2).add(new BuyTradeOffer(Items.GRASS, 4, 12, 3));
            trades.get(2).add(new BuyTradeOffer(Items.FERN, 2, 12, 4));

            trades.get(3).add(new SellTradeOffer(Items.ORANGE_TULIP, 4, 1, 4));
            trades.get(3).add(new SellTradeOffer(Items.RED_TULIP, 4, 1, 4));
            trades.get(3).add(new SellTradeOffer(Items.WHITE_TULIP, 4, 1, 4));
            trades.get(3).add(new SellTradeOffer(Items.PINK_TULIP, 4, 1, 4));
            trades.get(3).add(new SellTradeOffer(Items.FLOWER_POT, 5, 1, 4));
            trades.get(3).add(new BuyTradeOffer(Items.SPRUCE_SAPLING, 2, 12, 3));
            trades.get(3).add(new BuyTradeOffer(Items.ACACIA_SAPLING, 2, 12, 3));
            trades.get(3).add(new BuyTradeOffer(Items.BIRCH_SAPLING, 2, 12, 3));
            trades.get(3).add(new BuyTradeOffer(Items.DARK_OAK_SAPLING, 2, 12, 3));
            trades.get(3).add(new BuyTradeOffer(Items.JUNGLE_SAPLING, 2, 12, 3));
            trades.get(3).add(new BuyTradeOffer(Items.OAK_SAPLING, 2, 12, 3));

            trades.get(4).add(new SellTradeOffer(Items.SUNFLOWER, 5, 1, 4));
            trades.get(4).add(new SellTradeOffer(Items.ROSE_BUSH, 5, 1, 4));
            trades.get(4).add(new SellTradeOffer(Items.LILAC, 5, 1, 4));
            trades.get(4).add(new SellTradeOffer(Items.PEONY, 5, 1, 4));
            trades.get(4).add(new BuyTradeOffer(Items.ROOTED_DIRT, 2, 32, 2));
            trades.get(4).add(new BuyTradeOffer(Items.PODZOL, 2, 32, 2));

            trades.get(5).add(new SellTradeOffer(Items.BLUE_ORCHID, 4, 1, 4));
            trades.get(5).add(new SellTradeOffer(Items.WITHER_ROSE, 8, 1, 5));
            trades.get(5).add(new SellTradeOffer(Items.FLOWERING_AZALEA_LEAVES, 4, 2, 2));
            trades.get(5).add(new SellTradeOffer(Items.SPORE_BLOSSOM, 12, 1, 4));
            trades.get(5).add(new BuyTradeOffer(Items.MOSS_BLOCK, 2, 12, 2));
            trades.get(5).add(new BuyTradeOffer(Items.BONE_MEAL, 4, 12, 2));

        } else if (event.getType() == ModProfessions.OCCULTIST.get()) {
            trades.get(1).add(new SellTradeOffer(Items.SMALL_AMETHYST_BUD, 1, 1, 1));
            trades.get(1).add(new SellTradeOffer(Items.MEDIUM_AMETHYST_BUD, 2, 1, 1));
            trades.get(1).add(new SellTradeOffer(Items.LARGE_AMETHYST_BUD, 3, 1, 2));
            trades.get(1).add(new SellTradeOffer(Items.AMETHYST_CLUSTER, 5, 1, 3));
            trades.get(1).add(new SellTradeOffer(Items.TINTED_GLASS, 8, 4, 4));
            trades.get(1).add(new BuyTradeOffer(Items.AMETHYST_SHARD, 3, 12, 4));
            trades.get(1).add(new BuyTradeOffer(Items.GLOW_INK_SAC, 1, 12, 4));

            trades.get(2).add(new SellTradeOffer(Items.SOUL_LANTERN, 4, 1, 3));
            trades.get(2).add(new SellTradeOffer(Items.SOUL_TORCH, 2, 1, 3));
            trades.get(2).add(new SellTradeOffer(Items.GLOWSTONE_DUST, 4, 2, 2));
            trades.get(2).add(new BuyTradeOffer(Items.SOUL_SAND, 2, 12, 4));
            trades.get(2).add(new BuyTradeOffer(Items.SOUL_SOIL, 2, 12, 4));

            trades.get(3).add(new SellTradeOffer(Items.RED_CANDLE, 4, 2, 4));
            trades.get(3).add(new SellTradeOffer(Items.ORANGE_CANDLE, 4, 2, 4));
            trades.get(3).add(new SellTradeOffer(Items.YELLOW_CANDLE, 4, 2, 4));
            trades.get(3).add(new SellTradeOffer(Items.LIME_CANDLE, 4, 2, 4));
            trades.get(3).add(new SellTradeOffer(Items.GREEN_CANDLE, 4, 2, 4));
            trades.get(3).add(new SellTradeOffer(Items.LIGHT_BLUE_CANDLE, 4, 2, 4));
            trades.get(3).add(new SellTradeOffer(Items.BLUE_CANDLE, 4, 2, 4));
            trades.get(3).add(new SellTradeOffer(Items.CYAN_CANDLE, 4, 2, 4));
            trades.get(3).add(new SellTradeOffer(Items.MAGENTA_CANDLE, 4, 2, 4));
            trades.get(3).add(new SellTradeOffer(Items.PINK_CANDLE, 4, 2, 4));
            trades.get(3).add(new SellTradeOffer(Items.PURPLE_CANDLE, 4, 2, 4));
            trades.get(3).add(new SellTradeOffer(Items.WHITE_CANDLE, 4, 2, 4));
            trades.get(3).add(new SellTradeOffer(Items.GRAY_CANDLE, 4, 2, 4));
            trades.get(3).add(new SellTradeOffer(Items.LIGHT_GRAY_CANDLE, 4, 2, 4));
            trades.get(3).add(new SellTradeOffer(Items.BLACK_CANDLE, 4, 2, 4));
            trades.get(3).add(new SellTradeOffer(Items.BROWN_CANDLE, 4, 2, 4));

            trades.get(4).add(new SellTradeOffer(Items.QUARTZ, 4, 2, 4));
            trades.get(4).add(new SellTradeOffer(Items.SPECTRAL_ARROW, 2, 2, 4));
            trades.get(4).add(new SellTradeOffer(Items.CANDLE, 4, 2, 4));

            trades.get(5).add(new SellTradeOffer(Items.EXPERIENCE_BOTTLE, 3, 1, 5));
            trades.get(5).add(new BuyTradeOffer(Items.POISONOUS_POTATO, 1, 12, 10));

        } else if (event.getType() == ModProfessions.ALCHEMIST.get()) {
            trades.get(1).add(new SellTradeOffer(Items.SUGAR, 3, 1, 1));
            trades.get(1).add(new SellTradeOffer(Items.BROWN_MUSHROOM, 3, 1, 1));
            trades.get(1).add(new SellTradeOffer(Items.RED_MUSHROOM, 3, 1, 1));
            trades.get(1).add(new SellTradeOffer(Items.NETHER_WART, 5, 1, 2));
            trades.get(1).add(new BuyTradeOffer(Items.GLASS_BOTTLE, 3, 12, 2));

            trades.get(2).add(new SellTradeOffer(Items.MAGMA_CREAM, 4, 1, 3));
            trades.get(2).add(new SellTradeOffer(Items.PUFFERFISH, 5, 1, 3));
            trades.get(2).add(new SellTradeOffer(Items.FERMENTED_SPIDER_EYE, 5, 1, 3));
            trades.get(2).add(new SellPotionTradeOffer(Items.POTION, Items.POTION, 5, 4, 5));

            trades.get(3).add(new SellTradeOffer(Items.BLAZE_POWDER, 5, 1, 4));
            trades.get(3).add(new SellTradeOffer(Items.GHAST_TEAR, 5, 1, 4));
            trades.get(3).add(new SellTradeOffer(Items.GUNPOWDER, 3, 1, 4));
            trades.get(3).add(new SellTradeOffer(Items.PHANTOM_MEMBRANE, 5, 1, 4));
            trades.get(3).add(new BuyTradeOffer(Items.BLAZE_ROD, 1, 12, 5));
            trades.get(3).add(new BuyTradeOffer(Items.ENDER_PEARL, 1, 12, 5));

            trades.get(4).add(new SellPotionTradeOffer(Items.POTION, Items.SPLASH_POTION, 8, 4, 6));

            trades.get(5).add(new SellTradeOffer(Items.DRAGON_BREATH, 8, 1, 7));
            trades.get(5).add(new SellTradeOffer(Items.GLISTERING_MELON_SLICE, 4, 1, 6));
            trades.get(5).add(new SellTradeOffer(Items.RABBIT_FOOT, 4, 1, 6));

        } else if (event.getType() == ModProfessions.OCEANOGRAPHER.get()) {
            trades.get(1).add(new SellTradeOffer(Items.SEA_PICKLE, 3, 1, 1));
            trades.get(1).add(new SellTradeOffer(Items.WATER_BUCKET, 5, 1, 5));
            trades.get(1).add(new BuyTradeOffer(Items.SEAGRASS, 4, 12, 1));
            trades.get(1).add(new BuyTradeOffer(Items.KELP, 6, 12, 1));

            trades.get(2).add(new SellTradeOffer(Items.BRAIN_CORAL, 3, 1, 3));
            trades.get(2).add(new SellTradeOffer(Items.BUBBLE_CORAL, 3, 1, 3));
            trades.get(2).add(new SellTradeOffer(Items.FIRE_CORAL, 3, 1, 3));
            trades.get(2).add(new SellTradeOffer(Items.HORN_CORAL, 3, 1, 3));
            trades.get(2).add(new SellTradeOffer(Items.TUBE_CORAL, 3, 1, 3));
            trades.get(2).add(new ItemsAndEmeraldsTradeOffer(Items.DEAD_TUBE_CORAL, 1, Items.TUBE_CORAL, 1, 8, 3));
            trades.get(2).add(new ItemsAndEmeraldsTradeOffer(Items.DEAD_BRAIN_CORAL, 1, Items.BRAIN_CORAL, 1, 8, 3));
            trades.get(2).add(new ItemsAndEmeraldsTradeOffer(Items.DEAD_BUBBLE_CORAL, 1, Items.BUBBLE_CORAL, 1, 8, 3));
            trades.get(2).add(new ItemsAndEmeraldsTradeOffer(Items.DEAD_HORN_CORAL, 1, Items.HORN_CORAL, 1, 8, 3));
            trades.get(2).add(new ItemsAndEmeraldsTradeOffer(Items.DEAD_FIRE_CORAL, 1, Items.FIRE_CORAL, 1, 8, 3));

            trades.get(3).add(new SellTradeOffer(Items.BRAIN_CORAL_FAN, 3, 1, 4));
            trades.get(3).add(new SellTradeOffer(Items.BUBBLE_CORAL_FAN, 3, 1, 4));
            trades.get(3).add(new SellTradeOffer(Items.FIRE_CORAL_FAN, 3, 1, 4));
            trades.get(3).add(new SellTradeOffer(Items.HORN_CORAL_FAN, 3, 1, 4));
            trades.get(3).add(new SellTradeOffer(Items.TUBE_CORAL_FAN, 3, 1, 4));
            trades.get(3).add(new ItemsAndEmeraldsTradeOffer(Items.DEAD_TUBE_CORAL_FAN, 1, Items.TUBE_CORAL_FAN, 1, 8, 5));
            trades.get(3).add(new ItemsAndEmeraldsTradeOffer(Items.DEAD_BRAIN_CORAL_FAN, 1, Items.BRAIN_CORAL_FAN, 1, 8, 5));
            trades.get(3).add(new ItemsAndEmeraldsTradeOffer(Items.DEAD_BUBBLE_CORAL_FAN, 1, Items.BUBBLE_CORAL_FAN, 1, 8, 5));
            trades.get(3).add(new ItemsAndEmeraldsTradeOffer(Items.DEAD_HORN_CORAL_FAN, 1, Items.HORN_CORAL_FAN, 1, 8, 5));
            trades.get(3).add(new ItemsAndEmeraldsTradeOffer(Items.DEAD_FIRE_CORAL_FAN, 1, Items.FIRE_CORAL_FAN, 1, 8, 5));

            trades.get(4).add(new SellTradeOffer(Items.BRAIN_CORAL_BLOCK, 5, 1, 5));
            trades.get(4).add(new SellTradeOffer(Items.BUBBLE_CORAL_BLOCK, 5, 1, 5));
            trades.get(4).add(new SellTradeOffer(Items.FIRE_CORAL_BLOCK, 5, 1, 5));
            trades.get(4).add(new SellTradeOffer(Items.HORN_CORAL_BLOCK, 5, 1, 5));
            trades.get(4).add(new SellTradeOffer(Items.TUBE_CORAL_BLOCK, 5, 1, 5));
            trades.get(4).add(new ItemsAndEmeraldsTradeOffer(Items.DEAD_TUBE_CORAL_BLOCK, 1, Items.TUBE_CORAL_BLOCK, 1, 8, 5));
            trades.get(4).add(new ItemsAndEmeraldsTradeOffer(Items.DEAD_BRAIN_CORAL_BLOCK, 1, Items.BRAIN_CORAL_BLOCK, 1, 8, 5));
            trades.get(4).add(new ItemsAndEmeraldsTradeOffer(Items.DEAD_BUBBLE_CORAL_BLOCK, 1, Items.BUBBLE_CORAL_BLOCK, 1, 8, 5));
            trades.get(4).add(new ItemsAndEmeraldsTradeOffer(Items.DEAD_HORN_CORAL_BLOCK, 1, Items.HORN_CORAL_BLOCK, 1, 8, 5));
            trades.get(4).add(new ItemsAndEmeraldsTradeOffer(Items.DEAD_FIRE_CORAL_BLOCK, 1, Items.FIRE_CORAL_BLOCK, 1, 8, 5));

            trades.get(5).add(new SellTradeOffer(Items.NAUTILUS_SHELL, 6, 1, 15));
            trades.get(5).add(new SellTradeOffer(Items.SPONGE, 8, 1, 10));
            trades.get(5).add(new BuyTradeOffer(Items.SCUTE, 2, 8, 8));
            trades.get(5).add(new BuyTradeOffer(Items.TROPICAL_FISH_BUCKET, 1, 8, 4));
            trades.get(5).add(new BuyTradeOffer(Items.AXOLOTL_BUCKET, 1, 8, 4));
            trades.get(5).add(new BuyTradeOffer(Items.PUFFERFISH_BUCKET, 1, 8, 4));
        }
    }
}
