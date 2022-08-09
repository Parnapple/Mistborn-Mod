package Parnapple.mistbornmod.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class PewterStoringEffect extends MobEffect {
    public PewterStoringEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int pAmplifier) {
        entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 1,pAmplifier));
        entity.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 1,pAmplifier));
        entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 1,pAmplifier));

    }


    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {

        return true;
    }



}
