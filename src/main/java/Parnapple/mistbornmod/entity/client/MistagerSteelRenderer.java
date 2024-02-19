package Parnapple.mistbornmod.entity.client;

import Parnapple.mistbornmod.MistbornBaseMod;
import Parnapple.mistbornmod.entity.custom.MistagerSteelEntity;
import Parnapple.mistbornmod.entity.custom.MistagerTinEntity;
import net.minecraft.client.model.IllagerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.IllagerRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;

public class MistagerSteelRenderer extends IllagerRenderer<MistagerSteelEntity> {
    private static final ResourceLocation MISTAGER_STEEL = new ResourceLocation(MistbornBaseMod.MOD_ID, "textures/entity/mistager_steel.png");

    public MistagerSteelRenderer(EntityRendererProvider.Context p_174354_) {
        super(p_174354_, new IllagerModel<>(p_174354_.bakeLayer(ModelLayers.PILLAGER)), 0.5F);
        this.addLayer(new ItemInHandLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(MistagerSteelEntity pEntity) {
        return MISTAGER_STEEL;
    }
}
