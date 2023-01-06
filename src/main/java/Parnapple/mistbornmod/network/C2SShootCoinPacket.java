package Parnapple.mistbornmod.network;

import Parnapple.mistbornmod.capability.ModCapabilities;
import Parnapple.mistbornmod.entity.CoinProjectileEntity;
import Parnapple.mistbornmod.util.Metal;
import Parnapple.mistbornmod.util.MetalUtil;
import Parnapple.mistbornmod.util.ModTags;
import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class C2SShootCoinPacket {

    public C2SShootCoinPacket() {

    }

    public C2SShootCoinPacket(FriendlyByteBuf buf) {


    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE SERVER
            ServerPlayer player = context.getSender();
            ServerLevel level = player.getLevel();
            player.getCapability(ModCapabilities.ALLOMANCY_INSTANCE).ifPresent(data -> {
                ItemStack stack = player.getItemInHand(InteractionHand.MAIN_HAND);
                if(!stack.is(ModTags.Items.NUGGETS)) {
                    stack = player.getItemInHand(InteractionHand.OFF_HAND);
                }

                if(stack.is(ModTags.Items.NUGGETS)) {
                    float velocity = data.isFlaring(Metal.STEEL) ? 3.5F : 2.5F;
                    if(data.isEnhanced()) {
                        velocity = 10F;
                    }

                    if(player.isDiscrete() && stack.getCount() >= 8) {
                        stack.shrink(8);
                        Vec3[] positions = new Vec3[8];
                        Vec3 forward = player.getForward();

                        for(int i = 0; i < positions.length; i++) {
                            positions[i] = forward.yRot((i-4) * 20 + 10);
                        }

//                        positions[0] = forward;
//                        positions[1] = forward.yRot(45);
//                        positions[2] = forward.yRot(90);
//                        positions[3] = forward.yRot(135);
//                        positions[4] = forward.yRot(180);
//                        positions[5] = forward.yRot(225);
//                        positions[6] = forward.yRot(270);
//                        positions[7] = forward.yRot(315);

                        for(Vec3 position: positions) {
                            CoinProjectileEntity coin = new CoinProjectileEntity(level, player, new ItemStack(stack.getItem()));
                            double dx = position.x();
                            double dy = position.y();
                            double dz = position.z();
                            coin.shoot(dx, dy, dz, velocity, 0.0F);
                            level.addFreshEntity(coin);
                        }

                    } else {
                        stack.shrink(1);
//                    ThrownTrident coin = new ThrownTrident(level, player, new ItemStack(stack.getItem()));
                        CoinProjectileEntity coin = new CoinProjectileEntity(level, player, new ItemStack(stack.getItem()));
                        Vec3 forward = player.getLookAngle();
                        double dx = forward.x();
                        double dy = forward.y();
                        double dz = forward.z();
                        coin.shoot(dx, dy, dz, velocity, 0.0F);

                        level.addFreshEntity(coin);
                    }


                }


                int power = data.isFlaring(Metal.IRON) ? 16 : 8;
                if(data.isEnhanced()) {
                    power = 64;
                }

                List<Entity> entityList = level.getEntitiesOfClass(Entity.class, player.getBoundingBox().inflate(power));

                for(Entity entity: entityList) {
                    if(MetalUtil.isEntityMetal(entity) && !entity.equals(player) && !(entity instanceof CoinProjectileEntity coinEntity && (coinEntity.getOwner() == null || coinEntity.getOwner().equals(player)))) {
                        Vec3 move = Vec3.atCenterOf(player.blockPosition()).subtract(Vec3.atCenterOf(entity.blockPosition())).multiply(-0.5, -0.5, -0.5);
                        entity.setDeltaMovement(move);
                        if(entity instanceof CoinProjectileEntity coin) {
//                            coin.moveTo(coin.getOnPos().relative(Direction.Axis.Y, 1), 0.0F, 0.0F);
                            coin.shoot(move.x, move.y, move.z, 2.5F, 0.0F);
                        }
                    }
                }

            });

        });
        return true;
    }

}
