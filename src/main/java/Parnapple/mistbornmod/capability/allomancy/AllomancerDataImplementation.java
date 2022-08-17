package Parnapple.mistbornmod.capability.allomancy;

import Parnapple.mistbornmod.util.Metal;
import net.minecraft.nbt.CompoundTag;

import java.util.Arrays;

public class AllomancerDataImplementation implements IAllomancerData {

    private boolean[] powers;
    private boolean[] burning;

    public AllomancerDataImplementation() {
        int powerCount = Metal.values().length;
        this.powers = new boolean[powerCount];
        this.burning = new boolean[powerCount];
        Arrays.fill(this.powers, false);
        Arrays.fill(this.burning, false);
    }
    public boolean hasPower(Metal metal) {
        return this.powers[metal.getIndex()];
    }

    public boolean isBurning(Metal metal) {
        return this.burning[metal.getIndex()];
    }

    public boolean toggleBurn(Metal metal) {
        this.burning[metal.getIndex()] = !this.burning[metal.getIndex()];
        return isBurning(metal);
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
        final CompoundTag tag = new CompoundTag();
        for(Metal metal: Metal.values()) {
            tag.putBoolean(metal.getName(), this.hasPower(metal));
        }

        return tag;
    }

    public void deserializeNBT(CompoundTag tag) {
        for(Metal metal: Metal.values()) {
            if(tag.getBoolean(metal.getName())) {
                this.givePower(metal);
            } else {
                this.removePower(metal);
            }
        }
    }
}
