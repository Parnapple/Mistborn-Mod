package Parnapple.mistbornmod.util;

import Parnapple.mistbornmod.MistbornBaseMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;

public class ModTags {

    public static class Blocks {

        private static Tags.IOptionalNamedTag<Block> createTag(String name) {
            return BlockTags.createOptional(new ResourceLocation(MistbornBaseMod.MOD_ID, name));
        }
        private static Tags.IOptionalNamedTag<Block> createForgeTag(String name) {
            return BlockTags.createOptional(new ResourceLocation("forge", name));
        }

    }

    public static class Items {

        public static final Tags.IOptionalNamedTag<Item> TIN_INGOT = createForgeTag("ingots/tin");
        public static final Tags.IOptionalNamedTag<Item> PEWTER_INGOT = createForgeTag("ingots/pewter");
        public static final Tags.IOptionalNamedTag<Item> BRONZE_INGOT = createForgeTag("ingots/bronze");
        public static final Tags.IOptionalNamedTag<Item> STEEL_INGOT = createForgeTag("ingots/steel");
        public static final Tags.IOptionalNamedTag<Item> BRASS_INGOT = createForgeTag("ingots/brass");
        public static final Tags.IOptionalNamedTag<Item> ZINC_INGOT = createForgeTag("ingots/zinc");
        public static final Tags.IOptionalNamedTag<Item> ALUMINUM_INGOT = createForgeTag("ingots/aluminum");
        public static final Tags.IOptionalNamedTag<Item> SILVER_INGOT = createForgeTag("ingots/silver");
        public static final Tags.IOptionalNamedTag<Item> ELECTRUM_INGOT = createForgeTag("ingots/electrum");
        public static final Tags.IOptionalNamedTag<Item> CHROMIUM_INGOT = createForgeTag("ingots/chromium");
        public static final Tags.IOptionalNamedTag<Item> NICROSIL_INGOT = createForgeTag("ingots/nicrosil");
        public static final Tags.IOptionalNamedTag<Item> NICKEL_INGOT = createForgeTag("ingots/nickel");
        public static final Tags.IOptionalNamedTag<Item> CADMIUM_INGOT = createForgeTag("ingots/cadmium");
        public static final Tags.IOptionalNamedTag<Item> BISMUTH_INGOT = createForgeTag("ingots/bismuth");
        public static final Tags.IOptionalNamedTag<Item> LEAD_INGOT = createForgeTag("ingots/lead");
        public static final Tags.IOptionalNamedTag<Item> BENDALLOY_INGOT = createForgeTag("ingots/bendalloy");
        public static final Tags.IOptionalNamedTag<Item> DURALUMIN_INGOT = createForgeTag("ingots/duralumin");

        public static final Tags.IOptionalNamedTag<Item> TIN_NUGGET = createForgeTag("nuggets/tin");
        public static final Tags.IOptionalNamedTag<Item> PEWTER_NUGGET = createForgeTag("nuggets/pewter");
        public static final Tags.IOptionalNamedTag<Item> BRONZE_NUGGET = createForgeTag("nuggets/bronze");
        public static final Tags.IOptionalNamedTag<Item> STEEL_NUGGET = createForgeTag("nuggets/steel");
        public static final Tags.IOptionalNamedTag<Item> BRASS_NUGGET = createForgeTag("nuggets/brass");
        public static final Tags.IOptionalNamedTag<Item> ZINC_NUGGET = createForgeTag("nuggets/zinc");
        public static final Tags.IOptionalNamedTag<Item> ALUMINUM_NUGGET = createForgeTag("nuggets/aluminum");
        public static final Tags.IOptionalNamedTag<Item> SILVER_NUGGET = createForgeTag("nuggets/silver");
        public static final Tags.IOptionalNamedTag<Item> ELECTRUM_NUGGET = createForgeTag("nuggets/electrum");
        public static final Tags.IOptionalNamedTag<Item> CHROMIUM_NUGGET = createForgeTag("nuggets/chromium");
        public static final Tags.IOptionalNamedTag<Item> NICROSIL_NUGGET = createForgeTag("nuggets/nicrosil");
        public static final Tags.IOptionalNamedTag<Item> NICKEL_NUGGET = createForgeTag("nuggets/nickel");
        public static final Tags.IOptionalNamedTag<Item> CADMIUM_NUGGET = createForgeTag("nuggets/cadmium");
        public static final Tags.IOptionalNamedTag<Item> BISMUTH_NUGGET = createForgeTag("nuggets/bismuth");
        public static final Tags.IOptionalNamedTag<Item> LEAD_NUGGET = createForgeTag("nuggets/lead");
        public static final Tags.IOptionalNamedTag<Item> BENDALLOY_NUGGET = createForgeTag("nuggets/bendalloy");
        public static final Tags.IOptionalNamedTag<Item> DURALUMIN_NUGGET = createForgeTag("nuggets/duralumin");
        public static final Tags.IOptionalNamedTag<Item> COPPER_NUGGET = createForgeTag("nuggets/copper");




        private static Tags.IOptionalNamedTag<Item> createTag(String name) {
            return ItemTags.createOptional(new ResourceLocation(MistbornBaseMod.MOD_ID, name));
        }
        private static Tags.IOptionalNamedTag<Item> createForgeTag(String name) {
            return ItemTags.createOptional(new ResourceLocation("forge", name));
        }
    }




}
