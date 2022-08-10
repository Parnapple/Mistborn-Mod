package Parnapple.mistbornmod.entity.villager;

import Parnapple.mistbornmod.MistbornBaseMod;
import Parnapple.mistbornmod.block.ModBlocks;
import Parnapple.mistbornmod.item.ModItems;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.lang.reflect.InvocationTargetException;

public class VillagerProfessions {
    public static final DeferredRegister<PoiType> POINT_OF_INTEREST_TYPES =
            DeferredRegister.create(ForgeRegistries.POI_TYPES, MistbornBaseMod.MOD_ID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS =
            DeferredRegister.create(ForgeRegistries.PROFESSIONS, MistbornBaseMod.MOD_ID);

    public static final RegistryObject<PoiType> METALLURGIST_POI = POINT_OF_INTEREST_TYPES.register("metallurgist",
            () -> new PoiType("metallurgist", PoiType.getBlockStates(ModBlocks.METALLURGY_FURNACE_BLOCK.get()), 1, 1));

    public static final RegistryObject<VillagerProfession> METALLURGIST_PROFESSION = VILLAGER_PROFESSIONS.register("metallurgist",
            () -> new VillagerProfession("metallurgist", METALLURGIST_POI.get(), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.BUCKET_EMPTY_LAVA));



    public static void registerMetallurgistPOI() {
//this actually makes some sense... so weird. It's reflection, right?
        try {
            ObfuscationReflectionHelper.findMethod(PoiType.class, "registerBlockStates", PoiType.class).invoke(null, METALLURGIST_POI.get());
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void fillTradeData() {
       //access transformers used for the EmeraldsForItems() method. It's honestly worth it because of how easily it will make it

        VillagerTrades.ItemListing[] metal1 = new VillagerTrades.ItemListing[]{
          new VillagerTrades.EmeraldForItems(Items.COPPER_INGOT, 5, 16, 2),
          new VillagerTrades.EmeraldForItems(Items.IRON_INGOT, 4, 16, 2),
          new VillagerTrades.EmeraldForItems(ModItems.ZINC_INGOT.get(), 5, 16, 2),
          new VillagerTrades.EmeraldForItems(Items.COAL, 12, 8, 2),
          new VillagerTrades.ItemsForEmeralds(ModItems.STEEL_INGOT.get(), 3, 2, 9, 1),
          new VillagerTrades.ItemsForEmeralds(ModItems.BRASS_INGOT.get(), 1, 1, 18, 1),
          new VillagerTrades.ItemsForEmeralds(ModItems.BRONZE_INGOT.get(), 1, 1, 18, 1)
        };

        VillagerTrades.ItemListing[] metal2 = new VillagerTrades.ItemListing[]{
                new VillagerTrades.EmeraldForItems(ModItems.ALUMINUM_INGOT.get(), 4, 16, 10),
                new VillagerTrades.EmeraldForItems(ModItems.LEAD_INGOT.get(), 4, 16, 10),
                new VillagerTrades.EmeraldForItems(ModItems.TIN_INGOT.get(), 6, 16, 10),
                new VillagerTrades.ItemsForEmeralds(ModItems.PEWTER_INGOT.get(), 1, 1, 18, 5),
                new VillagerTrades.ItemsForEmeralds(ModItems.DURALUMIN_INGOT.get(), 2, 3, 6, 5),
        };

        VillagerTrades.ItemListing[] metal3 = new VillagerTrades.ItemListing[]{
                new VillagerTrades.EmeraldForItems(Items.GOLD_INGOT, 1, 4, 15),
                new VillagerTrades.EmeraldForItems(ModItems.SILVER_INGOT.get(), 1, 8, 15),
                new VillagerTrades.EmeraldForItems(ModItems.CADMIUM_INGOT.get(), 2, 16, 15),
                new VillagerTrades.EmeraldForItems(ModItems.CHROMIUM_INGOT.get(), 2, 16, 15),
                new VillagerTrades.EmeraldForItems(ModItems.NICKEL_INGOT.get(), 4, 16, 15),
                new VillagerTrades.EmeraldForItems(ModItems.BISMUTH_INGOT.get(), 3, 16, 15),
                new VillagerTrades.ItemsForEmeralds(ModItems.ELECTRUM_INGOT.get(), 2, 1, 18, 10),
                new VillagerTrades.ItemsForEmeralds(ModItems.BENDALLOY_INGOT.get(), 3, 2, 9, 10),
                new VillagerTrades.ItemsForEmeralds(ModItems.NICROSIL_INGOT.get(), 3, 2, 9, 10),
        };

        VillagerTrades.ItemListing[] metal4 = new VillagerTrades.ItemListing[]{
                new VillagerTrades.ItemsAndEmeraldsToItems(Items.IRON_INGOT, 1, 1, ModItems.STEEL_INGOT.get(), 1, 12, 20),
                new VillagerTrades.ItemsAndEmeraldsToItems(Items.COPPER_INGOT, 2, 1, ModItems.BRONZE_INGOT.get(), 2, 6, 20),
                new VillagerTrades.ItemsAndEmeraldsToItems(ModItems.ZINC_INGOT.get(), 2, 1, ModItems.BRASS_INGOT.get(), 2, 6, 20),
                new VillagerTrades.ItemsAndEmeraldsToItems(ModItems.TIN_INGOT.get(), 1, 1, ModItems.PEWTER_INGOT.get(), 1, 12, 20),
                new VillagerTrades.ItemsAndEmeraldsToItems(ModItems.ALUMINUM_INGOT.get(), 1, 1, ModItems.DURALUMIN_INGOT.get(), 1, 12, 20),
        };

        VillagerTrades.ItemListing[] metal5 = new VillagerTrades.ItemListing[]{
                new VillagerTrades.ItemsAndEmeraldsToItems(Items.GOLD_INGOT, 2, 2, ModItems.ELECTRUM_INGOT.get(), 2, 6, 25),
                new VillagerTrades.ItemsAndEmeraldsToItems(ModItems.CADMIUM_INGOT.get(), 2, 2, ModItems.BENDALLOY_INGOT.get(), 2, 6, 25),
                new VillagerTrades.ItemsAndEmeraldsToItems(ModItems.CHROMIUM_INGOT.get(), 2, 1, ModItems.NICROSIL_INGOT.get(), 2, 6, 25),
        };

                VillagerTrades.TRADES.put(METALLURGIST_PROFESSION.get(), toIntMap(ImmutableMap.of(1,metal1,2,metal2,3,metal3,4,metal4,5,metal5)));
    }

    private static Int2ObjectMap<VillagerTrades.ItemListing[]> toIntMap(ImmutableMap<Integer, VillagerTrades.ItemListing[]> pMap) {
        return new Int2ObjectOpenHashMap<>(pMap);
    }

    public static void register(IEventBus eventBus) {
        VILLAGER_PROFESSIONS.register(eventBus);
        POINT_OF_INTEREST_TYPES.register(eventBus);

    }

}
