package Parnapple.mistbornmod.screen.button;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class PlainTextImageButton extends ImageButton {
    private final Font font;
    private final Component message;

    public PlainTextImageButton(int pX, int pY, int pWidth, int pHeight, int pXTexStart, int pYTexStart, int pYDiffTex, ResourceLocation pResourceLocation, int pTextureWidth, int pTextureHeight, Button.OnPress pOnPress, Component pMessage) {
        super(pX, pY, pWidth, pHeight, pXTexStart, pYTexStart, pYDiffTex, pResourceLocation, pTextureWidth, pTextureHeight, pOnPress, NO_TOOLTIP, pMessage);
        this.message = pMessage;
        this.font = Minecraft.getInstance().font;
    }

    @Override
    public void renderButton(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        super.renderButton(pPoseStack, pMouseX, pMouseY, pPartialTick);
        int tx = this.x + (this.width/2) - (this.font.width(this.message.getString())/2);
        int ty = this.y + (this.height/2) - (this.font.lineHeight/2);
        drawString(pPoseStack, this.font, this.message, tx, ty, 16777215 | Mth.ceil(this.alpha * 255.0F) << 24);
    }
}
