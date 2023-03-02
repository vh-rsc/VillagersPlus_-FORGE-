package com.finallion.villagersplus.villagers;

import com.finallion.villagersplus.VillagersPlus;
import com.finallion.villagersplus.init.ModBlocks;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ModPointOfInterestType {
    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, VillagersPlus.MOD_ID);

    public static RegistryObject<PoiType> HORTICULTURIST_POI = POI_TYPES.register("horticulturist", () -> new PoiType("horticulturist", ImmutableSet.copyOf(Stream.of(
            ModBlocks.WARPED_HORTICULTURIST_TABLE_BLOCK.get().getStateDefinition().getPossibleStates(),
            ModBlocks.CRIMSON_HORTICULTURIST_TABLE_BLOCK.get().getStateDefinition().getPossibleStates(),
            ModBlocks.OAK_HORTICULTURIST_TABLE_BLOCK.get().getStateDefinition().getPossibleStates(),
            ModBlocks.DARK_OAK_HORTICULTURIST_TABLE_BLOCK.get().getStateDefinition().getPossibleStates(),
            ModBlocks.BIRCH_HORTICULTURIST_TABLE_BLOCK.get().getStateDefinition().getPossibleStates(),
            ModBlocks.JUNGLE_HORTICULTURIST_TABLE_BLOCK.get().getStateDefinition().getPossibleStates(),
            ModBlocks.ACACIA_HORTICULTURIST_TABLE_BLOCK.get().getStateDefinition().getPossibleStates(),
            ModBlocks.SPRUCE_HORTICULTURIST_TABLE_BLOCK.get().getStateDefinition().getPossibleStates()
            //ModBlocks.MANGROVE_HORTICULTURIST_TABLE_BLOCK.get().getStateDefinition().getPossibleStates()
    ).flatMap(Collection::stream).collect(Collectors.toList())), 1, 1));
    public static RegistryObject<PoiType> OCCULTIST_POI = POI_TYPES.register("occultist", () -> new PoiType("occultist", ImmutableSet.copyOf(ModBlocks.OCCULTIST_TABLE_BLOCK.get().getStateDefinition().getPossibleStates()), 1, 1));
    public static RegistryObject<PoiType> OCEANOGRAPHER_POI = POI_TYPES.register("oceanographer", () -> new PoiType("oceanographer", ImmutableSet.copyOf(ModBlocks.OCEANOGRAPHER_TABLE_BLOCK.get().getStateDefinition().getPossibleStates()), 1, 1));
    public static RegistryObject<PoiType> ALCHEMIST_POI = POI_TYPES.register("alchemist", () -> new PoiType("alchemist", ImmutableSet.copyOf(ModBlocks.ALCHEMIST_TABLE_BLOCK.get().getStateDefinition().getPossibleStates()), 1, 1));


}
