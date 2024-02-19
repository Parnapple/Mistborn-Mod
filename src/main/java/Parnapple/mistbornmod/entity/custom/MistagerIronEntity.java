package Parnapple.mistbornmod.entity.custom;

import Parnapple.mistbornmod.entity.CoinProjectileEntity;
import Parnapple.mistbornmod.item.ModItems;
import Parnapple.mistbornmod.item.custom.MetalBeadItem;
import Parnapple.mistbornmod.util.Metal;
import Parnapple.mistbornmod.util.MetalUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class MistagerIronEntity extends Mistager {


    public MistagerIronEntity(EntityType<? extends AbstractIllager> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    public Metal type() {
        return Metal.IRON;
    }

    public static AttributeSupplier setAttributes() {
        return AbstractIllager.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.20F)
                .add(Attributes.FOLLOW_RANGE, 12.0D)
                .add(Attributes.MAX_HEALTH, 24.0D)
                .add(Attributes.ATTACK_DAMAGE, 2.0D)
                .add(Attributes.ATTACK_SPEED, 0.5)
                .build();
    }

    protected void registerGoals() {
            super.registerGoals();
            this.goalSelector.addGoal(0, new FloatGoal(this));
            this.goalSelector.addGoal(4, new EatMetalBeadGoal(this,(MetalBeadItem) ModItems.IRON_BEAD.get()));
            this.goalSelector.addGoal(2, new BurnMetalGoal(this));
            this.goalSelector.addGoal(1, new PullFlyingCoinsGoal(this));
            this.goalSelector.addGoal(5, new PullItemsFromTargetGoal(this));
            this.goalSelector.addGoal(5, new PullTargetGoal(this));
            this.goalSelector.addGoal(2, new RaiderOpenDoorGoal(this));
            this.goalSelector.addGoal(3, new HoldGroundAttackGoal(this, 10.0F));
            this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.3D, false));
            this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, Raider.class)).setAlertOthers());
            this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
            this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, true));
            this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
            this.goalSelector.addGoal(8, new RandomStrollGoal(this, 0.4D));
            this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
            this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F));
    }

    public IllagerArmPose getArmPose() {
        if (this.isAggressive() && !getOffhandItem().getItem().equals(ModItems.IRON_BEAD.get())) {
            return IllagerArmPose.ATTACKING;
        } else {
            return this.isCelebrating() ? IllagerArmPose.CELEBRATING : IllagerArmPose.NEUTRAL;
        }
    }

    @Override
    public SoundEvent getCelebrateSound() {
        return SoundEvents.VINDICATOR_CELEBRATE;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.VINDICATOR_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.VINDICATOR_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.VINDICATOR_AMBIENT;
    }

    protected void populateDefaultEquipmentSlots(DifficultyInstance pDifficulty) {
            if(random.nextInt(4) == 1) {
                if(random.nextInt(2) == 1) {
                    this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(ModItems.OBSIDIAN_DAGGER.get()));
                } else {
                    this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(ModItems.GLASS_DAGGER.get()));
                }
            }
    }

    @Override
    public void applyRaidBuffs(int pWave, boolean pUnusedFalse) {
        super.applyRaidBuffs(pWave, pUnusedFalse);
        this.isFlaring = true;
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        this.populateDefaultEquipmentSlots(pDifficulty);
        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }

    protected static class BurnMetalGoal extends Goal {
        private final Mistager mob;

        public BurnMetalGoal(Mistager pMob) {
            this.mob = pMob;
        }

        public boolean canUse() {
            return mob.metalStores > 0 && !mob.getOffhandItem().getItem().equals(ModItems.IRON_BEAD.get());
        }

        public void start() {
            super.start();
            mob.setBurning(true);
            if(this.mob.hasActiveRaid()) {
                mob.isFlaring = true;
            }
        }

        public boolean requiresUpdateEveryTick() {
            return false;
        }



    }

    protected static class PullFlyingCoinsGoal extends Goal {
        private final Mistager mob;

        public PullFlyingCoinsGoal(Mistager pMob) {
            this.mob = pMob;
        }

        public boolean canUse() {
            return mob.isBurning;
        }

        public boolean canContinueToUse() {
            return mob.isBurning;
        }

        public void start() {
            super.start();
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        @Override
        public void tick() {
            super.tick();
            int range = mob.isFlaring ? 18: 12;

            for(CoinProjectileEntity coin: mob.level.getEntitiesOfClass(CoinProjectileEntity.class, mob.getBoundingBox().inflate(range))) {
                Vec3 move = Vec3.atCenterOf(mob.blockPosition().offset(0, -.1, 0)).subtract(Vec3.atCenterOf(coin.blockPosition())).multiply(.35, .35, .35);
                coin.setDeltaMovement(move);
            }
        }
    }

    protected static class PullItemsFromTargetGoal extends Goal {
        private final Mistager mob;

        public PullItemsFromTargetGoal(Mistager pMob) {
            this.mob = pMob;
        }

        public boolean canUse() {
            return mob.isBurning && mob.getTarget() != null;
        }

        public boolean canContinueToUse() {
            return mob.isBurning;
        }

        public void start() {
            super.start();
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        @Override
        public void tick() {
            super.tick();
            int range = mob.isFlaring ? 9: 6;

            LivingEntity target = mob.getTarget();

            if(target != null && mob.distanceTo(target) <= range) {
                ItemStack item = target.getItemInHand(InteractionHand.MAIN_HAND);
                if(MetalUtil.isItemMetal(item.getItem()) && !target.equals(mob)) {
                    ItemStack stack = item.copy();
                    stack.setCount(1);
                    ItemEntity itemEntity = new ItemEntity(mob.level, target.getX(), target.getY(), target.getZ(), stack);
                    itemEntity.setPickUpDelay(40);
                    item.shrink(1);
                    Vec3 move = Vec3.atCenterOf(mob.blockPosition()).subtract(Vec3.atCenterOf(target.blockPosition())).multiply(0.2, 0.2, 0.2);
                    itemEntity.setDeltaMovement(move);
                    mob.level.addFreshEntity(itemEntity);
                }
                item = target.getItemInHand(InteractionHand.OFF_HAND);
                if(MetalUtil.isItemMetal(item.getItem()) && !target.equals(mob)) {
                    ItemStack stack = item.copy();
                    stack.setCount(1);
                    ItemEntity itemEntity = new ItemEntity(mob.level, target.getX(), target.getY(), target.getZ(), stack);
                    itemEntity.setPickUpDelay(40);
                    item.shrink(1);
                    Vec3 move = Vec3.atCenterOf(mob.blockPosition()).subtract(Vec3.atCenterOf(target.blockPosition())).multiply(0.2, 0.2, 0.2);
                    itemEntity.setDeltaMovement(move);
                    mob.level.addFreshEntity(itemEntity);
                }
            }

        }
    }

    protected static class PullTargetGoal extends Goal {
        private final Mistager mob;

        public PullTargetGoal(Mistager pMob) {
            this.mob = pMob;
        }

        public boolean canUse() {
            return mob.isBurning && mob.getTarget() != null;
        }

        public boolean canContinueToUse() {
            return mob.isBurning;
        }

        public void start() {
            super.start();
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        public void tick() {
            if(mob.isBurning && mob.getTarget() != null) {
                int range = mob.isFlaring ? 18: 12;

                LivingEntity target = mob.getTarget();

                if(mob.distanceTo(target) <= range && mob.distanceTo(target) >= 3) {
                    if(MetalUtil.isEntityMetal(target) && !target.equals(mob)) {
                        Vec3 move = Vec3.atCenterOf(mob.blockPosition()).subtract(Vec3.atCenterOf(target.blockPosition())).multiply(.25, .25, .25);
                        target.setDeltaMovement(move);
                        if(target instanceof Player) {
                            target.hurtMarked = true;
                            target.move(MoverType.PLAYER, target.getDeltaMovement());
                        }

                    }
                }

            }
        }

    }


}
