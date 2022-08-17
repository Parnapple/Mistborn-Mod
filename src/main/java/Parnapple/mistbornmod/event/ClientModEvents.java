package Parnapple.mistbornmod.event;

import Parnapple.mistbornmod.block.container.ModContainers;
import Parnapple.mistbornmod.network.C2SBurnUpdatePacket;
import Parnapple.mistbornmod.network.ModPackets;
import Parnapple.mistbornmod.screen.MetallurgyFurnaceScreen;
import Parnapple.mistbornmod.MistbornBaseMod;
import Parnapple.mistbornmod.util.keys.ModKeyMappings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientModEvents {
    @Mod.EventBusSubscriber(modid = MistbornBaseMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        @SubscribeEvent
        public static void onKeyInput(InputEvent.KeyInputEvent event) {
            if(ModKeyMappings.burnKeyMapping.consumeClick()) {
                Player player = Minecraft.getInstance().player;
                player.displayClientMessage(new TextComponent("Allomancy is not done yet"), true);
                ModPackets.sendToServer(new C2SBurnUpdatePacket());
            }
        }
    }

    @Mod.EventBusSubscriber(modid = MistbornBaseMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void clientSetup(FMLClientSetupEvent event)
        {
            MenuScreens.register(ModContainers.METALLURGY_FURNACE_CONTAINER.get(), MetallurgyFurnaceScreen::new);
            ModKeyMappings.init();
        }
    }
}
