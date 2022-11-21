package Parnapple.mistbornmod.network;

import Parnapple.mistbornmod.client.ClientAllomancyData;
import Parnapple.mistbornmod.util.Metal;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class S2CSyncAllomancyPowerDataPacket {
    private final Metal[] metals;
    private final int length;

    public S2CSyncAllomancyPowerDataPacket(Metal[] metal) {
        this.metals = metal;
        this.length = metal.length;
    }

    public S2CSyncAllomancyPowerDataPacket(FriendlyByteBuf buf) {
        this.length = buf.readInt();
        this.metals = new Metal[length];
        for(int i = 0; i < length; i++) {
            this.metals[i] = buf.readEnum(Metal.class);
        }
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(this.length);
        for(Metal mt: this.metals) {
            buf.writeEnum(mt);
        }
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE CLIENT!
            ClientAllomancyData.removeAllPowers();
            for(Metal mt: this.metals) {
                ClientAllomancyData.givePower(mt);
            }
        });
        return true;
    }

}
