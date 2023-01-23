package com.finallion.villagersplus.client;

import com.finallion.villagersplus.client.renderer.HorticulturistTableBlockEntityRenderer;
import com.finallion.villagersplus.client.renderer.OceanographerTableBlockEntityRenderer;
import com.finallion.villagersplus.client.screen.AlchemistTableScreen;
import com.finallion.villagersplus.init.ModBlocks;
import com.finallion.villagersplus.init.ModParticles;
import com.finallion.villagersplus.init.ModScreen;
import com.finallion.villagersplus.particles.BubbleParticle;
import com.finallion.villagersplus.particles.ExperienceParticle;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class VillagersPlusClient {

    public VillagersPlusClient() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::initParticles);
        modEventBus.addListener(this::initBlockEntityRenderers);
    }

    @SubscribeEvent
    public void initParticles(RegisterParticleProvidersEvent event) {
        event.register(ModParticles.EXPERIENCE_PARTICLE.get(), ExperienceParticle.ExperienceParticleFactory::new);
        event.register(ModParticles.BUBBLE_PARTICLE.get(), BubbleParticle.Provider::new);
    }

    @SubscribeEvent
    public void initBlockEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlocks.HORTICULTURIST_TABLE_BLOCK_ENTITY.get(), HorticulturistTableBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlocks.OCEANOGRAPHER_TABLE_BLOCK_ENTITY.get(), OceanographerTableBlockEntityRenderer::new);
    }

    //HandledScreens.register(ModScreen.ALCHEMIST_TABLE_SCREEN_HANDLER, AlchemistTableScreen::new);


}
