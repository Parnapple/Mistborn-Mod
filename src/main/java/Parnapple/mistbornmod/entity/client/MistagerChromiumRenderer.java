package Parnapple.mistbornmod.entity.client;

import Parnapple.mistbornmod.MistbornBaseMod;
import Parnapple.mistbornmod.entity.custom.MistagerChromiumEntity;
import Parnapple.mistbornmod.entity.custom.MistagerPewterEntity;
import net.minecraft.client.model.IllagerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.IllagerRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;

public class MistagerChromiumRenderer extends IllagerRenderer<MistagerChromiumEntity>{
    private static final ResourceLocation MISTAGER_CHROMIUM = new ResourceLocation(MistbornBaseMod.MOD_ID, "textures/entity/mistager_chromium.png");

    public MistagerChromiumRenderer(EntityRendererProvider.Context p_174439_) {
        super(p_174439_, new IllagerModel<>(p_174439_.bakeLayer(ModelLayers.PILLAGER)), 0.5F);
        this.addLayer(new ItemInHandLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(MistagerChromiumEntity pEntity) {
        return MISTAGER_CHROMIUM;
    }

}
