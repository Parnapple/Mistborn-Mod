package Parnapple.mistbornmod.event;

import Parnapple.mistbornmod.crafting.MetallurgyRecipe;
import Parnapple.mistbornmod.MistbornBaseMod;
import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MistbornBaseMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerRecipeTypes(final RegistryEvent.Register<RecipeSerializer<?>> event) {
        Registry.register(Registry.RECIPE_TYPE, MetallurgyRecipe.Type.ID, MetallurgyRecipe.Type.INSTANCE);
    }
}
