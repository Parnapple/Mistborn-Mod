package Parnapple.mistbornmod.block.container;

import Parnapple.mistbornmod.MistbornBaseMod;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.fmllegacy.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainers {

    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS,
            MistbornBaseMod.MOD_ID);

    public static final RegistryObject<MenuType<MetallurgyFurnaceContainer>> METALLURGY_FURNACE_CONTAINER =
            registerMenuType(MetallurgyFurnaceContainer::new, "metallurgy_furnace_container");

    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory,
                                                                                                 String name) {
        return CONTAINERS.register(name, () -> IForgeContainerType.create(factory));
    }


    public static void register(IEventBus eventBus) {
        CONTAINERS.register(eventBus);
    }
}
