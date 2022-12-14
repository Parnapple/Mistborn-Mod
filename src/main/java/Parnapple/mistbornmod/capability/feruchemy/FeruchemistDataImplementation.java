package Parnapple.mistbornmod.capability.feruchemy;

import Parnapple.mistbornmod.util.Metal;
import net.minecraft.nbt.CompoundTag;

import java.util.Arrays;

public class FeruchemistDataImplementation implements IFeruchemistData {

    private boolean[] powers;
    private boolean everFeruchemist;

    public FeruchemistDataImplementation() {
        int powerCount = Metal.values().length;
        this.powers = new boolean[powerCount];
        this.everFeruchemist = false;
        Arrays.fill(this.powers, false);
    }

    public boolean hasPower(Metal metal) {
        return this.powers[metal.getIndex()];
    }

    public boolean isFeruchemist() {
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
        this.everFeruchemist = true;
    }

    @Override
    public void giveAllPowers() {
        Arrays.fill(this.powers, true);
        this.everFeruchemist = true;
    }

    @Override
    public void removePower(Metal metal) {
        this.powers[metal.getIndex()] = false;
    }

    @Override
    public boolean everFeruchemist() {
        return this.everFeruchemist;
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

        tag.putBoolean("ever", everFeruchemist);

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

        everFeruchemist = tag.getBoolean("ever");

    }
}
