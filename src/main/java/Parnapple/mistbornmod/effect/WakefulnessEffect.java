package Parnapple.mistbornmod.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;

public class WakefulnessEffect extends MobEffect {
    public WakefulnessEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        for(MobEffectInstance effect: pLivingEntity.getActiveEffects()) {
            if(effect.getEffect().getCategory().equals(MobEffectCategory.HARMFUL) && effect.getAmplifier() <= pAmplifier) {
                pLivingEntity.removeEffect(effect.getEffect());
            }
        }

        super.applyEffectTick(pLivingEntity, pAmplifier);
    }



    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}
