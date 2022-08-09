package Parnapple.mistbornmod.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class ColdEffect extends MobEffect {
    public ColdEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int pAmplifier) {
        entity.setIsInPowderSnow(true);
        entity.setTicksFrozen(entity.getTicksFrozen() + pAmplifier);
    }


    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {

        return true;
    }



}
