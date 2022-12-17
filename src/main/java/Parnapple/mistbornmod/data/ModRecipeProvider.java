package Parnapple.mistbornmod.data;

import Parnapple.mistbornmod.block.ModBlocks;
import Parnapple.mistbornmod.item.ModItems;
import Parnapple.mistbornmod.util.ModTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> p_176532_) {
        ShapedRecipeBuilder.shaped(ModBlocks.ALUMINUM_BLOCK.get())
                .define('X', ModTags.Items.ALUMINUM_INGOT)
                .pattern("XXX")
                .pattern("XXX")
                .pattern("XXX")
                .unlockedBy("has_material", has(ModItems.ALUMINUM_INGOT.get()))
                .save(p_176532_);
        ShapedRecipeBuilder.shaped(ModBlocks.METALLURGY_FURNACE_BLOCK.get())
                .define('S', ModTags.Items.STEEL_INGOT)
                .define('B', Items.POLISHED_BLACKSTONE)
                .define('L', Items.LAVA_BUCKET)
                .define('F', Items.BLAST_FURNACE)
                .pattern("SLS")
                .pattern("SFS")
                .pattern("BBB")
                .unlockedBy("has_material", has(ModTags.Items.STEEL_INGOT))
                .save(p_176532_);
        ShapedRecipeBuilder.shaped(ModItems.OBSIDIAN_DAGGER.get())
                .define('S', Items.STICK)
                .define('O', Items.OBSIDIAN)
                .pattern("O")
                .pattern("S")
                .unlockedBy("has_material", has(Items.OBSIDIAN))
                .save(p_176532_);
        ShapedRecipeBuilder.shaped(ModItems.GLASS_DAGGER.get())
                .define('S', Items.STICK)
                .define('G', Tags.Items.GLASS)
                .pattern("G")
                .pattern("S")
                .unlockedBy("has_material", has(Tags.Items.GLASS))
                .save(p_176532_);
        ShapedRecipeBuilder.shaped(ModItems.MISTCLOAK.get())
                .define('W', Items.GRAY_WOOL)
                .pattern("W W")
                .pattern("WWW")
                .pattern("WWW")
                .unlockedBy("has_material", has(Items.GRAY_WOOL))
                .save(p_176532_);
        ShapedRecipeBuilder.shaped(ModItems.MISTCLOAK_LEGGINGS.get())
                .define('W', Items.GRAY_WOOL)
                .pattern("WWW")
                .pattern("W W")
                .pattern("W W")
                .unlockedBy("has_material", has(Items.GRAY_WOOL))
                .save(p_176532_);
        ShapedRecipeBuilder.shaped(ModItems.STEEL_METALMIND.get())
                .define('I', ModTags.Items.STEEL_INGOT)
                .define('N', ModTags.Items.STEEL_NUGGET)
                .pattern(" IN")
                .pattern("I I")
                .pattern("NI ")
                .unlockedBy("has_material", has(ModTags.Items.STEEL_INGOT))
                .save(p_176532_);
        ShapedRecipeBuilder.shaped(ModItems.PEWTER_METALMIND.get())
                .define('I', ModTags.Items.PEWTER_INGOT)
                .define('N', ModTags.Items.PEWTER_NUGGET)
                .pattern(" IN")
                .pattern("I I")
                .pattern("NI ")
                .unlockedBy("has_material", has(ModTags.Items.PEWTER_INGOT))
                .save(p_176532_);
        ShapedRecipeBuilder.shaped(ModItems.GOLD_METALMIND.get())
                .define('I', Items.GOLD_INGOT)
                .define('N', Items.GOLD_NUGGET)
                .pattern(" IN")
                .pattern("I I")
                .pattern("NI ")
                .unlockedBy("has_material", has(Items.GOLD_INGOT))
                .save(p_176532_);
        ShapedRecipeBuilder.shaped(ModItems.ELECTRUM_METALMIND.get())
                .define('I', ModTags.Items.ELECTRUM_INGOT)
                .define('N', ModTags.Items.ELECTRUM_NUGGET)
                .pattern(" IN")
                .pattern("I I")
                .pattern("NI ")
                .unlockedBy("has_material", has(ModTags.Items.ELECTRUM_INGOT))
                .save(p_176532_);
        ShapedRecipeBuilder.shaped(ModItems.BENDALLOY_METALMIND.get())
                .define('I', ModTags.Items.BENDALLOY_INGOT)
                .define('N', ModTags.Items.BENDALLOY_NUGGET)
                .pattern(" IN")
                .pattern("I I")
                .pattern("NI ")
                .unlockedBy("has_material", has(ModTags.Items.BENDALLOY_INGOT))
                .save(p_176532_);
        ShapedRecipeBuilder.shaped(ModItems.TIN_METALMIND.get())
                .define('I', ModTags.Items.TIN_INGOT)
                .define('N', ModTags.Items.TIN_NUGGET)
                .pattern(" IN")
                .pattern("I I")
                .pattern("NI ")
                .unlockedBy("has_material", has(ModTags.Items.TIN_INGOT))
                .save(p_176532_);
        ShapedRecipeBuilder.shaped(ModItems.IRON_METALMIND.get())
                .define('I', Items.IRON_INGOT)
                .define('N', Items.IRON_NUGGET)
                .pattern(" IN")
                .pattern("I I")
                .pattern("NI ")
                .unlockedBy("has_material", has(Items.IRON_INGOT))
                .save(p_176532_);
        ShapedRecipeBuilder.shaped(ModItems.BRASS_METALMIND.get())
                .define('I', ModTags.Items.BRASS_INGOT)
                .define('N', ModTags.Items.BRASS_NUGGET)
                .pattern(" IN")
                .pattern("I I")
                .pattern("NI ")
                .unlockedBy("has_material", has(ModTags.Items.BRASS_INGOT))
                .save(p_176532_);
        ShapedRecipeBuilder.shaped(ModItems.CADMIUM_METALMIND.get())
                .define('I', ModTags.Items.CADMIUM_INGOT)
                .define('N', ModTags.Items.CADMIUM_NUGGET)
                .pattern(" IN")
                .pattern("I I")
                .pattern("NI ")
                .unlockedBy("has_material", has(ModTags.Items.CADMIUM_INGOT))
                .save(p_176532_);
        ShapedRecipeBuilder.shaped(ModItems.COPPER_METALMIND.get())
                .define('I', Items.COPPER_INGOT)
                .define('N', ModTags.Items.COPPER_NUGGET)
                .pattern(" IN")
                .pattern("I I")
                .pattern("NI ")
                .unlockedBy("has_material", has(Items.COPPER_INGOT))
                .save(p_176532_);
        ShapedRecipeBuilder.shaped(ModItems.CHROMIUM_METALMIND.get())
                .define('I', ModTags.Items.CHROMIUM_INGOT)
                .define('N', ModTags.Items.CHROMIUM_NUGGET)
                .pattern(" IN")
                .pattern("I I")
                .pattern("NI ")
                .unlockedBy("has_material", has(ModTags.Items.CHROMIUM_INGOT))
                .save(p_176532_);
        ShapedRecipeBuilder.shaped(ModItems.ALUMINUM_METALMIND.get())
                .define('I', ModTags.Items.ALUMINUM_INGOT)
                .define('N', ModTags.Items.ALUMINUM_NUGGET)
                .pattern(" IN")
                .pattern("I I")
                .pattern("NI ")
                .unlockedBy("has_material", has(ModTags.Items.ALUMINUM_INGOT))
                .save(p_176532_);
        ShapedRecipeBuilder.shaped(ModItems.ZINC_METALMIND.get())
                .define('I', ModTags.Items.ZINC_INGOT)
                .define('N', ModTags.Items.ZINC_NUGGET)
                .pattern(" IN")
                .pattern("I I")
                .pattern("NI ")
                .unlockedBy("has_material", has(ModTags.Items.ZINC_INGOT))
                .save(p_176532_);
        ShapedRecipeBuilder.shaped(ModItems.BRONZE_METALMIND.get())
                .define('I', ModTags.Items.BRONZE_INGOT)
                .define('N', ModTags.Items.BRONZE_NUGGET)
                .pattern(" IN")
                .pattern("I I")
                .pattern("NI ")
                .unlockedBy("has_material", has(ModTags.Items.BRONZE_INGOT))
                .save(p_176532_);
        ShapedRecipeBuilder.shaped(ModItems.DURALUMIN_METALMIND.get())
                .define('I', ModTags.Items.DURALUMIN_INGOT)
                .define('N', ModTags.Items.DURALUMIN_NUGGET)
                .pattern(" IN")
                .pattern("I I")
                .pattern("NI ")
                .unlockedBy("has_material", has(ModTags.Items.DURALUMIN_INGOT))
                .save(p_176532_);
        ShapedRecipeBuilder.shaped(ModItems.NICROSIL_GOLD_METALMIND.get())
                .define('I', ModTags.Items.NICROSIL_INGOT)
                .define('N', ModTags.Items.NICROSIL_NUGGET)
                .define('M', ModItems.GOLD_METALMIND.get())
                .pattern(" IN")
                .pattern("IMI")
                .pattern("NI ")
                .unlockedBy("has_material", has(ModTags.Items.NICROSIL_INGOT))
                .save(p_176532_);
        ShapedRecipeBuilder.shaped(ModItems.NICROSIL_STEEL_METALMIND.get())
                .define('I', ModTags.Items.NICROSIL_INGOT)
                .define('N', ModTags.Items.NICROSIL_NUGGET)
                .define('M', ModItems.STEEL_METALMIND.get())
                .pattern(" IN")
                .pattern("IMI")
                .pattern("NI ")
                .unlockedBy("has_material", has(ModTags.Items.NICROSIL_INGOT))
                .save(p_176532_);
        ShapedRecipeBuilder.shaped(ModItems.NICROSIL_PEWTER_METALMIND.get())
                .define('I', ModTags.Items.NICROSIL_INGOT)
                .define('N', ModTags.Items.NICROSIL_NUGGET)
                .define('M', ModItems.PEWTER_METALMIND.get())
                .pattern(" IN")
                .pattern("IMI")
                .pattern("NI ")
                .unlockedBy("has_material", has(ModTags.Items.NICROSIL_INGOT))
                .save(p_176532_);
        ShapedRecipeBuilder.shaped(ModItems.NICROSIL_ELECTRUM_METALMIND.get())
                .define('I', ModTags.Items.NICROSIL_INGOT)
                .define('N', ModTags.Items.NICROSIL_NUGGET)
                .define('M', ModItems.ELECTRUM_METALMIND.get())
                .pattern(" IN")
                .pattern("IMI")
                .pattern("NI ")
                .unlockedBy("has_material", has(ModTags.Items.NICROSIL_INGOT))
                .save(p_176532_);
        ShapedRecipeBuilder.shaped(ModItems.NICROSIL_BENDALLOY_METALMIND.get())
                .define('I', ModTags.Items.NICROSIL_INGOT)
                .define('N', ModTags.Items.NICROSIL_NUGGET)
                .define('M', ModItems.BENDALLOY_METALMIND.get())
                .pattern(" IN")
                .pattern("IMI")
                .pattern("NI ")
                .unlockedBy("has_material", has(ModTags.Items.NICROSIL_INGOT))
                .save(p_176532_);
        ShapedRecipeBuilder.shaped(ModItems.NICROSIL_TIN_METALMIND.get())
                .define('I', ModTags.Items.NICROSIL_INGOT)
                .define('N', ModTags.Items.NICROSIL_NUGGET)
                .define('M', ModItems.TIN_METALMIND.get())
                .pattern(" IN")
                .pattern("IMI")
                .pattern("NI ")
                .unlockedBy("has_material", has(ModTags.Items.NICROSIL_INGOT))
                .save(p_176532_);
        ShapedRecipeBuilder.shaped(ModItems.NICROSIL_IRON_METALMIND.get())
                .define('I', ModTags.Items.NICROSIL_INGOT)
                .define('N', ModTags.Items.NICROSIL_NUGGET)
                .define('M', ModItems.IRON_METALMIND.get())
                .pattern(" IN")
                .pattern("IMI")
                .pattern("NI ")
                .unlockedBy("has_material", has(ModTags.Items.NICROSIL_INGOT))
                .save(p_176532_);
        ShapedRecipeBuilder.shaped(ModItems.NICROSIL_BRASS_METALMIND.get())
                .define('I', ModTags.Items.NICROSIL_INGOT)
                .define('N', ModTags.Items.NICROSIL_NUGGET)
                .define('M', ModItems.BRASS_METALMIND.get())
                .pattern(" IN")
                .pattern("IMI")
                .pattern("NI ")
                .unlockedBy("has_material", has(ModTags.Items.NICROSIL_INGOT))
                .save(p_176532_);
        ShapedRecipeBuilder.shaped(ModItems.NICROSIL_CADMIUM_METALMIND.get())
                .define('I', ModTags.Items.NICROSIL_INGOT)
                .define('N', ModTags.Items.NICROSIL_NUGGET)
                .define('M', ModItems.CADMIUM_METALMIND.get())
                .pattern(" IN")
                .pattern("IMI")
                .pattern("NI ")
                .unlockedBy("has_material", has(ModTags.Items.NICROSIL_INGOT))
                .save(p_176532_);
        ShapedRecipeBuilder.shaped(ModItems.NICROSIL_CHROMIUM_METALMIND.get())
                .define('I', ModTags.Items.NICROSIL_INGOT)
                .define('N', ModTags.Items.NICROSIL_NUGGET)
                .define('M', ModItems.CHROMIUM_METALMIND.get())
                .pattern(" IN")
                .pattern("IMI")
                .pattern("NI ")
                .unlockedBy("has_material", has(ModTags.Items.NICROSIL_INGOT))
                .save(p_176532_);
        ShapedRecipeBuilder.shaped(ModItems.NICROSIL_ALUMINUM_METALMIND.get())
                .define('I', ModTags.Items.NICROSIL_INGOT)
                .define('N', ModTags.Items.NICROSIL_NUGGET)
                .define('M', ModItems.ALUMINUM_METALMIND.get())
                .pattern(" IN")
                .pattern("IMI")
                .pattern("NI ")
                .unlockedBy("has_material", has(ModTags.Items.NICROSIL_INGOT))
                .save(p_176532_);
        ShapedRecipeBuilder.shaped(ModItems.NICROSIL_ZINC_METALMIND.get())
                .define('I', ModTags.Items.NICROSIL_INGOT)
                .define('N', ModTags.Items.NICROSIL_NUGGET)
                .define('M', ModItems.ZINC_METALMIND.get())
                .pattern(" IN")
                .pattern("IMI")
                .pattern("NI ")
                .unlockedBy("has_material", has(ModTags.Items.NICROSIL_INGOT))
                .save(p_176532_);
        ShapedRecipeBuilder.shaped(ModItems.NICROSIL_COPPER_METALMIND.get())
                .define('I', ModTags.Items.NICROSIL_INGOT)
                .define('N', ModTags.Items.NICROSIL_NUGGET)
                .define('M', ModItems.COPPER_METALMIND.get())
                .pattern(" IN")
                .pattern("IMI")
                .pattern("NI ")
                .unlockedBy("has_material", has(ModTags.Items.NICROSIL_INGOT))
                .save(p_176532_);
        ShapedRecipeBuilder.shaped(ModItems.NICROSIL_DURALUMIN_METALMIND.get())
                .define('I', ModTags.Items.NICROSIL_INGOT)
                .define('N', ModTags.Items.NICROSIL_NUGGET)
                .define('M', ModItems.DURALUMIN_METALMIND.get())
                .pattern(" IN")
                .pattern("IMI")
                .pattern("NI ")
                .unlockedBy("has_material", has(ModTags.Items.NICROSIL_INGOT))
                .save(p_176532_);
        ShapedRecipeBuilder.shaped(ModItems.NICROSIL_BRONZE_METALMIND.get())
                .define('I', ModTags.Items.NICROSIL_INGOT)
                .define('N', ModTags.Items.NICROSIL_NUGGET)
                .define('M', ModItems.BRONZE_METALMIND.get())
                .pattern(" IN")
                .pattern("IMI")
                .pattern("NI ")
                .unlockedBy("has_material", has(ModTags.Items.NICROSIL_INGOT))
                .save(p_176532_);

        ShapedRecipeBuilder.shaped(ModItems.METAL_HAMMER.get())
                .define('S', ModTags.Items.STEEL_INGOT)
                .define('H', Items.STICK)
                .pattern("SS ")
                .pattern("SH ")
                .pattern("  H")
                .unlockedBy("has_material", has(ModTags.Items.STEEL_INGOT))
                .save(p_176532_);

        ShapelessRecipeBuilder.shapeless(ModItems.TIN_BEAD.get(), 8)
                .requires(ModTags.Items.TIN_INGOT)
                .requires(ModItems.METAL_HAMMER.get())
                .unlockedBy("has_material", has(ModItems.METAL_HAMMER.get()))
                .save(p_176532_);

        ShapelessRecipeBuilder.shapeless(ModItems.PEWTER_BEAD.get(), 8)
                .requires(ModTags.Items.PEWTER_INGOT)
                .requires(ModItems.METAL_HAMMER.get())
                .unlockedBy("has_material", has(ModItems.METAL_HAMMER.get()))
                .save(p_176532_);

        ShapelessRecipeBuilder.shapeless(ModItems.IRON_BEAD.get(), 8)
                .requires(Items.IRON_INGOT)
                .requires(ModItems.METAL_HAMMER.get())
                .unlockedBy("has_material", has(ModItems.METAL_HAMMER.get()))
                .save(p_176532_);

        ShapelessRecipeBuilder.shapeless(ModItems.STEEL_BEAD.get(), 8)
                .requires(ModTags.Items.STEEL_INGOT)
                .requires(ModItems.METAL_HAMMER.get())
                .unlockedBy("has_material", has(ModItems.METAL_HAMMER.get()))
                .save(p_176532_);

        ShapelessRecipeBuilder.shapeless(ModItems.COPPER_BEAD.get(), 8)
                .requires(Items.COPPER_INGOT)
                .requires(ModItems.METAL_HAMMER.get())
                .unlockedBy("has_material", has(ModItems.METAL_HAMMER.get()))
                .save(p_176532_);

        ShapelessRecipeBuilder.shapeless(ModItems.BRONZE_BEAD.get(), 8)
                .requires(ModTags.Items.BRONZE_INGOT)
                .requires(ModItems.METAL_HAMMER.get())
                .unlockedBy("has_material", has(ModItems.METAL_HAMMER.get()))
                .save(p_176532_);

        ShapelessRecipeBuilder.shapeless(ModItems.ZINC_BEAD.get(), 8)
                .requires(ModTags.Items.ZINC_INGOT)
                .requires(ModItems.METAL_HAMMER.get())
                .unlockedBy("has_material", has(ModItems.METAL_HAMMER.get()))
                .save(p_176532_);

        ShapelessRecipeBuilder.shapeless(ModItems.BRASS_BEAD.get(), 8)
                .requires(ModTags.Items.BRASS_INGOT)
                .requires(ModItems.METAL_HAMMER.get())
                .unlockedBy("has_material", has(ModItems.METAL_HAMMER.get()))
                .save(p_176532_);

        ShapelessRecipeBuilder.shapeless(ModItems.GOLD_BEAD.get(), 8)
                .requires(Items.GOLD_INGOT)
                .requires(ModItems.METAL_HAMMER.get())
                .unlockedBy("has_material", has(ModItems.METAL_HAMMER.get()))
                .save(p_176532_);

        ShapelessRecipeBuilder.shapeless(ModItems.ELECTRUM_BEAD.get(), 8)
                .requires(ModTags.Items.ELECTRUM_INGOT)
                .requires(ModItems.METAL_HAMMER.get())
                .unlockedBy("has_material", has(ModItems.METAL_HAMMER.get()))
                .save(p_176532_);

        ShapelessRecipeBuilder.shapeless(ModItems.CADMIUM_BEAD.get(), 8)
                .requires(ModTags.Items.CADMIUM_INGOT)
                .requires(ModItems.METAL_HAMMER.get())
                .unlockedBy("has_material", has(ModItems.METAL_HAMMER.get()))
                .save(p_176532_);

        ShapelessRecipeBuilder.shapeless(ModItems.BENDALLOY_BEAD.get(), 8)
                .requires(ModTags.Items.BENDALLOY_INGOT)
                .requires(ModItems.METAL_HAMMER.get())
                .unlockedBy("has_material", has(ModItems.METAL_HAMMER.get()))
                .save(p_176532_);

        ShapelessRecipeBuilder.shapeless(ModItems.CHROMIUM_BEAD.get(), 8)
                .requires(ModTags.Items.CHROMIUM_INGOT)
                .requires(ModItems.METAL_HAMMER.get())
                .unlockedBy("has_material", has(ModItems.METAL_HAMMER.get()))
                .save(p_176532_);

        ShapelessRecipeBuilder.shapeless(ModItems.NICROSIL_BEAD.get(), 8)
                .requires(ModTags.Items.NICROSIL_INGOT)
                .requires(ModItems.METAL_HAMMER.get())
                .unlockedBy("has_material", has(ModItems.METAL_HAMMER.get()))
                .save(p_176532_);

        ShapelessRecipeBuilder.shapeless(ModItems.ALUMINUM_BEAD.get(), 8)
                .requires(ModTags.Items.ALUMINUM_INGOT)
                .requires(ModItems.METAL_HAMMER.get())
                .unlockedBy("has_material", has(ModItems.METAL_HAMMER.get()))
                .save(p_176532_);

        ShapelessRecipeBuilder.shapeless(ModItems.DURALUMIN_BEAD.get(), 8)
                .requires(ModTags.Items.DURALUMIN_INGOT)
                .requires(ModItems.METAL_HAMMER.get())
                .unlockedBy("has_material", has(ModItems.METAL_HAMMER.get()))
                .save(p_176532_);

        ShapedRecipeBuilder.shaped(ModItems.ALUMINUM_SPIKE.get())
                .define('I', ModTags.Items.ALUMINUM_INGOT)
                .define('N', ModTags.Items.ALUMINUM_NUGGET)
                .pattern(" NI")
                .pattern("NI ")
                .pattern("I  ")
                .unlockedBy("has_material", has(ModTags.Items.ALUMINUM_INGOT))
                .save(p_176532_);

        ShapedRecipeBuilder.shaped(ModItems.BENDALLOY_SPIKE.get())
                .define('I', ModTags.Items.BENDALLOY_INGOT)
                .define('N', ModTags.Items.BENDALLOY_NUGGET)
                .pattern(" NI")
                .pattern("NI ")
                .pattern("I  ")
                .unlockedBy("has_material", has(ModTags.Items.BENDALLOY_INGOT))
                .save(p_176532_);

        ShapedRecipeBuilder.shaped(ModItems.BRASS_SPIKE.get())
                .define('I', ModTags.Items.BRASS_INGOT)
                .define('N', ModTags.Items.BRASS_NUGGET)
                .pattern(" NI")
                .pattern("NI ")
                .pattern("I  ")
                .unlockedBy("has_material", has(ModTags.Items.BRASS_INGOT))
                .save(p_176532_);

        ShapedRecipeBuilder.shaped(ModItems.STEEL_SPIKE.get())
                .define('I', ModTags.Items.STEEL_INGOT)
                .define('N', ModTags.Items.STEEL_NUGGET)
                .pattern(" NI")
                .pattern("NI ")
                .pattern("I  ")
                .unlockedBy("has_material", has(ModTags.Items.STEEL_INGOT))
                .save(p_176532_);

        ShapedRecipeBuilder.shaped(ModItems.BRONZE_SPIKE.get())
                .define('I', ModTags.Items.BRONZE_INGOT)
                .define('N', ModTags.Items.BRONZE_NUGGET)
                .pattern(" NI")
                .pattern("NI ")
                .pattern("I  ")
                .unlockedBy("has_material", has(ModTags.Items.BRONZE_INGOT))
                .save(p_176532_);

        ShapedRecipeBuilder.shaped(ModItems.CADMIUM_SPIKE.get())
                .define('I', ModTags.Items.CADMIUM_INGOT)
                .define('N', ModTags.Items.CADMIUM_NUGGET)
                .pattern(" NI")
                .pattern("NI ")
                .pattern("I  ")
                .unlockedBy("has_material", has(ModTags.Items.CADMIUM_INGOT))
                .save(p_176532_);

        ShapedRecipeBuilder.shaped(ModItems.ELECTRUM_SPIKE.get())
                .define('I', ModTags.Items.ELECTRUM_INGOT)
                .define('N', ModTags.Items.ELECTRUM_NUGGET)
                .pattern(" NI")
                .pattern("NI ")
                .pattern("I  ")
                .unlockedBy("has_material", has(ModTags.Items.ELECTRUM_INGOT))
                .save(p_176532_);

        ShapedRecipeBuilder.shaped(ModItems.GOLD_SPIKE.get())
                .define('I', Items.GOLD_INGOT)
                .define('N', Items.GOLD_NUGGET)
                .pattern(" NI")
                .pattern("NI ")
                .pattern("I  ")
                .unlockedBy("has_material", has(Items.GOLD_INGOT))
                .save(p_176532_);

        ShapedRecipeBuilder.shaped(ModItems.PEWTER_SPIKE.get())
                .define('I', ModTags.Items.PEWTER_INGOT)
                .define('N', ModTags.Items.PEWTER_NUGGET)
                .pattern(" NI")
                .pattern("NI ")
                .pattern("I  ")
                .unlockedBy("has_material", has(ModTags.Items.PEWTER_INGOT))
                .save(p_176532_);

    }
}
