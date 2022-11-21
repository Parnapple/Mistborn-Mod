package Parnapple.mistbornmod.network;

import Parnapple.mistbornmod.capability.ModCapabilities;
import Parnapple.mistbornmod.util.Metal;
import Parnapple.mistbornmod.util.MetalUtil;
import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class C2SPushPullPacket {
    public enum Dir{
        UP, DOWN, LEFT, RIGHT, FORWARD, BACKWARD
    }

    private final Dir direction;
    private final Metal burning;

    public C2SPushPullPacket(Dir direction, Metal burning) {
        this.direction = direction;
        this.burning = burning;
    }

    public C2SPushPullPacket(FriendlyByteBuf buf) {
        this(buf.readEnum(Dir.class), buf.readEnum(Metal.class));
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeEnum(this.direction);
        buf.writeEnum(this.burning);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE SERVER
            ServerPlayer player = context.getSender();
            float power = 0.1f;
            int range = 8;
            if(this.direction.equals(Dir.UP) &&
                    ((metalNearPlayer(player, range, Dir.DOWN) && this.burning.equals(Metal.STEEL)) || (metalNearPlayer(player, range, Dir.UP) && this.burning.equals(Metal.IRON)))) {
                move(player, new Vec3(0, power, 0));
            }
            else if(this.direction.equals(Dir.DOWN) &&
                    ((metalNearPlayer(player, range, Dir.UP) && this.burning.equals(Metal.STEEL)) || (metalNearPlayer(player, range, Dir.DOWN) && this.burning.equals(Metal.IRON)))) {
                move(player, new Vec3(0, -power, 0));
            }
            else if(this.direction.equals(Dir.LEFT) &&
                    ((metalNearPlayer(player, range, Dir.RIGHT) && this.burning.equals(Metal.STEEL)) || (metalNearPlayer(player, range, Dir.LEFT) && this.burning.equals(Metal.IRON)))) {
                double x = player.getForward().x;
                double z = player.getForward().z;
                move(player, player.getForward().with(Direction.Axis.Z, -power * x).with(Direction.Axis.X, z * power));
            }
            else if(this.direction.equals(Dir.RIGHT) &&
                    ((metalNearPlayer(player, range, Dir.LEFT) && this.burning.equals(Metal.STEEL)) || (metalNearPlayer(player, range, Dir.RIGHT) && this.burning.equals(Metal.IRON)))) {
                double x = player.getForward().x;
                double z = player.getForward().z;
                move(player, player.getForward().with(Direction.Axis.Z, x * power).with(Direction.Axis.X, -power * z));
            }
            else if(this.direction.equals(Dir.FORWARD) &&
                    ((metalNearPlayer(player, range, Dir.BACKWARD) && this.burning.equals(Metal.STEEL)) || (metalNearPlayer(player, range, Dir.FORWARD) && this.burning.equals(Metal.IRON)))) {
                move(player, player.getForward().multiply(power, 0, power));
            }
            else if(this.direction.equals(Dir.BACKWARD) &&
                    ((metalNearPlayer(player, range, Dir.FORWARD) && this.burning.equals(Metal.STEEL)) || (metalNearPlayer(player, range, Dir.BACKWARD) && this.burning.equals(Metal.IRON)))) {
                move(player, player.getForward().reverse().multiply(power, 0, power));
            }

            player.move(MoverType.PLAYER, player.getDeltaMovement());

            player.fallDistance  = 0;

        });
        return true;
    }

    public static void move(ServerPlayer player, Vec3 movement) {
        player.setDeltaMovement(movement.add(player.getDeltaMovement()));
        player.hurtMarked = true;
        player.fallDistance  = 0;
    }

    private static boolean metalNearPlayer(ServerPlayer player, int range, Dir direction) {

        Level level = player.level;
        AABB boundingBox = player.getBoundingBox().inflate(range);
        Vec3 move;
        if(direction.equals(Dir.DOWN)) {
            move = new Vec3(0, -range, 0);
        } else if(direction.equals(Dir.UP)) {
            move = new Vec3(0, range, 0);
        }  else if(direction.equals(Dir.FORWARD)) {
            move = player.getForward().multiply(range, 0, range);
        } else if(direction.equals(Dir.BACKWARD)) {
            move = player.getForward().multiply(-range, 0, -range);
        } else if(direction.equals(Dir.RIGHT)) {
            double x = player.getForward().x;
            double z = player.getForward().z;
            move = player.getForward().with(Direction.Axis.Z, x * range).with(Direction.Axis.X, -range * z);
        } else if(direction.equals(Dir.LEFT)) {
            double x = player.getForward().x;
            double z = player.getForward().z;
            move = player.getForward().with(Direction.Axis.Z, -x * range).with(Direction.Axis.X, range * z);
        } else {
            move = new Vec3(0, 0, 0);
        }

        boundingBox = boundingBox.move(move);

        return level.getBlockStates(boundingBox).filter(state -> MetalUtil.isBlockMetal(state.getBlock())).toArray().length > 0;
    }

}
