package com.finallion.villagersplus.particles;

import com.finallion.villagersplus.init.ModBlocks;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;


public class BubbleParticle extends TextureSheetParticle {

    BubbleParticle(ClientLevel clientWorld, double d, double e, double f, double g, double h, double i) {
        super(clientWorld, d, e, f);
        this.gravity = -0.125F;
        this.friction = 0.85F;
        this.setSize(0.02F, 0.02F);
        this.quadSize *= this.random.nextFloat() * 0.6F;
        this.xd = g * 0.20000000298023224D + (Math.random() * 2.0D - 1.0D) * 0.019999999552965164D;
        this.yd = h * 0.00500000298023224D;
        this.zd = i * 0.20000000298023224D + (Math.random() * 2.0D - 1.0D) * 0.019999999552965164D;
        this.lifetime = (int)(8.0D / (Math.random() * 0.8D + 0.2D));
    }


    public void tick() {
        super.tick();
        if (!this.removed && y - yo > 0.0325) {
            this.remove();
        }

        if (!this.removed && !this.level.getBlockState(new BlockPos(this.x, this.y, this.z)).is(ModBlocks.OCEANOGRAPHER_TABLE_BLOCK.get())) {
            this.remove();
        }

    }

    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public Provider(SpriteSet p_105753_) {
            this.sprite = p_105753_;
        }

        public Particle createParticle(SimpleParticleType p_105764_, ClientLevel p_105765_, double p_105766_, double p_105767_, double p_105768_, double p_105769_, double p_105770_, double p_105771_) {
            BubbleParticle bubblecolumnupparticle = new BubbleParticle(p_105765_, p_105766_, p_105767_, p_105768_, p_105769_, p_105770_, p_105771_);
            bubblecolumnupparticle.pickSprite(this.sprite);
            return bubblecolumnupparticle;
        }
    }
}
