package Parnapple.mistbornmod.entity.custom;

import Parnapple.mistbornmod.item.ModItems;
import Parnapple.mistbornmod.item.custom.MetalBeadItem;
import Parnapple.mistbornmod.util.Metal;
import com.google.common.collect.Maps;
import com.mojang.math.Vector3f;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RangedCrossbowAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.monster.CrossbowAttackMob;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Pillager;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.InventoryCarrier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.Map;

public class MistagerTinEntity extends Mistager implements CrossbowAttackMob, InventoryCarrier {
    private static final EntityDataAccessor<Boolean> IS_CHARGING_CROSSBOW = SynchedEntityData.defineId(MistagerTinEntity.class, EntityDataSerializers.BOOLEAN);
    private static final int INVENTORY_SIZE = 5;
    private static final int SLOT_OFFSET = 300;
    private static final float CROSSBOW_POWER = 1.6F;
    private final SimpleContainer inventory = new SimpleContainer(5);

    public MistagerTinEntity(EntityType<? extends MistagerTinEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier setAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 24.0D)
                .add(Attributes.MOVEMENT_SPEED,0.35F)
                .add(Attributes.ATTACK_DAMAGE, 5.0D)
                .add(Attributes.FOLLOW_RANGE, 96.0D)
                .build();
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(0, new EatMetalBeadGoal(this, (MetalBeadItem) ModItems.TIN_BEAD.get()));
        this.goalSelector.addGoal(2, new Raider.HoldGroundAttackGoal(this, 40.0F));
        this.goalSelector.addGoal(3, new RangedCrossbowAttackGoal<>(this, 1.0D, 32.0F));
        this.goalSelector.addGoal(8, new RandomStrollGoal(this, 0.6D));
        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 45.0F, 1.0F));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 45.0F));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, Raider.class)).setAlertOthers());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
    }

    @Override
    public void tick() {
        super.tick();

        if(this.isBurning) {
            this.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 1, 0, true, true, false));
        } else if(this.metalStores > 0 && !getOffhandItem().getItem().equals(ModItems.TIN_BEAD.get())) {
            this.setBurning(true);
        }

    }

    @Override
    public void shootCrossbowProjectile(LivingEntity pTarget, ItemStack pCrossbowStack, Projectile pProjectile, float pProjectileAngle) {
        this.shootCrossbowProjectile(this, pTarget, pProjectile, pProjectileAngle, 1.6F);
    }

    @Override
    public void performRangedAttack(LivingEntity pTarget, float pDistanceFactor) {
        this.performCrossbowAttack(this, 1.6F);
    }

    @Override
    public void performCrossbowAttack(LivingEntity pTarget, float pVelocity) {
        InteractionHand interactionhand = ProjectileUtil.getWeaponHoldingHand(this, item -> item instanceof CrossbowItem);
        ItemStack itemstack = this.getItemInHand(interactionhand);
        if (this.isHolding(is -> is.getItem() instanceof CrossbowItem)) {
            CrossbowItem.performShooting(this.level, this, interactionhand, itemstack, pVelocity, (float)(14 - this.level.getDifficulty().getId() * 4)/8);
        }

        this.onCrossbowAttackPerformed();
    }

    @Override
    public void shootCrossbowProjectile(LivingEntity pUser, LivingEntity pTarget, Projectile pProjectile, float pProjectileAngle, float pVelocity) {
        double d0 = pTarget.getX() - pUser.getX();
        double d1 = pTarget.getZ() - pUser.getZ();
        double d2 = Math.sqrt(d0 * d0 + d1 * d1);
        double d3 = pTarget.getY(0.66666666666D) - pProjectile.getY() + d2 * (double)0.0F;
        Vector3f vector3f = this.getProjectileShotVector(pUser, new Vec3(d0, d3, d1), pProjectileAngle);
        pProjectile.setNoGravity(true);
        pProjectile.shoot((double)vector3f.x(), (double)vector3f.y(), (double)vector3f.z(), pVelocity, (float)(14 - pUser.level.getDifficulty().getId() * 4)/8);
        pUser.playSound(SoundEvents.CROSSBOW_SHOOT, 1.0F, 1.0F / (pUser.getRandom().nextFloat() * 0.4F + 0.8F));
    }


    @Override
    public Metal type() {
        return Metal.TIN;
    }


    //Copied from Pillager
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(IS_CHARGING_CROSSBOW, false);
    }

    public boolean canFireProjectileWeapon(ProjectileWeaponItem pProjectileWeapon) {
        return pProjectileWeapon == Items.CROSSBOW;
    }

    public boolean isChargingCrossbow() {
        return this.entityData.get(IS_CHARGING_CROSSBOW);
    }

    public void setChargingCrossbow(boolean pIsCharging) {
        this.entityData.set(IS_CHARGING_CROSSBOW, pIsCharging);
    }

    public void onCrossbowAttackPerformed() {
        this.noActionTime = 0;
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        ListTag listtag = new ListTag();

        for(int i = 0; i < this.inventory.getContainerSize(); ++i) {
            ItemStack itemstack = this.inventory.getItem(i);
            if (!itemstack.isEmpty()) {
                listtag.add(itemstack.save(new CompoundTag()));
            }
        }

        pCompound.put("Inventory", listtag);
    }

    public AbstractIllager.IllagerArmPose getArmPose() {
        if (this.isChargingCrossbow()) {
            return AbstractIllager.IllagerArmPose.CROSSBOW_CHARGE;
        } else if (this.isHolding(is -> is.getItem() instanceof net.minecraft.world.item.CrossbowItem)) {
            return AbstractIllager.IllagerArmPose.CROSSBOW_HOLD;
        } else {
            return this.isAggressive() ? AbstractIllager.IllagerArmPose.ATTACKING : AbstractIllager.IllagerArmPose.NEUTRAL;
        }
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        ListTag listtag = pCompound.getList("Inventory", 10);

        for(int i = 0; i < listtag.size(); ++i) {
            ItemStack itemstack = ItemStack.of(listtag.getCompound(i));
            if (!itemstack.isEmpty()) {
                this.inventory.addItem(itemstack);
            }
        }

        this.setCanPickUpLoot(true);
    }

    public float getWalkTargetValue(BlockPos pPos, LevelReader pLevel) {
        return 0.0F;
    }

    /**
     * Will return how many at most can spawn in a chunk at once.
     */
    public int getMaxSpawnClusterSize() {
        return 1;
    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        this.populateDefaultEquipmentSlots(pDifficulty);
        this.populateDefaultEquipmentEnchantments(pDifficulty);
        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }

    /**
     * Gives armor or weapon for entity based on given DifficultyInstance
     */
    protected void populateDefaultEquipmentSlots(DifficultyInstance pDifficulty) {
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.CROSSBOW));
    }

    protected void enchantSpawnedWeapon(float pChanceMultiplier) {
        super.enchantSpawnedWeapon(pChanceMultiplier);
        if (this.random.nextInt(300) == 0) {
            ItemStack itemstack = this.getMainHandItem();
            if (itemstack.is(Items.CROSSBOW)) {
                Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(itemstack);
                map.putIfAbsent(Enchantments.PIERCING, 1);
                EnchantmentHelper.setEnchantments(map, itemstack);
                this.setItemSlot(EquipmentSlot.MAINHAND, itemstack);
            }
        }

    }

    /**
     * Returns whether this Entity is on the same team as the given Entity.
     */
    public boolean isAlliedTo(Entity pEntity) {
        if (super.isAlliedTo(pEntity)) {
            return true;
        } else if (pEntity instanceof LivingEntity && ((LivingEntity)pEntity).getMobType() == MobType.ILLAGER) {
            return this.getTeam() == null && pEntity.getTeam() == null;
        } else {
            return false;
        }
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.PILLAGER_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.PILLAGER_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.PILLAGER_HURT;
    }

    public Container getInventory() {
        return this.inventory;
    }

    /**
     * Tests if this entity should pickup a weapon or an armor. Entity drops current weapon or armor if the new one is
     * better.
     */
    protected void pickUpItem(ItemEntity pItemEntity) {
        ItemStack itemstack = pItemEntity.getItem();
        if (itemstack.getItem() instanceof BannerItem) {
            super.pickUpItem(pItemEntity);
        } else if (this.wantsItem(itemstack)) {
            this.onItemPickup(pItemEntity);
            ItemStack itemstack1 = this.inventory.addItem(itemstack);
            if (itemstack1.isEmpty()) {
                pItemEntity.discard();
            } else {
                itemstack.setCount(itemstack1.getCount());
            }
        }

    }

    private boolean wantsItem(ItemStack pItem) {
        return this.hasActiveRaid() && pItem.is(Items.WHITE_BANNER);
    }

    public SlotAccess getSlot(int pSlot) {
        int i = pSlot - 300;
        return i >= 0 && i < this.inventory.getContainerSize() ? SlotAccess.forContainer(this.inventory, i) : super.getSlot(pSlot);
    }

    public void applyRaidBuffs(int pWave, boolean pUnusedFalse) {
        super.applyRaidBuffs(pWave, pUnusedFalse);
        Raid raid = this.getCurrentRaid();
        boolean flag = this.random.nextFloat() <= raid.getEnchantOdds();
        if (flag) {
            ItemStack itemstack = new ItemStack(Items.CROSSBOW);
            Map<Enchantment, Integer> map = Maps.newHashMap();
            if (pWave > raid.getNumGroups(Difficulty.NORMAL)) {
                map.put(Enchantments.QUICK_CHARGE, 2);
            } else if (pWave > raid.getNumGroups(Difficulty.EASY)) {
                map.put(Enchantments.QUICK_CHARGE, 1);
            }

            map.put(Enchantments.MULTISHOT, 1);
            EnchantmentHelper.setEnchantments(map, itemstack);
            this.setItemSlot(EquipmentSlot.MAINHAND, itemstack);
        }

    }

    public SoundEvent getCelebrateSound() {
        return SoundEvents.PILLAGER_CELEBRATE;
    }


}
