package Parnapple.mistbornmod.network;

import Parnapple.mistbornmod.capability.ModCapabilities;
import Parnapple.mistbornmod.client.ClientAllomancyData;
import Parnapple.mistbornmod.util.Metal;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class S2CSyncAllomancerDataPacket {
    private final Metal metal;
    private final int store;
    private final boolean burn;
    private final boolean flare;

    public S2CSyncAllomancerDataPacket(Metal metal, int store, boolean burn, boolean flare) {
        this.metal = metal;
        this.store = store;
        this.burn = burn;
        this.flare = flare;
    }

    public S2CSyncAllomancerDataPacket(FriendlyByteBuf buf) {
        this.metal = Metal.getMetal(buf.readInt());
        this.store = buf.readInt();
        this.burn = buf.readBoolean();
        this.flare = buf.readBoolean();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(this.metal.getIndex());
        buf.writeInt(this.store);
        buf.writeBoolean(this.burn);
        buf.writeBoolean(this.flare);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE CLIENT!
            ClientAllomancyData.set(this.metal, this.store);
            ClientAllomancyData.setBurning(this.metal, this.burn);
            ClientAllomancyData.setFlaring(this.metal, this.flare);
        });
        return true;
    }

}
