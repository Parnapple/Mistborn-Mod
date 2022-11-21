package Parnapple.mistbornmod.screen;

import Parnapple.mistbornmod.network.C2SBurnUpdatePacket;
import Parnapple.mistbornmod.network.ModPackets;
import Parnapple.mistbornmod.util.Metal;
import Parnapple.mistbornmod.util.keys.ModKeyMappings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;

public class AllomancyScreen extends Screen {
    final Minecraft minecraft;

    public AllomancyScreen() {
        super(new TextComponent("allomancy_gui"));
        this.minecraft = Minecraft.getInstance();
    }

    @Override
    protected void init() {
        super.init();

        for(Metal mt: Metal.values()) {
            ResourceLocation texture = new ResourceLocation("mistbornmod","textures/gui/buttons/"+ mt.getName() +"_button.png");
            int value = mt.ordinal() + 1;
            int centerX = width/2;
            int centerY = height/2;
            int x, y;

            if(value % 4 == 1) {
                x = centerX - 66;
            } else if(value % 4 == 2) {
                x = centerX - 132;
            } else if(value % 4 == 3) {
                x = centerX + 2;
            } else {
                x = centerX + 68;
            }

            if(value / 4.0 <= 1) {
                y = centerY - 68;
            } else if(value / 4.0 <= 2) {
                y = centerY - 34;
            } else if(value / 4.0 <= 3) {
                y = centerY + 2;
            } else {
                y = centerY + 36;
            }

            ImageButton button = new ImageButton(x, y, 64, 32,0, 0,32,
                    texture, 64, 64, btn -> ModPackets.sendToServer(new C2SBurnUpdatePacket(mt)),
                    new TranslatableComponent("mistbornmod.metals." + mt.getName()));

            this.addRenderableWidget(button);
        }

    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public boolean keyReleased(int pKeyCode, int pScanCode, int pModifiers) {
        if(ModKeyMappings.burnKeyMapping.matches(pKeyCode, pScanCode)) {
            this.minecraft.setScreen(null);
            this.minecraft.mouseHandler.grabMouse();
            return true;
        }

        return super.keyReleased(pKeyCode, pScanCode, pModifiers);
    }
}
