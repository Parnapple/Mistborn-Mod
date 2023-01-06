package Parnapple.mistbornmod.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class LightnessEffect extends MobEffect {
    public LightnessEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int pAmplifier) {
        entity.addEffect(new MobEffectInstance(MobEffects.JUMP, 1,pAmplifier, true, false, false));
        entity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 1,pAmplifier, true, false, false));
        //rest of functionality in ModEvents class
    }


    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }



}
