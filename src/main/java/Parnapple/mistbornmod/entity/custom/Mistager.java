package Parnapple.mistbornmod.entity.custom;

import Parnapple.mistbornmod.item.ModItems;
import Parnapple.mistbornmod.item.custom.MetalBeadItem;
import Parnapple.mistbornmod.util.Metal;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.EnumSet;

public abstract class Mistager extends AbstractIllager {

    int metalStores;
    boolean isBurning;
    boolean isFlaring;
    int eatBeadCooldown;

    protected Mistager(EntityType<? extends AbstractIllager> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.metalStores = 1000;
        this.isBurning = false;
        this.isFlaring = false;
        this.eatBeadCooldown = 0;
    }

    @Override
    public void tick() {
        super.tick();
        if(this.isBurning) {
            int amount = this.isFlaring ? 2: 1;
            this.setMetalStores(this.metalStores - amount);
        } else {
            this.isFlaring = false;
        }
        if(this.metalStores <= 0) {
            this.setBurning(false);
            this.clearMetals();
        }
        if(this.eatBeadCooldown > 0) {
            this.eatBeadCooldown --;
        }
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putBoolean("burning", this.isBurning);
        pCompound.putBoolean("flaring", this.isFlaring);
        pCompound.putInt("metal_stores", this.metalStores);
        pCompound.putInt("bead_cooldown", this.eatBeadCooldown);
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        if (pCompound.contains("burning")) {
            this.setBurning(pCompound.getBoolean("burning"));
        }
        if (pCompound.contains("flaring")) {
            this.setBurning(pCompound.getBoolean("flaring"));
        }
        if (pCompound.contains("metal_stores")) {
            this.setMetalStores(pCompound.getInt("metal_stores"));
        }
        if (pCompound.contains("bead_cooldown")) {
            this.eatBeadCooldown = pCompound.getInt("bead_cooldown");
        }
    }

    public boolean isBurning() {
        return this.isBurning;
    }

    public abstract Metal type();

    public void clearMetals() {
        this.metalStores = 0;
    }

    public void setMetalStores(int stores) {
        this.metalStores = stores;
    }

    public void setEatBeadCooldown(int cooldown) {
        this.eatBeadCooldown = cooldown;
    }

    public void setBurning(boolean burning) {
        this.isBurning = burning;
    }

    public boolean isClouded() {
        for(MistagerCopperEntity smoker: level.getEntitiesOfClass(MistagerCopperEntity.class, this.getBoundingBox().inflate(48))) {
            if(smoker.isBurning) {
                if(smoker.isFlaring || smoker.distanceTo(this) < 24) {
                    return true;
                }
            }
        }


        return false;
    }

    @Override
    public void applyRaidBuffs(int pWave, boolean pUnusedFalse) {
        this.metalStores += 1600;
    }

    protected class EatMetalBeadGoal extends Goal {
        private final Mistager mob;
        private final MetalBeadItem beadType;

        public EatMetalBeadGoal(Mistager pMob, MetalBeadItem bead) {
            this.mob = pMob;
            this.beadType = bead;
        }

        public boolean canUse() {
            return mob.eatBeadCooldown <= 0 && (mob.getOffhandItem().getItem() instanceof MetalBeadItem || mob.metalStores <= 0);
        }

        public void start() {
            super.start();
            this.mob.getNavigation().stop();
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        public void tick() {
            mob.setTarget(null);
            mob.getNavigation().stop();
            if(mob.metalStores <= 0) {
                mob.setItemInHand(InteractionHand.OFF_HAND, new ItemStack(beadType, 8));
                mob.metalStores = 1;
            }
            else if(tickCount % 5 == 1) {
                mob.metalStores += 500;
                if(!mob.isSilent())
                    mob.playSound(SoundEvents.GENERIC_EAT, mob.getSoundVolume(), mob.getVoicePitch());

                mob.getOffhandItem().shrink(1);
            }
        }

        public void stop() {
            super.stop();
            this.mob.setAggressive(true);
            this.mob.eatBeadCooldown = 450;
        }

    }
}
