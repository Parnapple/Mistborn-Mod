package Parnapple.mistbornmod.network;

import Parnapple.mistbornmod.capability.ModCapabilities;
import Parnapple.mistbornmod.util.Metal;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class C2SBurnUpdatePacket {
    public C2SBurnUpdatePacket() {

    }

    public C2SBurnUpdatePacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE SERVER!
            ServerPlayer player = context.getSender();
            player.getCapability(ModCapabilities.ALLOMANCY_INSTANCE).ifPresent(data -> {
                if(data.getPowers().length == 1) {
                    data.toggleBurn(data.getPowers()[0]);
                }
            });
        });
        return true;
    }

}
