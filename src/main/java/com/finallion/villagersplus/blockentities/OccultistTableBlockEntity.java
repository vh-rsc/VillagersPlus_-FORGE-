package com.finallion.villagersplus.blockentities;

import com.finallion.villagersplus.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class OccultistTableBlockEntity extends BlockEntity {
    private int levels = 0;
    private final int MAX_EXP_STORAGE = 500;
    private final int AMOUNT = 50;

    public OccultistTableBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlocks.OCCULTIST_TABLE_BLOCK_ENTITY.get(), pos, state);
    }

    public int getLevels() {
        return levels;
    }

    public void interact(Level world, Player player) {
        if (player.isCrouching()) {
            if (levels <= MAX_EXP_STORAGE - AMOUNT) {
                if (player.totalExperience < AMOUNT) {
                    if (!world.isClientSide()) {
                        this.levels += player.totalExperience;
                        player.giveExperiencePoints(-(player.totalExperience));
                    }
                } else {
                    if (!world.isClientSide()) {
                        player.giveExperiencePoints(-AMOUNT);
                        this.levels += AMOUNT;
                    }
                }
            }
        } else {
            if (levels > 0) {
                if (levels >= AMOUNT) {
                    if (!world.isClientSide()) {
                        player.giveExperiencePoints(AMOUNT);
                        this.levels -= AMOUNT;
                    }
                } else {
                    if (!world.isClientSide()) {
                        player.giveExperiencePoints(levels);
                        this.levels = 0;
                    }

                }
            }
        }
    }



    public void load(CompoundTag nbt) {
        super.load(nbt);
        this.levels = nbt.getInt("Levels");
    }



    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        nbt.putInt("Levels", this.levels);
    }

}
