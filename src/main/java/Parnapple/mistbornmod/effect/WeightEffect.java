package Parnapple.mistbornmod.effect;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class WeightEffect extends MobEffect {
    public WeightEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int pAmplifier) {
        int x = entity.getBlockX();
        int y = entity.getBlockY() -1;
        int z = entity.getBlockZ();

        Level level = entity.level;

        if(!level.isClientSide()) {
            BlockPos pos = new BlockPos(x, y, z);

            boolean canDestroy = level.getBlockState(pos).getBlock().getExplosionResistance() < pAmplifier + 1;

            if(canDestroy) {
                canDestroy = false;
                for(int i = 0; i <= pAmplifier; i++) {
                    BlockPos pos2 = new BlockPos(x, y-(i+1), z);

                    if(level.getBlockState(pos2).getBlock() == Blocks.AIR) {
                        canDestroy = true;
                    }
                }

            }


            if(canDestroy) {
                level.destroyBlock(pos, true);
            }


        }


        //rest of functionality in ModEvents class
    }


    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }



}
