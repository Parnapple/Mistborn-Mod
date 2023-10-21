package Parnapple.mistbornmod.event;

import Parnapple.mistbornmod.crafting.MetallurgyRecipe;
import Parnapple.mistbornmod.MistbornBaseMod;
import Parnapple.mistbornmod.entity.ModEntities;
import Parnapple.mistbornmod.entity.custom.MistagerPewterEntity;
import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MistbornBaseMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerRecipeTypes(final RegistryEvent.Register<RecipeSerializer<?>> event) {
        Registry.register(Registry.RECIPE_TYPE, MetallurgyRecipe.Type.ID, MetallurgyRecipe.Type.INSTANCE);
    }

    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(ModEntities.MISTAGER_PEWTER.get(), MistagerPewterEntity.setAttributes());
    }
}
