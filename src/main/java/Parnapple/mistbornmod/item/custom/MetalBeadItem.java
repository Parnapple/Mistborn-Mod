package Parnapple.mistbornmod.item.custom;

import Parnapple.mistbornmod.capability.ModCapabilities;
import Parnapple.mistbornmod.network.ModPackets;
import Parnapple.mistbornmod.network.S2CSyncAllomancerDataPacket;
import Parnapple.mistbornmod.util.Metal;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class MetalBeadItem extends Item {
    public final Metal metal;

    public MetalBeadItem(Metal metal, Properties pProperties) {
        super(pProperties);
        this.metal = metal;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        if(!pLevel.isClientSide()) {
            ServerPlayer player = (ServerPlayer) pPlayer;

            player.getCapability(ModCapabilities.ALLOMANCY_INSTANCE).ifPresent(data -> {
                int store = data.getStore(this.metal);
                if(store < 16000) {
                    data.setStore(this.metal, store + 1000);
                    stack.shrink(1);
//                    pLevel.playLocalSound(player.getX(), player.getY(), player.getZ(), SoundEvents.DOLPHIN_EAT, SoundSource.PLAYERS, 100, 100, false);
                    ModPackets.sendToPlayer(new S2CSyncAllomancerDataPacket(this.metal, data.getStore(metal), data.isBurning(metal), data.isFlaring(metal)), player);
                }
            });
            return InteractionResultHolder.consume(stack);
        }

        return InteractionResultHolder.pass(stack);
    }


}
