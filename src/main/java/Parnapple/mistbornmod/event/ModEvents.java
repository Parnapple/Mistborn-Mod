package Parnapple.mistbornmod.event;

import Parnapple.mistbornmod.MistbornBaseMod;
import Parnapple.mistbornmod.capability.ModCapabilities;
import Parnapple.mistbornmod.command.AllomancyCommand;
import Parnapple.mistbornmod.command.FeruchemyCommand;
import Parnapple.mistbornmod.effect.ModEffects;
import Parnapple.mistbornmod.util.Metal;
import com.google.common.collect.Sets;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

import java.util.List;
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
                            if(!data.isFeruchemist()) {
                                byte randomMetal = (byte) (Math.random() * Metal.values().length);
                                data.givePower(Metal.getMetal(randomMetal));
                            }
                        });
                player.getCapability(ModCapabilities.ALLOMANCY_INSTANCE).ifPresent(data -> {
                    if(!data.isAllomancer()) {
                        byte randomMetal = (byte) (Math.random() * Metal.values().length);
                        data.givePower(Metal.getMetal(randomMetal));
                    }
                });

            }
        }
    }

    @SubscribeEvent
    public static void onWorldTick(final TickEvent.WorldTickEvent event) {
        if (event.phase != TickEvent.Phase.END) {
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
                if(data.isBurning(Metal.TIN)) {
                    player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 50, 1, true, false, false));
                }
            }
        });
    }

}
