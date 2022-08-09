package Parnapple.mistbornmod.crafting;

import Parnapple.mistbornmod.MistbornBaseMod;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModRecipeSerializers {
    //not sure if im supposed to do anything else...
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, MistbornBaseMod.MOD_ID);

    public static final RegistryObject<RecipeSerializer<MetallurgyRecipe>> METALLURGY_SERIALIZER =
            RECIPE_SERIALIZERS.register("metallurgy_recipe", () -> MetallurgyRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        RECIPE_SERIALIZERS.register(eventBus);
    }

}
