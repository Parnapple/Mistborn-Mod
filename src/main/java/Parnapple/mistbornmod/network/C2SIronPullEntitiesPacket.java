package Parnapple.mistbornmod.network;

import Parnapple.mistbornmod.capability.ModCapabilities;
import Parnapple.mistbornmod.entity.CoinProjectileEntity;
import Parnapple.mistbornmod.util.Metal;
import Parnapple.mistbornmod.util.MetalUtil;
import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;

import java.util.List;
import java.util.function.Supplier;

public class C2SIronPullEntitiesPacket {

    public C2SIronPullEntitiesPacket() {

    }

    public C2SIronPullEntitiesPacket(FriendlyByteBuf buf) {


    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE SERVER
            ServerPlayer player = context.getSender();
            Level level = player.level;
            player.getCapability(ModCapabilities.ALLOMANCY_INSTANCE).ifPresent(data -> {
                int power = data.isFlaring(Metal.IRON) ? 16 : 8;
                if(data.isEnhanced()) {
                    power = 64;
                }
                List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(power));

                for(LivingEntity entity: entities) {
                    ItemStack item = entity.getItemInHand(InteractionHand.MAIN_HAND);
                    if(MetalUtil.isItemMetal(item.getItem()) && !entity.equals(player)) {
                        ItemStack stack = item.copy();
                        stack.setCount(1);
                        ItemEntity itemEntity = new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), stack);
                        item.shrink(1);
                        level.addFreshEntity(itemEntity);
                    }
                    item = entity.getItemInHand(InteractionHand.OFF_HAND);
                    if(MetalUtil.isItemMetal(item.getItem()) && !entity.equals(player)) {
                        ItemStack stack = item.copy();
                        stack.setCount(1);
                        ItemEntity itemEntity = new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), stack);
                        item.shrink(1);
                        level.addFreshEntity(itemEntity);
                    }
                }

                List<Entity> entityList = level.getEntitiesOfClass(Entity.class, player.getBoundingBox().inflate(power));

                for(Entity entity: entityList) {
                    if(MetalUtil.isEntityMetal(entity) && !entity.equals(player)) {
//                        entity.moveTo(Vec3.atCenterOf(player.blockPosition()));
                        Vec3 move = Vec3.atCenterOf(player.blockPosition()).subtract(Vec3.atCenterOf(entity.blockPosition())).multiply(0.5, 0.5, 0.5);
                        entity.setDeltaMovement(move);

                        if(entity instanceof CoinProjectileEntity coin) {
                            entity.moveTo(Vec3.atCenterOf(player.blockPosition()));
//                            coin.shoot(move.x, move.y + 1, move.z, 2.5F, 0.0F);
                        }
                    }
                }

            });


        });
        return true;
    }

}
