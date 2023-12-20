package Parnapple.mistbornmod.event;

import Parnapple.mistbornmod.capability.allomancy.AllomancyCapabilityProvider;
import Parnapple.mistbornmod.capability.feruchemy.FeruchemyCapabilityProvider;
import Parnapple.mistbornmod.capability.ModCapabilities;
import Parnapple.mistbornmod.capability.hemalurgy.HemalurgyCapabilityProvider;
import Parnapple.mistbornmod.network.ModPackets;
import Parnapple.mistbornmod.network.S2CSyncAllomancerDataPacket;
import Parnapple.mistbornmod.util.Metal;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;


public class ModCapEvents {

    @SubscribeEvent
     public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
         if (event.getObject() instanceof Player) {
             if (!event.getObject().getCapability(ModCapabilities.FERUCHEMY_INSTANCE).isPresent()) {
                 // The player does not already have this capability so we need to add the capability provider here
                 event.addCapability(FeruchemyCapabilityProvider.IDENTIFIER, new FeruchemyCapabilityProvider());
             }
             if (!event.getObject().getCapability(ModCapabilities.ALLOMANCY_INSTANCE).isPresent()) {
                 // The player does not already have this capability so we need to add the capability provider here
                 event.addCapability(AllomancyCapabilityProvider.IDENTIFIER, new AllomancyCapabilityProvider());
             }
             if (!event.getObject().getCapability(ModCapabilities.HEMALURGY_INSTANCE).isPresent()) {
                 // The player does not already have this capability so we need to add the capability provider here
                 event.addCapability(HemalurgyCapabilityProvider.IDENTIFIER, new HemalurgyCapabilityProvider());
             }
         }
     }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if(!event.getPlayer().level.isClientSide()) {

            event.getOriginal().reviveCaps();

            ServerPlayer player = (ServerPlayer) event.getPlayer();

            player.getCapability(ModCapabilities.FERUCHEMY_INSTANCE).ifPresent(data -> {
                event.getOriginal().getCapability(ModCapabilities.FERUCHEMY_INSTANCE).ifPresent(oldData -> {
                    for(Metal metal: Metal.values()) {
                        if(oldData.hasPower(metal)) {
                            data.givePower(metal);
                        }
                    }
                });
            });

            player.getCapability(ModCapabilities.ALLOMANCY_INSTANCE).ifPresent(data -> {
                event.getOriginal().getCapability(ModCapabilities.ALLOMANCY_INSTANCE).ifPresent(oldData -> {
                    if(event.isWasDeath()) {
                        Player oldPlayer = event.getOriginal();
                        data.setDeathPos(new BlockPos(oldPlayer.position()), oldPlayer.level.dimension().location().toString());
                    } else if(data.getDeathDimension() != null) {
                        data.setDeathPos(oldData.getDeathPos(), oldData.getDeathDimension().location().toString());
                    }

                    data.setSpawnPos(oldData.getSpawnPos(), oldData.getSpawnDimension().location().toString());

                    for(Metal metal: Metal.values()) {
                        if(oldData.hasPower(metal)) {
                            data.givePower(metal);
                        }
                        if (player.level.getGameRules().getBoolean(GameRules.RULE_KEEPINVENTORY) ||
                                !event.isWasDeath()) { // if keepInventory is true, or they didn't die, allow them to keep their metals, too
                            for (Metal mt : Metal.values()) {
                                data.setStore(mt, oldData.getStore(mt));
                            }
                        }
                        ModPackets.sendToPlayer(new S2CSyncAllomancerDataPacket(metal, data.getStore(metal), data.isBurning(metal), data.isFlaring(metal)), player);
                    }
                });
            });

            player.getCapability(ModCapabilities.HEMALURGY_INSTANCE).ifPresent(data -> {
                event.getOriginal().getCapability(ModCapabilities.HEMALURGY_INSTANCE).ifPresent(oldData -> {
                   data.setSpikeCount(oldData.getSpikeCount());
                });
            });

            event.getOriginal().getCapability(ModCapabilities.FERUCHEMY_INSTANCE).invalidate();
            event.getOriginal().getCapability(ModCapabilities.ALLOMANCY_INSTANCE).invalidate();
            event.getOriginal().getCapability(ModCapabilities.HEMALURGY_INSTANCE).invalidate();
            event.getOriginal().invalidateCaps();

        }
    }

}
