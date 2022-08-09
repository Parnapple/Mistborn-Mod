package Parnapple.mistbornmod.event;

import Parnapple.mistbornmod.capability.FeruchemyCapabilityProvider;
import Parnapple.mistbornmod.capability.ModCapabilities;
import Parnapple.mistbornmod.util.Metal;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;


public class FeruchemistCapEvents {

    @SubscribeEvent
     public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
         if (event.getObject() instanceof Player) {
             if (!event.getObject().getCapability(ModCapabilities.FERUCHEMY_INSTANCE).isPresent()) {
                 // The player does not already have this capability so we need to add the capability provider here
                 event.addCapability(FeruchemyCapabilityProvider.IDENTIFIER, new FeruchemyCapabilityProvider());
             }
         }
     }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if(!event.getPlayer().level.isClientSide()) {

            event.getOriginal().reviveCaps();

            Player player = event.getPlayer();

            player.getCapability(ModCapabilities.FERUCHEMY_INSTANCE).ifPresent(data -> {
                event.getOriginal().getCapability(ModCapabilities.FERUCHEMY_INSTANCE).ifPresent(oldData -> {
                    for(Metal metal: Metal.values()) {
                        if(oldData.hasPower(metal)) {
                            data.givePower(metal);
                        }
                    }
                });
            });

            event.getOriginal().getCapability(ModCapabilities.FERUCHEMY_INSTANCE).invalidate();
            event.getOriginal().invalidateCaps();

        }
    }

}
