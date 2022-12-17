package Parnapple.mistbornmod.capability;

import Parnapple.mistbornmod.capability.allomancy.IAllomancerData;
import Parnapple.mistbornmod.capability.feruchemy.IFeruchemistData;
import Parnapple.mistbornmod.capability.hemalurgy.IHemalurgyData;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;

public class ModCapabilities {

    public static final Capability<IFeruchemistData> FERUCHEMY_INSTANCE = CapabilityManager.get(new CapabilityToken<>() {});

    public static final Capability<IAllomancerData> ALLOMANCY_INSTANCE = CapabilityManager.get(new CapabilityToken<>() {});

    public static final Capability<IHemalurgyData> HEMALURGY_INSTANCE = CapabilityManager.get(new CapabilityToken<>() {});

    public static void register(RegisterCapabilitiesEvent event) {
        event.register(IFeruchemistData.class);
    }

}
