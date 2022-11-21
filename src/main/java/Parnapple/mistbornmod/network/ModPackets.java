package Parnapple.mistbornmod.network;

import Parnapple.mistbornmod.MistbornBaseMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModPackets {
    private static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(MistbornBaseMod.MOD_ID, "newtorking"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(C2SBurnUpdatePacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(C2SBurnUpdatePacket::new)
                .encoder(C2SBurnUpdatePacket::toBytes)
                .consumer(C2SBurnUpdatePacket::handle)
                .add();
        net.messageBuilder(C2SPushPullPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(C2SPushPullPacket::new)
                .encoder(C2SPushPullPacket::toBytes)
                .consumer(C2SPushPullPacket::handle)
                .add();
        net.messageBuilder(S2CSyncAllomancerDataPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(S2CSyncAllomancerDataPacket::new)
                .encoder(S2CSyncAllomancerDataPacket::toBytes)
                .consumer(S2CSyncAllomancerDataPacket::handle)
                .add();
        net.messageBuilder(S2CSyncAllomancyPowerDataPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(S2CSyncAllomancyPowerDataPacket::new)
                .encoder(S2CSyncAllomancyPowerDataPacket::toBytes)
                .consumer(S2CSyncAllomancyPowerDataPacket::handle)
                .add();
        net.messageBuilder(S2CSyncGECompassDataPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(S2CSyncGECompassDataPacket::new)
                .encoder(S2CSyncGECompassDataPacket::toBytes)
                .consumer(S2CSyncGECompassDataPacket::handle)
                .add();

    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
}
