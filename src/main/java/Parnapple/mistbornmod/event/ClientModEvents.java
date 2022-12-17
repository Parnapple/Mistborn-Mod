package Parnapple.mistbornmod.event;

import Parnapple.mistbornmod.block.container.ModContainers;
import Parnapple.mistbornmod.capability.ModCapabilities;
import Parnapple.mistbornmod.client.AllomancyHudOverlay;
import Parnapple.mistbornmod.client.ClientAllomancyData;
import Parnapple.mistbornmod.network.C2SBurnUpdatePacket;
import Parnapple.mistbornmod.network.C2SPushPullPacket;
import Parnapple.mistbornmod.network.ModPackets;
import Parnapple.mistbornmod.network.S2CSyncAllomancerDataPacket;
import Parnapple.mistbornmod.screen.AllomancyScreen;
import Parnapple.mistbornmod.screen.MetallurgyFurnaceScreen;
import Parnapple.mistbornmod.MistbornBaseMod;
import Parnapple.mistbornmod.util.Metal;
import Parnapple.mistbornmod.util.keys.ModKeyMappings;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.gui.OverlayRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import static net.minecraftforge.client.gui.ForgeIngameGui.HOTBAR_ELEMENT;

public class ClientModEvents {
    @Mod.EventBusSubscriber(modid = MistbornBaseMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        private static final Minecraft mc = Minecraft.getInstance();
        @SubscribeEvent
        public static void onKeyInput(InputEvent.KeyInputEvent event) {
            Player player = mc.player;
            if(ModKeyMappings.burnKeyMapping.consumeClick()) {
                if(ClientAllomancyData.getPowers().length > 1) {
                    mc.setScreen(new AllomancyScreen());
                } else {
                    ModPackets.sendToServer(new C2SBurnUpdatePacket(ClientAllomancyData.getPowers()[0], Screen.hasControlDown() || Screen.hasAltDown()));
                }
            }
            if(ClientAllomancyData.isBurning(Metal.IRON) || ClientAllomancyData.isBurning(Metal.STEEL)) {
                Metal burning = ClientAllomancyData.isBurning(Metal.STEEL) ? Metal.STEEL : Metal.IRON;
                if(mc.options.keyUp.isDown()) {
                    ModPackets.sendToServer(new C2SPushPullPacket(C2SPushPullPacket.Dir.FORWARD, burning));
                }
                if(mc.options.keyDown.isDown()) {
                    ModPackets.sendToServer(new C2SPushPullPacket(C2SPushPullPacket.Dir.BACKWARD, burning));
                }
                if(mc.options.keyLeft.isDown()) {
                    ModPackets.sendToServer(new C2SPushPullPacket(C2SPushPullPacket.Dir.LEFT, burning));
                }
                if(mc.options.keyRight.isDown()) {
                    ModPackets.sendToServer(new C2SPushPullPacket(C2SPushPullPacket.Dir.RIGHT, burning));
                }
                if(mc.options.keyShift.isDown()) {
                    ModPackets.sendToServer(new C2SPushPullPacket(C2SPushPullPacket.Dir.DOWN, burning));
                }
                if(mc.options.keyJump.isDown()) {
                    ModPackets.sendToServer(new C2SPushPullPacket(C2SPushPullPacket.Dir.UP, burning));
                }
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
            OverlayRegistry.registerOverlayAbove(HOTBAR_ELEMENT, "allomancy", AllomancyHudOverlay.ALLOMANCY_HUD);
        }


    }
}
