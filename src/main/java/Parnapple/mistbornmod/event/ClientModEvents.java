package Parnapple.mistbornmod.event;

import Parnapple.mistbornmod.block.container.ModContainers;
import Parnapple.mistbornmod.screen.MetallurgyFurnaceScreen;
import Parnapple.mistbornmod.MistbornBaseMod;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = MistbornBaseMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)

public class ClientModEvents {
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event)
    {
        MenuScreens.register(ModContainers.METALLURGY_FURNACE_CONTAINER.get(), MetallurgyFurnaceScreen::new);
    }
}
