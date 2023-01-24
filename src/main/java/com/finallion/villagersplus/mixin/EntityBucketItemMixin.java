package com.finallion.villagersplus.mixin;

import com.finallion.villagersplus.util.DuckBucketable;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.level.material.Fluid;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.function.Supplier;

@Mixin(MobBucketItem.class)
public class EntityBucketItemMixin extends BucketItem implements DuckBucketable {
    @Final
    @Shadow
    private Supplier<? extends EntityType<?>> entityTypeSupplier;

    public EntityBucketItemMixin(Fluid p_40689_, Properties p_40690_) {
        super(p_40689_, p_40690_);
    }

    public EntityBucketItemMixin(Supplier<? extends Fluid> supplier, Properties builder) {
        super(supplier, builder);
    }

    @Override
    public EntityType<?> getEntityType() {
        return entityTypeSupplier.get();
    }
}
