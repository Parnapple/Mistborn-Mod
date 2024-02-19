package Parnapple.mistbornmod.entity;

import Parnapple.mistbornmod.entity.custom.MistagerSteelEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class CoinProjectileEntity extends AbstractArrow implements ItemSupplier {

    public static DamageSource allomancy(CoinProjectileEntity coinProjectile, @Nullable Entity pIndirectEntity) {
        return (new IndirectEntityDamageSource("allomancy", coinProjectile, pIndirectEntity)).setProjectile();
    }

    ItemStack nuggetItem = ItemStack.EMPTY;

    public CoinProjectileEntity(EntityType<? extends CoinProjectileEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public CoinProjectileEntity(Level pLevel, LivingEntity pShooter, ItemStack pStack) {
        super(ModEntities.COIN_PROJECTILE.get(), pShooter, pLevel);
        this.nuggetItem = pStack;
    }

    @Override
    protected ItemStack getPickupItem() {
        return this.nuggetItem.copy();
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        Entity entity = pResult.getEntity();
        float damage = Mth.randomBetweenInclusive(this.random, 5, 10);

        Entity ownerEntity = this.getOwner();
        if(ownerEntity instanceof MistagerSteelEntity) {
            damage = 5;
        }
        DamageSource damagesource = allomancy(this, ownerEntity == null ? this : ownerEntity);
//        DamageSource damagesource = DamageSource.trident(this, ownerEntity == null ? this : ownerEntity);
        SoundEvent soundevent = SoundEvents.CHAIN_BREAK;

        if (entity.hurt(damagesource, damage)) {
            if (entity.getType() == EntityType.ENDERMAN) {
                return;
            }

            if (entity instanceof LivingEntity livingentity1) {
                if (ownerEntity instanceof LivingEntity) {
                    EnchantmentHelper.doPostHurtEffects(livingentity1, ownerEntity);
                    EnchantmentHelper.doPostDamageEffects((LivingEntity)ownerEntity, livingentity1);
                }

                this.doPostHurtEffects(livingentity1);
            }
        }

        this.setDeltaMovement(this.getDeltaMovement().multiply(0.5, 0.5, 0.5));
        float f1 = 1.0F;


        this.playSound(soundevent, f1, 1.0F);

    }

    public boolean shouldRender(double pX, double pY, double pZ) {
        return true;
    }

    @Override
    public ItemStack getItem() {
        return this.getPickupItem().equals(ItemStack.EMPTY) ? new ItemStack(Items.IRON_NUGGET) : this.getPickupItem();
//        return this.nuggetItem.equals(ItemStack.EMPTY) ? new ItemStack(Items.IRON_NUGGET) : this.nuggetItem;
//        return this.nuggetItem.copy();
//        return new ItemStack(this.nuggetItem.getItem());
    }

    @Override
    protected SoundEvent getDefaultHitGroundSoundEvent() {
        return SoundEvents.CHAIN_FALL;
    }
}
