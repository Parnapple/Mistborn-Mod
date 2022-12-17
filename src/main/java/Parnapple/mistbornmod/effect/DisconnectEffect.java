package Parnapple.mistbornmod.effect;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;

public class DisconnectEffect extends MobEffect {
    public DisconnectEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {

        AABB boundingBox = pLivingEntity.getBoundingBox().inflate(pAmplifier + 4);

        for(LivingEntity target: pLivingEntity.getLevel().getEntitiesOfClass(LivingEntity.class, boundingBox)) {
            target.setLastHurtByMob(pLivingEntity);
        }

        super.applyEffectTick(pLivingEntity, pAmplifier);
    }



    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}
