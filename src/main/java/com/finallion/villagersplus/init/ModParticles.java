package com.finallion.villagersplus.init;

import com.finallion.villagersplus.VillagersPlus;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, VillagersPlus.MOD_ID);


    public static final RegistryObject<SimpleParticleType> EXPERIENCE_PARTICLE = PARTICLES.register("villagersplus:experience_particle", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> BUBBLE_PARTICLE = PARTICLES.register("villagersplus:bubble_particle", () -> new SimpleParticleType(true));

}
