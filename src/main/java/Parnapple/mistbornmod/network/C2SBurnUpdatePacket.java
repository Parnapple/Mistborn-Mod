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
    private final Metal metal;

    public C2SBurnUpdatePacket(Metal metal) {
        this.metal = metal;
    }

    public C2SBurnUpdatePacket(FriendlyByteBuf buf) {
        this.metal = buf.readEnum(Metal.class);
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeEnum(this.metal);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE SERVER!
            ServerPlayer player = context.getSender();
            player.getCapability(ModCapabilities.ALLOMANCY_INSTANCE).ifPresent(data -> {
                data.toggleBurn(this.metal);
                ModPackets.sendToPlayer(new S2CSyncAllomancerDataPacket(this.metal, data.getStore(this.metal), data.isBurning(this.metal)), player);
            });
        });
        return true;
    }

}
