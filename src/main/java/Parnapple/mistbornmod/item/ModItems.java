package Parnapple.mistbornmod.item;

import Parnapple.mistbornmod.MistbornBaseMod;
import Parnapple.mistbornmod.entity.ModEntities;
import Parnapple.mistbornmod.item.custom.*;
import Parnapple.mistbornmod.util.Metal;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MistbornBaseMod.MOD_ID);

    public static final RegistryObject<Item> TIN_INGOT = ITEMS.register("tin_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> RAW_TIN = ITEMS.register("raw_tin",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> TIN_NUGGET = ITEMS.register("tin_nugget",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> PEWTER_INGOT = ITEMS.register("pewter_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> PEWTER_NUGGET = ITEMS.register("pewter_nugget",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> BRONZE_INGOT = ITEMS.register("bronze_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> BRONZE_NUGGET = ITEMS.register("bronze_nugget",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> BRONZE_PICKAXE = ITEMS.register("bronze_pickaxe",
            () -> new PickaxeItem(ModItemTier.BRONZE, 0, 2f,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> BRONZE_AXE = ITEMS.register("bronze_axe",
            () -> new AxeItem(ModItemTier.BRONZE, 6f, -3.1f,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> BRONZE_SHOVEL = ITEMS.register("bronze_shovel",
            () -> new ShovelItem(ModItemTier.BRONZE, 1.5f, -3f,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> BRONZE_HOE = ITEMS.register("bronze_hoe",
            () -> new HoeItem(ModItemTier.BRONZE, -2, 0f,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> BRONZE_SWORD = ITEMS.register("bronze_sword",
            () -> new SwordItem(ModItemTier.BRONZE, 3, -2.4f,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> BRONZE_HELMET = ITEMS.register("bronze_helmet",
            () -> new ArmorItem(ModArmorMaterials.BRONZE, EquipmentSlot.HEAD,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> BRONZE_CHESTPLATE = ITEMS.register("bronze_chestplate",
            () -> new ArmorItem(ModArmorMaterials.BRONZE, EquipmentSlot.CHEST,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> BRONZE_LEGGINGS = ITEMS.register("bronze_leggings",
            () -> new ArmorItem(ModArmorMaterials.BRONZE, EquipmentSlot.LEGS,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> BRONZE_BOOTS = ITEMS.register("bronze_boots",
            () -> new ArmorItem(ModArmorMaterials.BRONZE, EquipmentSlot.FEET,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> STEEL_NUGGET = ITEMS.register("steel_nugget",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> STEEL_PICKAXE = ITEMS.register("steel_pickaxe",
            () -> new PickaxeItem(ModItemTier.STEEL, 0, 2f,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> STEEL_AXE = ITEMS.register("steel_axe",
            () -> new AxeItem(ModItemTier.STEEL, 6f, -3.1f,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> STEEL_SHOVEL = ITEMS.register("steel_shovel",
            () -> new ShovelItem(ModItemTier.STEEL, 1.5f, -3f,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> STEEL_HOE = ITEMS.register("steel_hoe",
            () -> new HoeItem(ModItemTier.STEEL, -2, 0f,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> STEEL_SWORD = ITEMS.register("steel_sword",
            () -> new SwordItem(ModItemTier.STEEL, 3, -2.4f,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> STEEL_HELMET = ITEMS.register("steel_helmet",
            () -> new ArmorItem(ModArmorMaterials.STEEL, EquipmentSlot.HEAD,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> STEEL_CHESTPLATE = ITEMS.register("steel_chestplate",
            () -> new ArmorItem(ModArmorMaterials.STEEL, EquipmentSlot.CHEST,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> STEEL_LEGGINGS = ITEMS.register("steel_leggings",
            () -> new ArmorItem(ModArmorMaterials.STEEL, EquipmentSlot.LEGS,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> STEEL_BOOTS = ITEMS.register("steel_boots",
            () -> new ArmorItem(ModArmorMaterials.STEEL, EquipmentSlot.FEET,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item>  BRASS_INGOT = ITEMS.register("brass_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> BRASS_NUGGET = ITEMS.register("brass_nugget",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item>  ZINC_INGOT = ITEMS.register("zinc_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> ZINC_NUGGET = ITEMS.register("zinc_nugget",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> RAW_ZINC = ITEMS.register("raw_zinc",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item>  ALUMINUM_INGOT = ITEMS.register("aluminum_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> ALUMINUM_NUGGET = ITEMS.register("aluminum_nugget",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> RAW_BAUXITE = ITEMS.register("raw_bauxite",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> ALUMINA = ITEMS.register("alumina",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item>  SILVER_INGOT = ITEMS.register("silver_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> SILVER_NUGGET = ITEMS.register("silver_nugget",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> RAW_SILVER = ITEMS.register("raw_silver",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item>  ELECTRUM_INGOT = ITEMS.register("electrum_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> ELECTRUM_NUGGET = ITEMS.register("electrum_nugget",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> CHROMIUM_INGOT = ITEMS.register("chromium_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> CHROMIUM_NUGGET = ITEMS.register("chromium_nugget",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> RAW_CHROMIUM = ITEMS.register("raw_chromium",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item>  NICROSIL_INGOT = ITEMS.register("nicrosil_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> NICROSIL_NUGGET = ITEMS.register("nicrosil_nugget",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item>  NICKEL_INGOT = ITEMS.register("nickel_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> NICKEL_NUGGET = ITEMS.register("nickel_nugget",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> RAW_NICKEL = ITEMS.register("raw_nickel",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item>  CADMIUM_INGOT = ITEMS.register("cadmium_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> CADMIUM_NUGGET = ITEMS.register("cadmium_nugget",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> RAW_CADMIUM = ITEMS.register("raw_cadmium",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item>  BISMUTH_INGOT = ITEMS.register("bismuth_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> BISMUTH_NUGGET = ITEMS.register("bismuth_nugget",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> RAW_BISMUTH = ITEMS.register("raw_bismuth",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item>  LEAD_INGOT = ITEMS.register("lead_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> LEAD_NUGGET = ITEMS.register("lead_nugget",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> RAW_LEAD = ITEMS.register("raw_lead",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item>  BENDALLOY_INGOT = ITEMS.register("bendalloy_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> BENDALLOY_NUGGET = ITEMS.register("bendalloy_nugget",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item>  DURALUMIN_INGOT = ITEMS.register("duralumin_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> DURALUMIN_NUGGET = ITEMS.register("duralumin_nugget",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> COPPER_NUGGET = ITEMS.register("copper_nugget",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> OBSIDIAN_DAGGER = ITEMS.register("obsidian_dagger",
            () -> new DaggerItem(ModItemTier.OBSIDIAN, 0, 0f,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> GLASS_DAGGER = ITEMS.register("glass_dagger",
            () -> new DaggerItem(ModItemTier.GLASS, 0, 0f,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> MISTCLOAK = ITEMS.register("mistcloak",
            () -> new MistcloakItem(ModArmorMaterials.MISTCLOAK, EquipmentSlot.CHEST,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> MISTCLOAK_LEGGINGS = ITEMS.register("mistcloak_leggings",
            () -> new ArmorItem(ModArmorMaterials.MISTCLOAK, EquipmentSlot.LEGS,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> STEEL_METALMIND = ITEMS.register("steel_metalmind",
            () -> new MetalmindItem(Metal.STEEL,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> PEWTER_METALMIND = ITEMS.register("pewter_metalmind",
            () -> new MetalmindItem(Metal.PEWTER,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> GOLD_METALMIND = ITEMS.register("gold_metalmind",
            () -> new MetalmindItem(Metal.GOLD,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> ELECTRUM_METALMIND = ITEMS.register("electrum_metalmind",
            () -> new MetalmindItem(Metal.ELECTRUM,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> BENDALLOY_METALMIND = ITEMS.register("bendalloy_metalmind",
            () -> new MetalmindItem(Metal.BENDALLOY,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> TIN_METALMIND = ITEMS.register("tin_metalmind",
            () -> new MetalmindItem(Metal.TIN,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> IRON_METALMIND = ITEMS.register("iron_metalmind",
            () -> new MetalmindItem(Metal.IRON,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> BRASS_METALMIND = ITEMS.register("brass_metalmind",
            () -> new MetalmindItem(Metal.BRASS,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> CADMIUM_METALMIND = ITEMS.register("cadmium_metalmind",
            () -> new MetalmindItem(Metal.CADMIUM,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> COPPER_METALMIND = ITEMS.register("copper_metalmind",
            () -> new CoppermindItem(new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> CHROMIUM_METALMIND = ITEMS.register("chromium_metalmind",
            () -> new MetalmindItem(Metal.CHROMIUM,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> ALUMINUM_METALMIND = ITEMS.register("aluminum_metalmind",
            () -> new MetalmindItem(Metal.ALUMINUM,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> ZINC_METALMIND = ITEMS.register("zinc_metalmind",
            () -> new MetalmindItem(Metal.ZINC,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> DURALUMIN_METALMIND = ITEMS.register("duralumin_metalmind",
            () -> new MetalmindItem(Metal.DURALUMIN,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> BRONZE_METALMIND = ITEMS.register("bronze_metalmind",
            () -> new MetalmindItem(Metal.BRONZE,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> NICROSIL_GOLD_METALMIND = ITEMS.register("nicrosil_gold_metalmind",
            () -> new MetalmindItem(Metal.GOLD,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB), true));

    public static final RegistryObject<Item> NICROSIL_STEEL_METALMIND = ITEMS.register("nicrosil_steel_metalmind",
            () -> new MetalmindItem(Metal.STEEL,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB), true));

    public static final RegistryObject<Item> NICROSIL_PEWTER_METALMIND = ITEMS.register("nicrosil_pewter_metalmind",
            () -> new MetalmindItem(Metal.PEWTER,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB), true));

    public static final RegistryObject<Item> NICROSIL_ELECTRUM_METALMIND = ITEMS.register("nicrosil_electrum_metalmind",
            () -> new MetalmindItem(Metal.ELECTRUM,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB), true));

    public static final RegistryObject<Item> NICROSIL_BENDALLOY_METALMIND = ITEMS.register("nicrosil_bendalloy_metalmind",
            () -> new MetalmindItem(Metal.BENDALLOY,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB), true));

    public static final RegistryObject<Item> NICROSIL_TIN_METALMIND = ITEMS.register("nicrosil_tin_metalmind",
            () -> new MetalmindItem(Metal.TIN,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB), true));

    public static final RegistryObject<Item> NICROSIL_IRON_METALMIND = ITEMS.register("nicrosil_iron_metalmind",
            () -> new MetalmindItem(Metal.IRON,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB), true));

    public static final RegistryObject<Item> NICROSIL_BRASS_METALMIND = ITEMS.register("nicrosil_brass_metalmind",
            () -> new MetalmindItem(Metal.BRASS,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB), true));

    public static final RegistryObject<Item> NICROSIL_CADMIUM_METALMIND = ITEMS.register("nicrosil_cadmium_metalmind",
            () -> new MetalmindItem(Metal.CADMIUM,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB),true));

    public static final RegistryObject<Item> NICROSIL_COPPER_METALMIND = ITEMS.register("nicrosil_copper_metalmind",
            () -> new CoppermindItem(new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB), true));

    public static final RegistryObject<Item> NICROSIL_CHROMIUM_METALMIND = ITEMS.register("nicrosil_chromium_metalmind",
            () -> new MetalmindItem(Metal.CHROMIUM,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB), true));

    public static final RegistryObject<Item> NICROSIL_ALUMINUM_METALMIND = ITEMS.register("nicrosil_aluminum_metalmind",
            () -> new MetalmindItem(Metal.ALUMINUM,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB), true));

    public static final RegistryObject<Item> NICROSIL_ZINC_METALMIND = ITEMS.register("nicrosil_zinc_metalmind",
            () -> new MetalmindItem(Metal.ZINC,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB), true));

    public static final RegistryObject<Item> NICROSIL_DURALUMIN_METALMIND = ITEMS.register("nicrosil_duralumin_metalmind",
            () -> new MetalmindItem(Metal.DURALUMIN,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB), true));

    public static final RegistryObject<Item> NICROSIL_BRONZE_METALMIND = ITEMS.register("nicrosil_bronze_metalmind",
            () -> new MetalmindItem(Metal.BRONZE,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB), true));

    public static final RegistryObject<Item> TIN_BEAD = ITEMS.register("tin_bead",
            () -> new MetalBeadItem(Metal.TIN,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB).stacksTo(16)));

    public static final RegistryObject<Item> PEWTER_BEAD = ITEMS.register("pewter_bead",
            () -> new MetalBeadItem(Metal.PEWTER,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB).stacksTo(16)));

    public static final RegistryObject<Item> IRON_BEAD = ITEMS.register("iron_bead",
            () -> new MetalBeadItem(Metal.IRON,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB).stacksTo(16)));

    public static final RegistryObject<Item> STEEL_BEAD = ITEMS.register("steel_bead",
            () -> new MetalBeadItem(Metal.STEEL,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB).stacksTo(16)));

    public static final RegistryObject<Item> ZINC_BEAD = ITEMS.register("zinc_bead",
            () -> new MetalBeadItem(Metal.ZINC,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB).stacksTo(16)));

    public static final RegistryObject<Item> BRASS_BEAD = ITEMS.register("brass_bead",
            () -> new MetalBeadItem(Metal.BRASS,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB).stacksTo(16)));

    public static final RegistryObject<Item> COPPER_BEAD = ITEMS.register("copper_bead",
            () -> new MetalBeadItem(Metal.COPPER,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB).stacksTo(16)));

    public static final RegistryObject<Item> BRONZE_BEAD = ITEMS.register("bronze_bead",
            () -> new MetalBeadItem(Metal.BRONZE,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB).stacksTo(16)));

    public static final RegistryObject<Item> GOLD_BEAD = ITEMS.register("gold_bead",
            () -> new MetalBeadItem(Metal.GOLD,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB).stacksTo(16)));

    public static final RegistryObject<Item> ELECTRUM_BEAD = ITEMS.register("electrum_bead",
            () -> new MetalBeadItem(Metal.ELECTRUM,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB).stacksTo(16)));

    public static final RegistryObject<Item> BENDALLOY_BEAD = ITEMS.register("bendalloy_bead",
            () -> new MetalBeadItem(Metal.BENDALLOY,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB).stacksTo(16)));

    public static final RegistryObject<Item> CADMIUM_BEAD = ITEMS.register("cadmium_bead",
            () -> new MetalBeadItem(Metal.CADMIUM,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB).stacksTo(16)));

    public static final RegistryObject<Item> CHROMIUM_BEAD = ITEMS.register("chromium_bead",
            () -> new MetalBeadItem(Metal.CHROMIUM,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB).stacksTo(16)));

    public static final RegistryObject<Item> NICROSIL_BEAD = ITEMS.register("nicrosil_bead",
            () -> new MetalBeadItem(Metal.NICROSIL,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB).stacksTo(16)));

    public static final RegistryObject<Item> ALUMINUM_BEAD = ITEMS.register("aluminum_bead",
            () -> new MetalBeadItem(Metal.ALUMINUM,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB).stacksTo(16)));

    public static final RegistryObject<Item> DURALUMIN_BEAD = ITEMS.register("duralumin_bead",
            () -> new MetalBeadItem(Metal.DURALUMIN,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB).stacksTo(16)));

    public static final RegistryObject<Item> METAL_HAMMER = ITEMS.register("metal_hammer",
            () -> new MetalShapingHammerItem(
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB).stacksTo(1).durability(512)));

    public static final RegistryObject<Item> ALUMINUM_SPIKE = ITEMS.register("aluminum_spike",
            () -> new HemalurgicSpikeItem(Metal.ALUMINUM));

    public static final RegistryObject<Item> STEEL_SPIKE = ITEMS.register("steel_spike",
            () -> new HemalurgicSpikeItem(Metal.STEEL));

    public static final RegistryObject<Item> PEWTER_SPIKE = ITEMS.register("pewter_spike",
            () -> new HemalurgicSpikeItem(Metal.PEWTER));

    public static final RegistryObject<Item> BRASS_SPIKE = ITEMS.register("brass_spike",
            () -> new HemalurgicSpikeItem(Metal.BRASS));

    public static final RegistryObject<Item> BRONZE_SPIKE = ITEMS.register("bronze_spike",
            () -> new HemalurgicSpikeItem(Metal.BRONZE));

    public static final RegistryObject<Item> CADMIUM_SPIKE = ITEMS.register("cadmium_spike",
            () -> new HemalurgicSpikeItem(Metal.CADMIUM));

    public static final RegistryObject<Item> GOLD_SPIKE = ITEMS.register("gold_spike",
            () -> new HemalurgicSpikeItem(Metal.GOLD));

    public static final RegistryObject<Item> ELECTRUM_SPIKE = ITEMS.register("electrum_spike",
            () -> new HemalurgicSpikeItem(Metal.ELECTRUM));

    public static final RegistryObject<Item> BENDALLOY_SPIKE = ITEMS.register("bendalloy_spike",
            () -> new HemalurgicSpikeItem(Metal.BENDALLOY));

    public static final RegistryObject<Item> COIN_POUCH = ITEMS.register("coin_pouch",
            () -> new CoinPouchItem(new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> DUELING_CANE = ITEMS.register("dueling_cane",
            () -> new SwordItem(Tiers.WOOD, 4, 1.2f, new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> HARDWOOD_DUELING_CANE = ITEMS.register("hardwood_dueling_cane",
            () -> new SwordItem(ModItemTier.HARDWOOD, 5, 1.0f, new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> MISTAGER_PEWTER_SPAWN_EGG = ITEMS.register("mistager_pewter_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.MISTAGER_PEWTER,0x545756, 0x98a6aa,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> MISTAGER_TIN_SPAWN_EGG = ITEMS.register("mistager_tin_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.MISTAGER_TIN,0x545756, 0xa7a7a7,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> MISTAGER_BRASS_SPAWN_EGG = ITEMS.register("mistager_brass_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.MISTAGER_BRASS,0x545756, 0xc3a322,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> MISTAGER_ZINC_SPAWN_EGG = ITEMS.register("mistager_zinc_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.MISTAGER_ZINC,0x545756, 0x878787,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> MISTAGER_CHROMIUM_SPAWN_EGG = ITEMS.register("mistager_chromium_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.MISTAGER_CHROMIUM,0x545756, 0xe7e7e7,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> MISTAGER_COPPER_SPAWN_EGG = ITEMS.register("mistager_copper_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.MISTAGER_COPPER,0x545756, 0x9c4e31,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> MISTAGER_BRONZE_SPAWN_EGG = ITEMS.register("mistager_bronze_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.MISTAGER_BRONZE,0x545756, 0xc37022,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> MISTAGER_IRON_SPAWN_EGG = ITEMS.register("mistager_iron_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.MISTAGER_IRON,0x545756, 0xd8d8d8,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static final RegistryObject<Item> MISTAGER_STEEL_SPAWN_EGG = ITEMS.register("mistager_steel_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.MISTAGER_STEEL,0x545756, 0xebebeb,
                    new Item.Properties().tab(ModCreativeModeTab.MISTBORN_TAB)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
