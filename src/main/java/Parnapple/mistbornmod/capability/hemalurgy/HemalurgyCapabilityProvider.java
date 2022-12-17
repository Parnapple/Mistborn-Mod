package Parnapple.mistbornmod.capability.hemalurgy;

import Parnapple.mistbornmod.MistbornBaseMod;
import Parnapple.mistbornmod.capability.ModCapabilities;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class HemalurgyCapabilityProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static final ResourceLocation IDENTIFIER = new ResourceLocation(MistbornBaseMod.MOD_ID, "hemalurgy");

    private final HemalurgyDataImplementation data = new HemalurgyDataImplementation();
    private final LazyOptional<IHemalurgyData> optionalData = LazyOptional.of(() -> data);

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return ModCapabilities.HEMALURGY_INSTANCE.orEmpty(cap, this.optionalData);
    }

    void invalidate() {
        this.optionalData.invalidate();
    }

    @Override
    public CompoundTag serializeNBT() {
        return this.data.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.data.deserializeNBT(nbt);
    }
}
