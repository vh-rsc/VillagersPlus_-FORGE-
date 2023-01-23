package com.finallion.villagersplus.init;

import com.finallion.villagersplus.VillagersPlus;
import com.finallion.villagersplus.blocks.AlchemistTableBlock;
import com.finallion.villagersplus.blocks.HorticulturistTableBlock;
import com.finallion.villagersplus.blocks.OccultistTableBlock;
import com.finallion.villagersplus.blocks.OceanographerTableBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, VillagersPlus.MOD_ID);

    public static final RegistryObject<Item> ALCHEMIST_TABLE_BLOCK = ITEMS.register("alchemist_table", () -> new BlockItem(ModBlocks.ALCHEMIST_TABLE_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> OCEANOGRAPHER_TABLE_BLOCK = ITEMS.register("oceanographer_table", () -> new BlockItem(ModBlocks.OCEANOGRAPHER_TABLE_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> OAK_HORTICULTURIST_TABLE_BLOCK = ITEMS.register("oak_horticulturist_table", () -> new BlockItem(ModBlocks.OAK_HORTICULTURIST_TABLE_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> DARK_OAK_HORTICULTURIST_TABLE_BLOCK = ITEMS.register("dark_oak_horticulturist_table", () -> new BlockItem(ModBlocks.DARK_OAK_HORTICULTURIST_TABLE_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> ACACIA_HORTICULTURIST_TABLE_BLOCK = ITEMS.register("acacia_horticulturist_table", () -> new BlockItem(ModBlocks.ACACIA_HORTICULTURIST_TABLE_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> JUNGLE_HORTICULTURIST_TABLE_BLOCK = ITEMS.register("jungle_horticulturist_table", () -> new BlockItem(ModBlocks.JUNGLE_HORTICULTURIST_TABLE_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> SPRUCE_HORTICULTURIST_TABLE_BLOCK = ITEMS.register("spruce_horticulturist_table", () -> new BlockItem(ModBlocks.SPRUCE_HORTICULTURIST_TABLE_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> BIRCH_HORTICULTURIST_TABLE_BLOCK = ITEMS.register("birch_horticulturist_table", () -> new BlockItem(ModBlocks.BIRCH_HORTICULTURIST_TABLE_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> MANGROVE_HORTICULTURIST_TABLE_BLOCK = ITEMS.register("mangrove_horticulturist_table", () -> new BlockItem(ModBlocks.MANGROVE_HORTICULTURIST_TABLE_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> CRIMSON_HORTICULTURIST_TABLE_BLOCK = ITEMS.register("crimson_horticulturist_table", () -> new BlockItem(ModBlocks.CRIMSON_HORTICULTURIST_TABLE_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> WARPED_HORTICULTURIST_TABLE_BLOCK = ITEMS.register("warped_horticulturist_table", () -> new BlockItem(ModBlocks.WARPED_HORTICULTURIST_TABLE_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> OCCULTIST_TABLE_BLOCK = ITEMS.register("occultist_table", () -> new BlockItem(ModBlocks.OCCULTIST_TABLE_BLOCK.get(), new Item.Properties()));

}
