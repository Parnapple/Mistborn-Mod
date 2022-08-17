package Parnapple.mistbornmod.capability.allomancy;

import Parnapple.mistbornmod.MistbornBaseMod;
import Parnapple.mistbornmod.capability.ModCapabilities;
import Parnapple.mistbornmod.capability.feruchemy.FeruchemistDataImplementation;
import Parnapple.mistbornmod.capability.feruchemy.IFeruchemistData;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class AllomancyCapabilityProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static final ResourceLocation IDENTIFIER = new ResourceLocation(MistbornBaseMod.MOD_ID, "allomancy");

    private final AllomancerDataImplementation data = new AllomancerDataImplementation();
    private final LazyOptional<IAllomancerData> optionalData = LazyOptional.of(() -> data);

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return ModCapabilities.ALLOMANCY_INSTANCE.orEmpty(cap, this.optionalData);
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
