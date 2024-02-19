package Parnapple.mistbornmod.entity.ai;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.monster.Monster;

public class GenericMeleeAttackGoal extends MeleeAttackGoal {
    public GenericMeleeAttackGoal(PathfinderMob pMob, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
        super(pMob, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
    }

    protected void checkAndPerformAttack(LivingEntity pEnemy, double pDistToEnemySqr) {
        double d0 = this.getAttackReachSqr(pEnemy);
        if (pDistToEnemySqr <= d0 && this.isTimeToAttack()) {
            this.resetAttackCooldown();
            this.mob.swing(InteractionHand.MAIN_HAND);
            if (this.mob instanceof Monster) {
                this.mob.doHurtTarget(pEnemy);
            } else {
                pEnemy.hurt(DamageSource.mobAttack(this.mob), 1);
            }
        }

    }
}
