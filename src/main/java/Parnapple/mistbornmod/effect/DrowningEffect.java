package Parnapple.mistbornmod.effect;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class DrowningEffect extends MobEffect {
    public DrowningEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int pAmplifier) {
        entity.setAirSupply(entity.getAirSupply()-((pAmplifier+2)*2));
        if (entity.getAirSupply() <= -20) {
            entity.setAirSupply(0);
            entity.hurt(DamageSource.DROWN, 1.0F);
        }

    }


    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {

        return true;
    }



}
