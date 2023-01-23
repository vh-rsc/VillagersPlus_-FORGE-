package com.finallion.villagersplus.villagers;

import com.finallion.villagersplus.VillagersPlus;
import com.google.common.collect.ImmutableSet;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModProfessions {

    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS = DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, VillagersPlus.MOD_ID);

    public static RegistryObject<VillagerProfession> HORTICULTURIST = VILLAGER_PROFESSIONS.register("horticulturist", () ->
                new VillagerProfession("horticulturist", holder -> holder.value().equals(ModPointOfInterestType.HORTICULTURIST_POI.get()), holder -> holder.value().equals(ModPointOfInterestType.HORTICULTURIST_POI.get()), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_FARMER));
    public static RegistryObject<VillagerProfession> OCCULTIST = VILLAGER_PROFESSIONS.register("occultist", () ->
                new VillagerProfession("occultist", holder -> holder.value().equals(ModPointOfInterestType.OCCULTIST_POI.get()), holder -> holder.value().equals(ModPointOfInterestType.OCCULTIST_POI.get()), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_CLERIC));
    public static RegistryObject<VillagerProfession> OCEANOGRAPHER = VILLAGER_PROFESSIONS.register("oceanographer", () ->
                new VillagerProfession("oceanographer", holder -> holder.value().equals(ModPointOfInterestType.OCEANOGRAPHER_POI.get()), holder -> holder.value().equals(ModPointOfInterestType.OCEANOGRAPHER_POI.get()), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.BUCKET_FILL));
    public static RegistryObject<VillagerProfession> ALCHEMIST = VILLAGER_PROFESSIONS.register("alchemist", () ->
                new VillagerProfession("alchemist", holder -> holder.value().equals(ModPointOfInterestType.ALCHEMIST_POI.get()), holder -> holder.value().equals(ModPointOfInterestType.ALCHEMIST_POI.get()), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_CLERIC));



}
