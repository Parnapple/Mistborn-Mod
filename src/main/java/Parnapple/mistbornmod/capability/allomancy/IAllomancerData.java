package Parnapple.mistbornmod.capability.allomancy;

import Parnapple.mistbornmod.util.Metal;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

public interface IAllomancerData {

    boolean hasPower(Metal metal);

    boolean isBurning(Metal metal);

    boolean toggleBurn(Metal metal);

    void stopAllBurning();

    void setStore(Metal metal, int amount);

    int getStore(Metal metal);

    boolean isAllomancer();

    Metal[] getPowers();

    void givePower(Metal metal);

    void giveAllPowers();

    void removePower(Metal metal);

    void removeAllPowers();

    void setSpawnPos(BlockPos pos, String dimension);

    BlockPos getSpawnPos();

    ResourceKey<Level> getSpawnDimension();

    void setDeathPos(BlockPos pos, String dimension);

    BlockPos getDeathPos();

    ResourceKey<Level> getDeathDimension();

}
