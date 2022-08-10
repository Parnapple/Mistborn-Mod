package Parnapple.mistbornmod.util;

import Parnapple.mistbornmod.MistbornBaseMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;

public class ModTags {

    public static class Blocks {

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(new ResourceLocation(MistbornBaseMod.MOD_ID, name));
        }
        private static TagKey<Block> createForgeTag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }

    }

    public static class Items {

        public static final TagKey<Item> TIN_INGOT = createForgeTag("ingots/tin");
        public static final TagKey<Item> PEWTER_INGOT = createForgeTag("ingots/pewter");
        public static final TagKey<Item> BRONZE_INGOT = createForgeTag("ingots/bronze");
        public static final TagKey<Item> STEEL_INGOT = createForgeTag("ingots/steel");
        public static final TagKey<Item> BRASS_INGOT = createForgeTag("ingots/brass");
        public static final TagKey<Item> ZINC_INGOT = createForgeTag("ingots/zinc");
        public static final TagKey<Item> ALUMINUM_INGOT = createForgeTag("ingots/aluminum");
        public static final TagKey<Item> SILVER_INGOT = createForgeTag("ingots/silver");
        public static final TagKey<Item> ELECTRUM_INGOT = createForgeTag("ingots/electrum");
        public static final TagKey<Item> CHROMIUM_INGOT = createForgeTag("ingots/chromium");
        public static final TagKey<Item> NICROSIL_INGOT = createForgeTag("ingots/nicrosil");
        public static final TagKey<Item> NICKEL_INGOT = createForgeTag("ingots/nickel");
        public static final TagKey<Item> CADMIUM_INGOT = createForgeTag("ingots/cadmium");
        public static final TagKey<Item> BISMUTH_INGOT = createForgeTag("ingots/bismuth");
        public static final TagKey<Item> LEAD_INGOT = createForgeTag("ingots/lead");
        public static final TagKey<Item> BENDALLOY_INGOT = createForgeTag("ingots/bendalloy");
        public static final TagKey<Item> DURALUMIN_INGOT = createForgeTag("ingots/duralumin");

        public static final TagKey<Item> TIN_NUGGET = createForgeTag("nuggets/tin");
        public static final TagKey<Item> PEWTER_NUGGET = createForgeTag("nuggets/pewter");
        public static final TagKey<Item> BRONZE_NUGGET = createForgeTag("nuggets/bronze");
        public static final TagKey<Item> STEEL_NUGGET = createForgeTag("nuggets/steel");
        public static final TagKey<Item> BRASS_NUGGET = createForgeTag("nuggets/brass");
        public static final TagKey<Item> ZINC_NUGGET = createForgeTag("nuggets/zinc");
        public static final TagKey<Item> ALUMINUM_NUGGET = createForgeTag("nuggets/aluminum");
        public static final TagKey<Item> SILVER_NUGGET = createForgeTag("nuggets/silver");
        public static final TagKey<Item> ELECTRUM_NUGGET = createForgeTag("nuggets/electrum");
        public static final TagKey<Item> CHROMIUM_NUGGET = createForgeTag("nuggets/chromium");
        public static final TagKey<Item> NICROSIL_NUGGET = createForgeTag("nuggets/nicrosil");
        public static final TagKey<Item> NICKEL_NUGGET = createForgeTag("nuggets/nickel");
        public static final TagKey<Item> CADMIUM_NUGGET = createForgeTag("nuggets/cadmium");
        public static final TagKey<Item> BISMUTH_NUGGET = createForgeTag("nuggets/bismuth");
        public static final TagKey<Item> LEAD_NUGGET = createForgeTag("nuggets/lead");
        public static final TagKey<Item> BENDALLOY_NUGGET = createForgeTag("nuggets/bendalloy");
        public static final TagKey<Item> DURALUMIN_NUGGET = createForgeTag("nuggets/duralumin");
        public static final TagKey<Item> COPPER_NUGGET = createForgeTag("nuggets/copper");




        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(new ResourceLocation(MistbornBaseMod.MOD_ID, name));
        }
        private static TagKey<Item> createForgeTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }
    }




}
