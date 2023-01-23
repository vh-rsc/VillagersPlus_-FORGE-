package com.finallion.villagersplus.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.ParticleGroup;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;

import java.util.Optional;

public class ExperienceParticle extends TextureSheetParticle {

    public ExperienceParticle(ClientLevel world, SpriteSet spriteProvider, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
        super(world, x, y - 0.125D, z, velocityX, velocityY, velocityZ);
        this.setSize(0.01F, 0.01F);
        this.pickSprite(spriteProvider);
        this.quadSize *= this.random.nextFloat() * 0.6F + 0.2F;
        this.lifetime = (int)(16.0D / (Math.random() * 0.8D + 0.2D));
        this.hasPhysics = false;
        this.friction = 1.0F;
        this.gravity = 0.0F;
    }

    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    public static class ExperienceParticleFactory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public ExperienceParticleFactory(SpriteSet p_172419_) {
            this.sprite = p_172419_;
        }

        public Particle createParticle(SimpleParticleType p_172430_, ClientLevel p_172431_, double p_172432_, double p_172433_, double p_172434_, double p_172435_, double p_172436_, double p_172437_) {
            ExperienceParticle suspendedparticle = new ExperienceParticle(p_172431_, this.sprite, p_172432_, p_172433_, p_172434_, 0.0D, (double)-0.8F, 0.0D);
            suspendedparticle.lifetime = Mth.randomBetweenInclusive(p_172431_.random, 500, 1000);
            suspendedparticle.gravity = 0.01F;
            suspendedparticle.setColor(0.655F, 0.8F, 0.1F);
            return suspendedparticle;
        }
    }
}

