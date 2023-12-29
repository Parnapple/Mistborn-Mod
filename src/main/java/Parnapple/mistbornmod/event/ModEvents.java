package Parnapple.mistbornmod.event;

import Parnapple.mistbornmod.MistbornBaseMod;
import Parnapple.mistbornmod.capability.ModCapabilities;
import Parnapple.mistbornmod.command.AllomancyCommand;
import Parnapple.mistbornmod.command.FeruchemyCommand;
import Parnapple.mistbornmod.effect.ModEffects;
import Parnapple.mistbornmod.entity.CoinProjectileEntity;
import Parnapple.mistbornmod.item.custom.HemalurgicSpikeItem;
import Parnapple.mistbornmod.network.ModPackets;
import Parnapple.mistbornmod.network.S2CSyncAllomancerDataPacket;
import Parnapple.mistbornmod.network.S2CSyncAllomancyPowerDataPacket;
import Parnapple.mistbornmod.network.S2CSyncGECompassDataPacket;
import Parnapple.mistbornmod.util.Metal;
import com.google.common.collect.Sets;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.animal.horse.Llama;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerSetSpawnEvent;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

import java.util.List;
import java.util.Random;
import java.util.Set;

@Mod.EventBusSubscriber(modid = MistbornBaseMod.MOD_ID)
public class ModEvents {

    private static Set<ResourceLocation> steelInjectSet = Sets.newHashSet(
            BuiltInLootTables.ABANDONED_MINESHAFT,
            BuiltInLootTables.DESERT_PYRAMID,
            BuiltInLootTables.BASTION_OTHER,
            BuiltInLootTables.END_CITY_TREASURE,
            BuiltInLootTables.JUNGLE_TEMPLE,
            BuiltInLootTables.SIMPLE_DUNGEON,
            BuiltInLootTables.ABANDONED_MINESHAFT,
            BuiltInLootTables.BURIED_TREASURE,
            BuiltInLootTables.PILLAGER_OUTPOST,
            BuiltInLootTables.NETHER_BRIDGE,
            BuiltInLootTables.SHIPWRECK_TREASURE,
            BuiltInLootTables.UNDERWATER_RUIN_BIG,
            BuiltInLootTables.STRONGHOLD_CROSSING,
            BuiltInLootTables.VILLAGE_ARMORER,
            BuiltInLootTables.BASTION_BRIDGE
    );

    @SubscribeEvent
    public static void onLootTableLoad(final LootTableLoadEvent event) {
       if(steelInjectSet.contains(event.getName())) {
           event
                   .getTable()
                   .addPool(LootPool
                           .lootPool()
                           .name("steel_inject")
                           .add(LootTableReference.lootTableReference(new ResourceLocation(MistbornBaseMod.MOD_ID, "chests/inject/steel_ingot")))
                           .build());
       }

   }

    @SubscribeEvent
    public static void lowerEntityVisibilityIfHasStealthEffect(LivingEvent.LivingVisibilityEvent event) {
        LivingEntity entity = event.getEntityLiving();

        if(entity.hasEffect(ModEffects.STEALTH.get())) {
            event.modifyVisibility(-100.0);
        }
        //the rest of the functionality is in the StealthEffect class
    }

    @SubscribeEvent
    public static void knockbackResistanceFromWeightOrLightnessEffects(LivingKnockBackEvent event) {
        LivingEntity entity = event.getEntityLiving();
        int knockbackModifier = 0;

        if(entity.hasEffect(ModEffects.WEIGHT.get())) {
            knockbackModifier = -1 * entity.getEffect(ModEffects.WEIGHT.get()).getAmplifier();
        } else if(entity.hasEffect(ModEffects.LIGHTNESS.get())) {
            knockbackModifier = 1 * entity.getEffect(ModEffects.LIGHTNESS.get()).getAmplifier();
        }

        event.setStrength(event.getStrength() + knockbackModifier);


    }

    @SubscribeEvent
    public static void extraExperienceWithMentalSpeedEffect(PlayerXpEvent.PickupXp event) {
        Player player = event.getPlayer();

        if(player.hasEffect(ModEffects.MENTAL_SLOWDOWN.get())) {
            event.getOrb().value /=  (player.getEffect(ModEffects.MENTAL_SLOWDOWN.get()).getAmplifier()+2);
        } else if(player.hasEffect(ModEffects.MENTAL_SPEED.get())) {
            event.getOrb().value +=  1 + player.getEffect(ModEffects.MENTAL_SPEED.get()).getAmplifier();
        }


    }

    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {
        new FeruchemyCommand(event.getDispatcher());
        new AllomancyCommand(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void giveRandomPowerOnJoinWorld(PlayerEvent.PlayerLoggedInEvent event) {
        if(!event.getPlayer().level.isClientSide) {
            if(event.getPlayer() instanceof ServerPlayer player) {

                player.getCapability(ModCapabilities.FERUCHEMY_INSTANCE).ifPresent(data -> {
                            if(!data.isFeruchemist() && !data.everFeruchemist()) {
                                byte randomMetal = (byte) (Math.random() * Metal.values().length);
                                data.givePower(Metal.getMetal(randomMetal));
                            }
                        });

                player.getCapability(ModCapabilities.ALLOMANCY_INSTANCE).ifPresent(data -> {
                    if(!data.isAllomancer() && !data.everAllomancer()) {
                        byte randomMetal = (byte) (Math.random() * Metal.values().length);
                        Metal power = Metal.getMetal(randomMetal);
                        data.givePower(power);
                        data.setSpawnPos(player.getRespawnPosition(), player.getRespawnDimension().location().toString());
                        ModPackets.sendToPlayer(new S2CSyncAllomancerDataPacket(power, data.getStore(power), data.isBurning(power), data.isFlaring(power)), player);
                    }
                    for(Metal mt: Metal.values()) {
                        ModPackets.sendToPlayer(new S2CSyncAllomancerDataPacket(mt, data.getStore(mt), data.isBurning(mt), data.isFlaring(mt)), player);
                    }
                    ModPackets.sendToPlayer(new S2CSyncAllomancyPowerDataPacket(data.getPowers()), player);
                });

            }
        }
    }

    @SubscribeEvent
    public static void onWorldTick(final TickEvent.WorldTickEvent event) {
        if (event.phase != TickEvent.Phase.END || event.side == LogicalSide.CLIENT) {
            return;
        }

        Level level = event.world;
        List<? extends Player> list = level.players();
        for(Player player : list) {
            playerPowerTick(player, level);
        }
    }


    private static void playerPowerTick(Player player, Level level) {
        player.getCapability(ModCapabilities.ALLOMANCY_INSTANCE).ifPresent(data -> {
            if(data.isAllomancer()) {

                if(data.isBurning(Metal.DURALUMIN)) {
                    data.setEnhanced(true);
                }


                for(Metal mt: Metal.values()) {
                    if(data.isBurning(mt)) {
                        if(data.getStore(mt) <= 0 || !data.hasPower(mt)) {
                            data.toggleBurn(mt);
                            data.setEnhanced(false);
                        } else {
                            int burnPower = data.isFlaring(mt) ? 2 : 1;
                            if(data.isEnhanced()) {
                                burnPower = 20;
                            }
//                            if(data.isFlaring(mt))
//                                player.displayClientMessage(new TextComponent("You are flaring: " + mt.getName()), true);
                            data.setStore(mt, data.getStore(mt) - burnPower);
                        }
                        ModPackets.sendToPlayer(new S2CSyncAllomancerDataPacket(mt, data.getStore(mt), data.isBurning(mt), data.isFlaring(mt)), (ServerPlayer) player);
                    } else {
                        data.toggleFlaring(mt, false);
                    }
                }


                if(data.isBurning(Metal.TIN)) {
                        player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 100, 17, true, false, false));
                        if(data.isEnhanced()) {
                            player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 100, 1, true, false, false));
                        }
                }
                if(!data.isBurning(Metal.TIN) && player.hasEffect(MobEffects.NIGHT_VISION) && player.getEffect(MobEffects.NIGHT_VISION).getAmplifier() == 17) {
                    player.removeEffect(MobEffects.NIGHT_VISION);
                }

                if(data.isBurning(Metal.PEWTER)) {
                    int strength = data.isFlaring(Metal.PEWTER) ? 1 : 0;
                    if(data.isEnhanced()) {
                        strength = 10;
                    }
                    player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 10, strength, true, false, false));
                    player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,10, strength, true, false, false));
                    player.addEffect(new MobEffectInstance(MobEffects.JUMP, 10, strength, true, false, false));
                    player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 10, strength, true, false, false));
                }

                if(data.isBurning(Metal.ALUMINUM)) {
                    for(Metal mt: Metal.values()) {
                        data.setStore(mt, 0);
                        ModPackets.sendToPlayer(new S2CSyncAllomancerDataPacket(mt, data.getStore(mt), data.isBurning(mt), data.isFlaring(mt)), (ServerPlayer) player);
                    }
                }

                if(data.isBurning(Metal.BRASS)) {
                    int strength = data.isFlaring(Metal.BRASS) ? 8 : 4;
                    if(data.isEnhanced()) {
                        strength = 15;
                    }
                    for(PathfinderMob entity: level.getEntitiesOfClass(PathfinderMob.class, player.getBoundingBox().inflate(strength))) {

                        entity.goalSelector.getRunningGoals().filter(ModEvents::isAggressiveGoal).forEach(WrappedGoal::stop);
                        entity.targetSelector.getRunningGoals().filter(ModEvents::isAggressiveGoal).forEach(WrappedGoal::stop);
                        entity.goalSelector.tick();
                        entity.targetSelector.tick();

                        entity.setLastHurtByMob(null);
                        entity.setTarget(null);
                        entity.setAggressive(false);
                        entity.targetSelector.disableControlFlag(Goal.Flag.TARGET);

                        if(entity instanceof TamableAnimal tamableAnimal) {
                            tamableAnimal.tame(player);
                        }
                        if(data.isEnhanced()) {
                            entity.setNoAi(true);
                        }
                    }

                }

                if(data.isBurning(Metal.ZINC)) {
                    int strength = data.isFlaring(Metal.ZINC) ? 16 : 8;
                    if(data.isEnhanced()) {
                        strength = 40;
                    }
                    for(PathfinderMob entity: level.getEntitiesOfClass(PathfinderMob.class, player.getBoundingBox().inflate(strength))) {
                        Random random = new Random();

                        if(entity.isNoAi()) {
                            entity.setNoAi(false);
                        }

                        entity.targetSelector.enableControlFlag(Goal.Flag.TARGET);
                        entity.setAggressive(true);

                        if(!(entity instanceof Bee || entity instanceof Wolf || entity instanceof Llama) && entity instanceof Animal animal) {
                            if(!animal.isBaby() && random.nextInt(100) == 1)
                                    animal.setInLove(player);
                        } else if (entity.getTarget() == null || entity.getTarget().equals(player)) {
                            int size = level.getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(8)).size();
                            LivingEntity newTarget = level.getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(8)).get(random.nextInt(size));
                            if(!entity.equals(newTarget)) {
                                entity.setLastHurtByMob(newTarget);
                                entity.setTarget(newTarget);
                            }
                        }

                        if (entity instanceof Creeper creeper) {
                            entity.goalSelector.addGoal(1, new SwellGoal(creeper));
                        }
                        if (entity instanceof AbstractHorse horse) {
                            entity.goalSelector.addGoal(10, new RunAroundLikeCrazyGoal(horse, 3));
                        }
                        if (entity instanceof AbstractSkeleton skeleton) {
                            entity.goalSelector.addGoal(1, new RangedBowAttackGoal<>(skeleton, 1.0D, 20, 15.0F));
                        }
                        if (entity instanceof Illusioner illusioner) {
                            entity.goalSelector.addGoal(1, new RangedBowAttackGoal<>(illusioner, 0.5D, 20, 15.0F));
                        }
                        if (entity instanceof Pillager pillager) {
                            entity.goalSelector.addGoal(2, new RangedCrossbowAttackGoal<>(pillager, 1.0D, 8.0F));
                        }

                    }

                }

                if(data.isBurning(Metal.BENDALLOY) && !data.isBurning(Metal.CADMIUM)) {
                    if(!level.isClientSide()) {
                        int range = data.isFlaring(Metal.BENDALLOY) ? 6 : 3;
                        int power = data.isFlaring(Metal.BENDALLOY) ? 6 : 3;
                        if(data.isEnhanced()) {
                            range = 12;
                            power = 12;
                        }
                        AABB withinRange = player.getBoundingBox().inflate(range);
                        AABB outsideRange = player.getBoundingBox().inflate(range*8);

                        List<LivingEntity> entitiesInRange = level.getEntitiesOfClass(LivingEntity.class, withinRange);
                        List<LivingEntity> entitiesOutsideRange = level.getEntitiesOfClass(LivingEntity.class, outsideRange).stream().filter(entity -> !entitiesInRange.contains(entity)).toList();

                        for(LivingEntity entity: entitiesOutsideRange) {
                            entity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 10, power, true, false, false));
                            entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 10, power, true, false, false));
                        }
                        for(LivingEntity entity: entitiesInRange) {
                            entity.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 10, power, true, false, false));
                        }
                    }
                }
                else if(data.isBurning(Metal.CADMIUM) && !data.isBurning(Metal.BENDALLOY)) {
                    if(!level.isClientSide()) {
                        int range = data.isFlaring(Metal.CADMIUM) ? 8 : 4;
                        int power = data.isFlaring(Metal.CADMIUM) ? 6 : 3;
                        if(data.isEnhanced()) {
                            range = 16;
                            power = 12;
                        }
                        AABB withinRange = player.getBoundingBox().inflate(range);
                        AABB outsideRange = player.getBoundingBox().inflate(range*8);

                        List<LivingEntity> entitiesInRange = level.getEntitiesOfClass(LivingEntity.class, withinRange);
                        List<LivingEntity> entitiesOutsideRange = level.getEntitiesOfClass(LivingEntity.class, outsideRange).stream().filter(entity -> !entitiesInRange.contains(entity)).toList();

                        for(LivingEntity entity: entitiesInRange) {
                            entity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 10, power, true, false, false));
                            entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 10, power, true, false, false));
                        }
                        for(LivingEntity entity: entitiesOutsideRange) {
                            entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 10, power, true, false, false));
                            entity.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 10, power, true, false, false));
                            entity.tick();
                        }

                    }
                }

                if(data.isBurning(Metal.GOLD)) {
                    BlockPos deathPos = data.getDeathPos();
                    if(deathPos != null) {
                        if(data.isFlaring(Metal.GOLD))
                            player.displayClientMessage(new TextComponent(deathPos.toString()), true);

                        int pos = getCompassPos(deathPos, player);
                        //player.displayClientMessage(new TextComponent("Gold pos: " + pos), true);
                        if(data.isEnhanced()) {
                            ResourceKey<Level> dim = data.getDeathDimension();
                            if((!player.level.dimension().equals(dim)) && (data.getDeathDimension() != null)) {
                                player.changeDimension(level.getServer().getLevel(dim));
                            }
                            player.teleportToWithTicket(deathPos.getX(), deathPos.getY()+3, deathPos.getZ());
                            player.fallDistance = 0;
                            data.setStore(Metal.GOLD, 0);
                            data.setEnhanced(false);
                        }

                        ModPackets.sendToPlayer(new S2CSyncGECompassDataPacket(1, pos), (ServerPlayer) player);

                    }


                }
                if(data.isBurning(Metal.ELECTRUM)) {
                    BlockPos spawnPos;

                    if(data.getSpawnPos() == null) {
                        spawnPos = new BlockPos(level.getLevelData().getXSpawn(), level.getLevelData().getYSpawn(), level.getLevelData().getZSpawn());
                        data.setSpawnPos(spawnPos, Level.OVERWORLD.location().toString());
                    } else {
                        spawnPos = data.getSpawnPos();
                    }

                    int pos = getCompassPos(spawnPos, player);
                    //player.displayClientMessage(new TextComponent("Electrum pos: " + pos), true);

                    if(data.isEnhanced()) {
                        ResourceKey<Level> dim = data.getSpawnDimension();
                        if(!player.level.dimension().equals(dim)) {
                            player.changeDimension(level.getServer().getLevel(dim));
                        }
                        player.teleportToWithTicket(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ());
                        player.fallDistance = 0;
                        data.setStore(Metal.ELECTRUM, 0);
                        data.setEnhanced(false);
                    }

                    ModPackets.sendToPlayer(new S2CSyncGECompassDataPacket(2, pos), (ServerPlayer) player);

                    if(data.isFlaring(Metal.ELECTRUM))
                        player.displayClientMessage(new TextComponent(spawnPos.toString()), true);
                }

                if(data.isBurning(Metal.BRONZE)) {
                    int power = data.isFlaring(Metal.BRONZE) ? 128 : 64;
                    List<ServerPlayer> players = level.getEntitiesOfClass(ServerPlayer.class, player.getBoundingBox().inflate(power));

                    for(ServerPlayer otherPlayer: players) {
                        otherPlayer.getCapability(ModCapabilities.ALLOMANCY_INSTANCE).ifPresent(otherData -> {
                            if(!otherData.isBurning(Metal.COPPER)) {
                                for(Metal mt: Metal.values()) {
                                    if(otherData.isBurning(mt)) {
                                        if(!otherPlayer.equals(player)) {
                                            otherPlayer.addEffect(new MobEffectInstance(MobEffects.GLOWING, 10));
                                            if(data.isEnhanced()) {
                                                otherPlayer.teleportTo(player.getX(), player.getY(), player.getZ());
                                            }
                                        }
                                    }
                                }
                            }
                        });
                    }

                }

                if(data.isBurning(Metal.CHROMIUM) && data.isEnhanced()) {
                    int range = 8;
                    List<ServerPlayer> players = level.getEntitiesOfClass(ServerPlayer.class, player.getBoundingBox().inflate(range));

                    for(ServerPlayer otherPlayer: players) {
                        otherPlayer.getCapability(ModCapabilities.ALLOMANCY_INSTANCE).ifPresent(otherData -> {
                            if(!otherPlayer.equals(player)) {
                                for(Metal mt: Metal.values()) {
                                    otherData.setStore(mt, 0);
                                }
                            }
                        });
                    }

                }

            }
        });
    }

    @SubscribeEvent
    public static void onAttack(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof ServerPlayer source) {
            source.getCapability(ModCapabilities.ALLOMANCY_INSTANCE).ifPresent(data -> {
                if(data.isBurning(Metal.CHROMIUM)) {
                    if (event.getEntityLiving() instanceof Player player) {
                        player.getCapability(ModCapabilities.ALLOMANCY_INSTANCE).ifPresent(otherData -> {
                            for(Metal mt: Metal.values()) {
                                otherData.setStore(mt, 0);
                            }
                        });
                    }
                }
                if(data.isBurning(Metal.NICROSIL)) {
                    if (event.getEntityLiving() instanceof Player player) {
                        player.getCapability(ModCapabilities.ALLOMANCY_INSTANCE).ifPresent(otherData -> {
                            for(Metal mt: Metal.values()) {
                                otherData.setEnhanced(true);
                            }
                        });
                    }
                }
            });
        }
    }

    @SubscribeEvent
    public static void onDeath(LivingDeathEvent event) {
        if(event.getSource().getEntity() instanceof LivingEntity entity) {
            if(entity.getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof HemalurgicSpikeItem spike) {
                if(event.getEntityLiving() instanceof ServerPlayer player) {
                    spike.onPlayerKill(player, entity.getItemInHand(InteractionHand.MAIN_HAND));
                } else if(event.getEntityLiving() instanceof AbstractIllager) {
                    spike.onEntityKill(event.getEntityLiving(), entity.getItemInHand(InteractionHand.MAIN_HAND));
                }
            }
        }


    }

    @SubscribeEvent
    public static void onSetSpawn(PlayerSetSpawnEvent event) {
        if(event.getPlayer() instanceof ServerPlayer player) {
            player.getCapability(ModCapabilities.ALLOMANCY_INSTANCE).ifPresent(data -> {
                data.setSpawnPos(event.getNewSpawn(), event.getSpawnWorld().location().toString());
            });
        }
    }

    private static boolean isAggressiveGoal(Goal goal) {
        return goal instanceof TargetGoal
                || goal instanceof SwellGoal
                || goal.getClass().getName().contains("Attack")
                || goal.getClass().getName().contains("Shoot")
                || goal.getClass().getName().contains("Spell");
    }


    private static double getAngleTo(Vec3 vec3, Entity entity) {
        return Math.atan2(vec3.z() - entity.getZ(), vec3.x() - entity.getX());
    }

    private static float getAngleThingForCompass(BlockPos pos, Player player) {
        double d1 = player.getYRot();
        d1 = Mth.positiveModulo(d1 / 360.0D, 1.0D);
        double d2 = getAngleTo(Vec3.atCenterOf(pos), player) / (double)((float)Math.PI * 2F);
        double d3 = 0.5D - (d1 - 0.25D - d2);
        return Mth.positiveModulo((float)d3, 1);
    }

    private static int getCompassPos(BlockPos pos, Player player) {
        int angle = Math.round(getAngleThingForCompass(pos, player) *100);
        if(0 <= angle && angle < 5 || 95 <= angle && angle <= 100) {
            return 5;
        } else if(5 <= angle && angle < 20) {
            return 4;
        } else if(20 <= angle && angle < 30) {
            return 3;
        } else if(30 <= angle && angle < 45) {
            return 2;
        } else if(45 <= angle && angle < 55) {
            return 1;
        } else if(55 <= angle && angle < 70) {
            return 8;
        } else if(70 <= angle && angle < 80) {
            return 7;
        }else if(80 <= angle && angle < 95) {
            return 6;
        }

        return angle;
    }


}
