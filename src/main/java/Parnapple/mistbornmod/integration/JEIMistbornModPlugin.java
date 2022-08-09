package Parnapple.mistbornmod.integration;

import Parnapple.mistbornmod.MistbornBaseMod;
import Parnapple.mistbornmod.block.ModBlocks;
import Parnapple.mistbornmod.crafting.MetallurgyRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.List;
import java.util.stream.Collectors;

@JeiPlugin
public class JEIMistbornModPlugin implements IModPlugin {
    private static final Minecraft MC = Minecraft.getInstance();

    private static List<Recipe<?>> findRecipesByType(RecipeType<?> type) {
        return MC.level
                .getRecipeManager()
                .getRecipes()
                .stream()
                .filter(r -> r.getType() == type)
                .collect(Collectors.toList());
    }



    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(MistbornBaseMod.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new
                MetallurgyRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(findRecipesByType(MetallurgyRecipe. Type.INSTANCE), MetallurgyRecipeCategory.UID);
    }
    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.METALLURGY_FURNACE_BLOCK.get()),
                MetallurgyRecipeCategory.UID);
    }


}
