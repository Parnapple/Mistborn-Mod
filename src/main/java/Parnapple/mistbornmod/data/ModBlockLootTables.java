package Parnapple.mistbornmod.data;

import Parnapple.mistbornmod.block.ModBlocks;
import Parnapple.mistbornmod.item.ModItems;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockLootTables extends BlockLoot {
    @Override
    protected void addTables() {
        this.dropSelf(ModBlocks.ALUMINUM_BLOCK.get());
        this.dropSelf(ModBlocks.BRASS_BLOCK.get());
        this.dropSelf(ModBlocks.BRONZE_BLOCK.get());
        this.dropSelf(ModBlocks.CHROMIUM_BLOCK.get());
        this.dropSelf(ModBlocks.ELECTRUM_BLOCK.get());
        this.dropSelf(ModBlocks.NICKEL_BLOCK.get());
        this.dropSelf(ModBlocks.NICROSIL_BLOCK.get());
        this.dropSelf(ModBlocks.PEWTER_BLOCK.get());
        this.dropSelf(ModBlocks.SILVER_BLOCK.get());
        this.dropSelf(ModBlocks.STEEL_BLOCK.get());
        this.dropSelf(ModBlocks.TIN_BLOCK.get());
        this.dropSelf(ModBlocks.ZINC_BLOCK.get());
        this.dropSelf(ModBlocks.CADMIUM_BLOCK.get());
        this.dropSelf(ModBlocks.BISMUTH_BLOCK.get());
        this.dropSelf(ModBlocks.LEAD_BLOCK.get());
        this.dropSelf(ModBlocks.BENDALLOY_BLOCK.get());
        this.dropSelf(ModBlocks.DURALUMIN_BLOCK.get());
        this.dropSelf(ModBlocks.METALLURGY_FURNACE_BLOCK.get());

        this.add(ModBlocks.BAUXITE_ORE.get(), (block) -> {
           return  createOreDrop(ModBlocks.BAUXITE_ORE.get(), ModItems.RAW_BAUXITE.get());
        });
        this.add(ModBlocks.CHROMIUM_ORE.get(), (block) -> {
            return  createOreDrop(ModBlocks.CHROMIUM_ORE.get(), ModItems.RAW_CHROMIUM.get());
        });
        this.add(ModBlocks.DEEPSLATE_CHROMIUM_ORE.get(), (block) -> {
            return createOreDrop(ModBlocks.DEEPSLATE_CHROMIUM_ORE.get(), ModItems.RAW_CHROMIUM.get());
        });
        this.add(ModBlocks.NICKEL_ORE.get(), (block) -> {
            return createOreDrop(ModBlocks.NICKEL_ORE.get(), ModItems.RAW_NICKEL.get());
        });
        this.add(ModBlocks.DEEPSLATE_NICKEL_ORE.get(), (block) -> {
            return createOreDrop(ModBlocks.DEEPSLATE_NICKEL_ORE.get(), ModItems.RAW_NICKEL.get());
        });
        this.add(ModBlocks.SILVER_ORE.get(), (block) -> {
            return createOreDrop(ModBlocks.SILVER_ORE.get(), ModItems.RAW_SILVER.get());
        });
        this.add(ModBlocks.DEEPSLATE_SILVER_ORE.get(), (block) -> {
            return createOreDrop(ModBlocks.DEEPSLATE_SILVER_ORE.get(), ModItems.RAW_SILVER.get());
        });
        this.add(ModBlocks.TIN_ORE.get(), (block) -> {
            return createOreDrop(ModBlocks.TIN_ORE.get(), ModItems.RAW_TIN.get());
        });
        this.add(ModBlocks.DEEPSLATE_TIN_ORE.get(), (block) -> {
            return createOreDrop(ModBlocks.DEEPSLATE_TIN_ORE.get(), ModItems.RAW_TIN.get());
        });
        this.add(ModBlocks.ZINC_ORE.get(), (block) -> {
            return createOreDrop(ModBlocks.ZINC_ORE.get(), ModItems.RAW_ZINC.get());
        });
        this.add(ModBlocks.DEEPSLATE_ZINC_ORE.get(), (block) -> {
            return createOreDrop(ModBlocks.DEEPSLATE_ZINC_ORE.get(), ModItems.RAW_ZINC.get());
        });
        this.add(ModBlocks.CADMIUM_ORE.get(), (block) -> {
            return createOreDrop(ModBlocks.CADMIUM_ORE.get(), ModItems.RAW_CADMIUM.get());
        });
        this.add(ModBlocks.DEEPSLATE_CADMIUM_ORE.get(), (block) -> {
            return createOreDrop(ModBlocks.DEEPSLATE_CADMIUM_ORE.get(), ModItems.RAW_CADMIUM.get());
        });
        this.add(ModBlocks.BISMUTH_ORE.get(), (block) -> {
            return createOreDrop(ModBlocks.BISMUTH_ORE.get(), ModItems.RAW_BISMUTH.get());
        });
        this.add(ModBlocks.DEEPSLATE_BISMUTH_ORE.get(), (block) -> {
            return createOreDrop(ModBlocks.DEEPSLATE_BISMUTH_ORE.get(), ModItems.RAW_BISMUTH.get());
        });
        this.add(ModBlocks.LEAD_ORE.get(), (block) -> {
            return createOreDrop(ModBlocks.LEAD_ORE.get(), ModItems.RAW_LEAD.get());
        });
        this.add(ModBlocks.DEEPSLATE_LEAD_ORE.get(), (block) -> {
            return createOreDrop(ModBlocks.DEEPSLATE_LEAD_ORE.get(), ModItems.RAW_LEAD.get());
        });

    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
