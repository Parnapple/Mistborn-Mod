package Parnapple.mistbornmod.network;

import Parnapple.mistbornmod.client.ClientAllomancyData;
import Parnapple.mistbornmod.util.Metal;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class S2CSyncGECompassDataPacket {
    private int metal;//1=gold, 2=electrum
    private int pos;

    public S2CSyncGECompassDataPacket(int metal, int pos) {
        this.metal = metal;
        this.pos = pos;
    }

    public S2CSyncGECompassDataPacket(FriendlyByteBuf buf) {
        this.metal = buf.readInt();
        this.pos = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(this.metal);
        buf.writeInt(this.pos);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE CLIENT!
            if(this.metal==1) {
                ClientAllomancyData.setGoldCompassPos(this.pos);
            }
            if(this.metal==2) {
                ClientAllomancyData.setElectrumCompassPos(this.pos);
            }
        });
        return true;
    }

}
