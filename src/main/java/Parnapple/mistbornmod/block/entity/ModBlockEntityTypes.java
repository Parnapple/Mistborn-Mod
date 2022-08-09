package Parnapple.mistbornmod.block.entity;

import Parnapple.mistbornmod.MistbornBaseMod;
import Parnapple.mistbornmod.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlockEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, MistbornBaseMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<MetallurgyFurnaceBlockEntity>> METALLURGY_FURNACE_BLOCK_ENTITY = BLOCK_ENTITY_TYPES.register("metallurgy_furnace_block_entity",
            () -> BlockEntityType.Builder.of(MetallurgyFurnaceBlockEntity::new, ModBlocks.METALLURGY_FURNACE_BLOCK.get()).build(null));


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITY_TYPES.register(eventBus);
    }
}
