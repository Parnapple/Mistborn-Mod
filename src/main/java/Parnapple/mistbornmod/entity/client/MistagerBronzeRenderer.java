package Parnapple.mistbornmod.entity.client;

import Parnapple.mistbornmod.MistbornBaseMod;
import Parnapple.mistbornmod.entity.custom.MistagerBronzeEntity;
import Parnapple.mistbornmod.entity.custom.MistagerCopperEntity;
import net.minecraft.client.model.IllagerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.IllagerRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;

public class MistagerBronzeRenderer extends IllagerRenderer<MistagerBronzeEntity>{
    private static final ResourceLocation MISTAGER_BRONZE = new ResourceLocation(MistbornBaseMod.MOD_ID, "textures/entity/mistager_bronze.png");

    public MistagerBronzeRenderer(EntityRendererProvider.Context p_174439_) {
        super(p_174439_, new IllagerModel<>(p_174439_.bakeLayer(ModelLayers.PILLAGER)), 0.5F);
        this.addLayer(new ItemInHandLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(MistagerBronzeEntity pEntity) {
        return MISTAGER_BRONZE;
    }

}
