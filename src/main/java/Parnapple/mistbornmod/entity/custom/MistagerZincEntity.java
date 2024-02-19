package Parnapple.mistbornmod.entity.custom;

import Parnapple.mistbornmod.capability.ModCapabilities;
import Parnapple.mistbornmod.entity.ai.GenericMeleeAttackGoal;
import Parnapple.mistbornmod.item.ModItems;
import Parnapple.mistbornmod.item.custom.MetalBeadItem;
import Parnapple.mistbornmod.util.Metal;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.animal.horse.Llama;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.level.Level;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class MistagerZincEntity extends Mistager {


    public MistagerZincEntity(EntityType<? extends AbstractIllager> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    public Metal type() {
        return Metal.ZINC;
    }

    public static AttributeSupplier setAttributes() {
        return AbstractIllager.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.45F)
                .add(Attributes.FOLLOW_RANGE, 12.0D)
                .add(Attributes.MAX_HEALTH, 24.0D)
                .add(Attributes.ATTACK_DAMAGE, 1.0D)
                .add(Attributes.ATTACK_SPEED, 0.5)
                .build();
    }

    protected void registerGoals() {
            super.registerGoals();
            this.goalSelector.addGoal(0, new FloatGoal(this));
            this.goalSelector.addGoal(0, new EatMetalBeadGoal(this,(MetalBeadItem) ModItems.ZINC_BEAD.get()));
            this.goalSelector.addGoal(5, new RiotEntitiesNearTargetGoal(this));
            this.goalSelector.addGoal(2, new RaiderOpenDoorGoal(this));
            this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 8.0F, 0.6D, 1.0D));

            this.goalSelector.addGoal(8, new RandomStrollGoal(this, 0.4D));
            this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
            this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F));

            this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, Raider.class)).setAlertOthers());
            this.targetSelector.addGoal(2, (new NearestAttackableTargetGoal<>(this, Player.class, true)));
            this.targetSelector.addGoal(3, (new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false)));
            this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, false));
    }

    public IllagerArmPose getArmPose() {
        return this.isCelebrating() ? IllagerArmPose.CELEBRATING : IllagerArmPose.CROSSED;
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.EVOKER_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.EVOKER_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.EVOKER_HURT;
    }

    @Override
    public SoundEvent getCelebrateSound() {
        return SoundEvents.EVOKER_CELEBRATE;
    }

    @Override
    public void applyRaidBuffs(int pWave, boolean pUnusedFalse) {
        super.applyRaidBuffs(pWave, pUnusedFalse);
        this.isFlaring = true;
    }

    protected static class RiotEntitiesNearTargetGoal extends Goal {
        private final Mistager mob;

        public RiotEntitiesNearTargetGoal(Mistager pMob) {
            this.mob = pMob;
        }

        public boolean canUse() {
            if(mob.getTarget() == null) {
                return false;
            }
            int range = mob.isFlaring ? 32: 16;
            return mob.distanceTo(mob.getTarget()) <= range && (mob.metalStores > 0 && !mob.getOffhandItem().getItem().equals(ModItems.ZINC_BEAD.get()));
        }

        public void start() {
            super.start();
            mob.setBurning(true);

        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        public void tick() {
            if(this.mob.tickCount % 50 == 0 && this.mob.isBurning) {
                if(mob.getTarget() == null) {
                    return;
                }

                int range = mob.isFlaring ? 48: 24;

                for(PathfinderMob entity: mob.level.getEntitiesOfClass(PathfinderMob.class, mob.getBoundingBox().inflate(range)).stream().filter(subject -> !(subject instanceof Mistager)).toList()) {
                    if(entity.isNoAi()) {
                        entity.setNoAi(false);
                    }

                    entity.targetSelector.enableControlFlag(Goal.Flag.TARGET);
                    entity.setAggressive(true);

                    entity.setLastHurtByMob(mob.getTarget());
                    entity.setTarget(mob.getTarget());

//                entity.goalSelector.addGoal(1, new MeleeAttackGoal(entity, 1.0D, true));

                    if(entity instanceof Animal && !(entity instanceof NeutralMob)) {
                        entity.goalSelector.addGoal(0, new GenericMeleeAttackGoal(entity, 2.0D, true));
                    }
                }
            }


        }


    }

}
