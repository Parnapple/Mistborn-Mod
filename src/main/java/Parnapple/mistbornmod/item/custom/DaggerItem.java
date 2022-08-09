package Parnapple.mistbornmod.item.custom;

import Parnapple.mistbornmod.effect.ModEffects;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class DaggerItem extends SwordItem {
    public DaggerItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
        if(entity.isOnGround()) {
            entity.playSound(SoundEvents.GLASS_BREAK, 100f, 10f);
            stack.shrink(1);
        }
        return super.onEntityItemUpdate(stack, entity);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        pTarget.addEffect(new MobEffectInstance(ModEffects.WOUNDED.get(), 150));

        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }
}
