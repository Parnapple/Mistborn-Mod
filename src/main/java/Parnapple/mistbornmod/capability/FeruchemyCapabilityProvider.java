package Parnapple.mistbornmod.capability;

import Parnapple.mistbornmod.MistbornBaseMod;


import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nullable;
import javax.annotation.Nonnull;

public class FeruchemyCapabilityProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static final ResourceLocation IDENTIFIER = new ResourceLocation(MistbornBaseMod.MOD_ID, "feruchemy");

    private final FeruchemistDataImplementation data = new FeruchemistDataImplementation();
    private final LazyOptional<IFeruchemistData> optionalData = LazyOptional.of(() -> data);

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return ModCapabilities.FERUCHEMY_INSTANCE.orEmpty(cap, this.optionalData);
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
