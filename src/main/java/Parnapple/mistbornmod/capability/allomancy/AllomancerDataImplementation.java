package Parnapple.mistbornmod.capability.allomancy;

import Parnapple.mistbornmod.util.Metal;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import java.util.Arrays;

public class AllomancerDataImplementation implements IAllomancerData {

    private boolean[] powers;
    private boolean[] burning;
    private int[] stores;
    private BlockPos spawnPos;
    private String spawnDim;
    private BlockPos deathPos;
    private String deathDim;

    public AllomancerDataImplementation() {
        int powerCount = Metal.values().length;
        this.powers = new boolean[powerCount];
        this.burning = new boolean[powerCount];
        this.stores = new int[powerCount];
        Arrays.fill(this.powers, false);
        Arrays.fill(this.burning, false);
        Arrays.fill(this.stores, 0);

        this.spawnPos = null;
        this.deathPos = null;
    }

    @Override
    public boolean hasPower(Metal metal) {
        return this.powers[metal.getIndex()];
    }

    @Override
    public boolean isBurning(Metal metal) {
        return this.burning[metal.getIndex()];
    }

    @Override
    public boolean toggleBurn(Metal metal) {
        this.burning[metal.getIndex()] = !this.burning[metal.getIndex()];
        return isBurning(metal);
    }

    @Override
    public void stopAllBurning() {
        Arrays.fill(this.burning, false);
    }

    @Override
    public void setStore(Metal metal, int amount) {
        this.stores[metal.getIndex()] = amount;
        if(this.stores[metal.getIndex()] < 0) {
            this.stores[metal.getIndex()] = 0;
        }
    }

    public int getStore(Metal metal) {
        return this.stores[metal.getIndex()];
    }

    @Override
    public boolean isAllomancer() {
        for(boolean metal: this.powers) {
            if(metal)
                return true;
        }

        return false;
    }

    @Override
    public Metal[] getPowers() {
        return Arrays.stream(Metal.values()).filter(this::hasPower).toArray(Metal[]::new);
    }

    @Override
    public void givePower(Metal metal) {
        this.powers[metal.getIndex()] = true;
    }

    @Override
    public void giveAllPowers() {
        Arrays.fill(this.powers, true);
    }

    @Override
    public void removePower(Metal metal) {
        this.powers[metal.getIndex()] = false;
    }

    @Override
    public void removeAllPowers() {
        Arrays.fill(this.powers, false);
    }

    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();

        CompoundTag powers = new CompoundTag();
        for(Metal metal: Metal.values()) {
            powers.putBoolean(metal.getName(), this.hasPower(metal));
        }

        CompoundTag burning = new CompoundTag();
        for(Metal metal: Metal.values()) {
            burning.putBoolean(metal.getName(), this.isBurning(metal));
        }

        CompoundTag stores = new CompoundTag();
        for(Metal metal: Metal.values()) {
            stores.putInt(metal.getName(), this.getStore(metal));
        }

        CompoundTag spawnPos = new CompoundTag();
        if(this.spawnPos != null) {
            spawnPos.putInt("x", this.spawnPos.getX());
            spawnPos.putInt("y", this.spawnPos.getY());
            spawnPos.putInt("z", this.spawnPos.getZ());
            spawnPos.putString("dim", this.spawnDim);
        }

        CompoundTag deathPos = new CompoundTag();
        if(this.deathPos != null) {
            spawnPos.putInt("x", this.deathPos.getX());
            spawnPos.putInt("y", this.deathPos.getY());
            spawnPos.putInt("z", this.deathPos.getZ());
            spawnPos.putString("dim", this.deathDim);
        }

        tag.put("powers", powers);
        tag.put("burning", burning);
        tag.put("stores", stores);
        tag.put("spawn", spawnPos);
        tag.put("death", deathPos);
        return tag;
    }

    public void deserializeNBT(CompoundTag tag) {
        CompoundTag powers = tag.getCompound("powers");
        for(Metal metal: Metal.values()) {
            if(powers.getBoolean(metal.getName())) {
                this.givePower(metal);
            } else {
                this.removePower(metal);
            }
        }

        CompoundTag burning = tag.getCompound("burning");
        this.stopAllBurning();
        for(Metal metal: Metal.values()) {
            if(burning.getBoolean(metal.getName())) {
                this.toggleBurn(metal);
            }
        }

        CompoundTag stores = tag.getCompound("stores");
        for(Metal metal: Metal.values()) {
            this.setStore(metal, stores.getInt(metal.getName()));
        }

        CompoundTag spawnPos = tag.getCompound("spawn");
        if(spawnPos.contains("x")) {
            this.spawnPos = new BlockPos(spawnPos.getInt("x"), spawnPos.getInt("y"), spawnPos.getInt("z"));
            this.spawnDim = spawnPos.getString("dim");
        }

        CompoundTag deathPos = tag.getCompound("death");
        if(deathPos.contains("x")) {
            this.deathPos = new BlockPos(deathPos.getInt("x"), deathPos.getInt("y"), deathPos.getInt("z"));
            this.deathDim = deathPos.getString("dim");
        }
    }

    public void setSpawnPos(BlockPos pos, String dimension) {
        this.spawnPos = pos;
        this.spawnDim = dimension;
    }

    public BlockPos getSpawnPos() {
        return this.spawnPos;
    }

    public ResourceKey<Level> getSpawnDimension() {
        if (this.spawnDim == null) {
            return null;
        }
        return ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(this.spawnDim));
    }

    public void setDeathPos(BlockPos pos, String dimension) {
        this.deathPos = pos;
        this.deathDim = dimension;
    }

    public BlockPos getDeathPos() {
        return this.deathPos;
    }

    public ResourceKey<Level> getDeathDimension() {
        if (this.deathPos == null) {
            return null;
        }
        return ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(this.deathDim));
    }


}
