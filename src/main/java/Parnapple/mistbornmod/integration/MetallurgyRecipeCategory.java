package Parnapple.mistbornmod.integration;

import Parnapple.mistbornmod.MistbornBaseMod;
import Parnapple.mistbornmod.block.ModBlocks;
import Parnapple.mistbornmod.crafting.MetallurgyRecipe;
import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MetallurgyRecipeCategory implements IRecipeCategory<MetallurgyRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(MistbornBaseMod.MOD_ID, "metallurgy_recipe");
    public final static ResourceLocation TEXTURE =
            new ResourceLocation(MistbornBaseMod.MOD_ID, "textures/gui/container/metallurgy_furnace.png");

    private final IDrawable background;
    private final IDrawable icon;
    protected final IDrawableAnimated arrow;
    protected final IDrawable heatIndicator;


    public MetallurgyRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 120);
        this.icon = helper.createDrawableIngredient(new ItemStack(ModBlocks.METALLURGY_FURNACE_BLOCK.get()));

        this.arrow = helper.drawableBuilder(TEXTURE,  176, 14, 24, 17)
                .buildAnimated(400, IDrawableAnimated.StartDirection.LEFT, false);

        heatIndicator = helper.drawableBuilder(TEXTURE, 176, 0, 14, 14)
                .buildAnimated(50, IDrawableAnimated.StartDirection.TOP, true);


    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends MetallurgyRecipe> getRecipeClass() {
        return MetallurgyRecipe.class;
    }

    @Override
    public Component getTitle() {
        return new TextComponent("Metallurgy Furnace");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setIngredients(MetallurgyRecipe recipe, IIngredients ingredients) {
        List<Ingredient> inputs = new ArrayList<>(recipe.getIngredients());

        ingredients.setInputIngredients(inputs);
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getResultItem());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, MetallurgyRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup itemStacks = recipeLayout.getItemStacks();
        NonNullList<Ingredient> recipeIngredients = recipe.getIngredients();

        int borderSlotSize = 18;
        for (int row = 0; row < 3; ++row) {
            for (int column = 0; column < 3; ++column) {
                int inputIndex = row * 3 + column;
                if (inputIndex < recipeIngredients.size()) {
                    itemStacks.init(inputIndex, true, column * borderSlotSize + 37, row * borderSlotSize + 23);
                    itemStacks.set(inputIndex, Arrays.asList(recipeIngredients.get(inputIndex).getItems()));
                }
            }
        }

        itemStacks.init(10, false, 115, 77);
        itemStacks.set(10, recipe.getResultItem());


    }

    @Override
    public void draw(MetallurgyRecipe recipe, PoseStack stack, double mouseX, double mouseY) {
        arrow.draw(stack, 80, 78);
        heatIndicator.draw(stack, 57, 79);
    }
}
