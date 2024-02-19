package Parnapple.mistbornmod.event;

import Parnapple.mistbornmod.crafting.MetallurgyRecipe;
import Parnapple.mistbornmod.MistbornBaseMod;
import Parnapple.mistbornmod.entity.ModEntities;
import Parnapple.mistbornmod.entity.custom.*;
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
        event.put(ModEntities.MISTAGER_TIN.get(), MistagerTinEntity.setAttributes());
        event.put(ModEntities.MISTAGER_BRASS.get(), MistagerBrassEntity.setAttributes());
        event.put(ModEntities.MISTAGER_ZINC.get(), MistagerZincEntity.setAttributes());
        event.put(ModEntities.MISTAGER_CHROMIUM.get(), MistagerChromiumEntity.setAttributes());
        event.put(ModEntities.MISTAGER_COPPER.get(), MistagerCopperEntity.setAttributes());
        event.put(ModEntities.MISTAGER_BRONZE.get(), MistagerBronzeEntity.setAttributes());
        event.put(ModEntities.MISTAGER_IRON.get(), MistagerIronEntity.setAttributes());
        event.put(ModEntities.MISTAGER_STEEL.get(), MistagerSteelEntity.setAttributes());
    }
}
