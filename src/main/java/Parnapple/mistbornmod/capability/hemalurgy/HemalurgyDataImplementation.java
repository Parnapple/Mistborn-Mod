package Parnapple.mistbornmod.capability.hemalurgy;

import Parnapple.mistbornmod.util.Metal;
import net.minecraft.nbt.CompoundTag;

import java.util.Arrays;

public class HemalurgyDataImplementation implements IHemalurgyData {

    private int spikeCount;

    public HemalurgyDataImplementation() {
       this.spikeCount = 0;
    }

    @Override
    public void setSpikeCount(int count) {
        this.spikeCount = count;
    }

    public int getSpikeCount() {
        return spikeCount;
    }

    @Override
    public boolean isSpiked() {
        return this.spikeCount > 0;
    }

    public CompoundTag serializeNBT() {
        final CompoundTag tag = new CompoundTag();
        tag.putInt("spikes", this.spikeCount);

        return tag;
    }

    public void deserializeNBT(CompoundTag tag) {
        this.spikeCount = tag.getInt("spikes");
    }
}
