package Parnapple.mistbornmod.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class PewterStrengthEffect extends MobEffect {
    public PewterStrengthEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int pAmplifier) {
        entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1,pAmplifier, true, false, false));
        entity.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 1,pAmplifier, true, false, false));
        entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1,pAmplifier, true, false, false));

        if (entity.getHealth() < entity.getMaxHealth()) {
            entity.heal((pAmplifier + 1)/2);
        }

    }


    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {

        return true;
    }



}
