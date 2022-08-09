package Parnapple.mistbornmod.effect;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

import java.util.HashMap;
import java.util.Map;

public class WoundedEffect extends MobEffect {
    public WoundedEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    public static final DamageSource WOUNDS = (new DamageSource("wounds")).bypassArmor();

    //private static Map<LivingEntity, Float> woundedMap = new HashMap();


    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if(!pLivingEntity.level.isClientSide()) {
            if(pLivingEntity.walkDist != 0) {
                    pLivingEntity.hurt(WOUNDS, pAmplifier + 1);
                    pLivingEntity.walkDist = 0;
                }
        }

        super.applyEffectTick(pLivingEntity, pAmplifier);
    }



    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}
