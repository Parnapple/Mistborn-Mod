package Parnapple.mistbornmod.entity;

import Parnapple.mistbornmod.MistbornBaseMod;
import Parnapple.mistbornmod.entity.custom.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, MistbornBaseMod.MOD_ID);

    public static final RegistryObject<EntityType<CoinProjectileEntity>> COIN_PROJECTILE = ENTITIES.register("coin_projectile", () -> EntityType.Builder
            .<CoinProjectileEntity>of(CoinProjectileEntity::new, MobCategory.MISC)
            .setShouldReceiveVelocityUpdates(true)
            .setUpdateInterval(20)
//            .setCustomClientFactory((spawnEntity, world) -> new CoinProjectileEntity(spawnEntity.getEntity(), world))
            .sized(0.25F, 0.25F)
            .build("coin_projectile"));

    public static final RegistryObject<EntityType<MistagerPewterEntity>> MISTAGER_PEWTER =
            ENTITIES.register("mistager_pewter",
                    () -> EntityType.Builder.of(MistagerPewterEntity::new, MobCategory.MONSTER)
                            .sized(0.6f, 1.95f)
                            .canSpawnFarFromPlayer()
                            .build(new ResourceLocation(MistbornBaseMod.MOD_ID, "mistager_pewter").toString()));

    public static final RegistryObject<EntityType<MistagerTinEntity>> MISTAGER_TIN =
            ENTITIES.register("mistager_tin",
                    () -> EntityType.Builder.of(MistagerTinEntity::new, MobCategory.MONSTER)
                            .sized(0.6f, 1.95f)
                            .canSpawnFarFromPlayer()
                            .clientTrackingRange(24)
                            .build(new ResourceLocation(MistbornBaseMod.MOD_ID, "mistager_tin").toString()));

    public static final RegistryObject<EntityType<MistagerBrassEntity>> MISTAGER_BRASS =
            ENTITIES.register("mistager_brass",
                    () -> EntityType.Builder.of(MistagerBrassEntity::new, MobCategory.MONSTER)
                            .sized(0.6f, 1.95f)
                            .canSpawnFarFromPlayer()
                            .build(new ResourceLocation(MistbornBaseMod.MOD_ID, "mistager_brass").toString()));

    public static final RegistryObject<EntityType<MistagerZincEntity>> MISTAGER_ZINC =
            ENTITIES.register("mistager_zinc",
                    () -> EntityType.Builder.of(MistagerZincEntity::new, MobCategory.MONSTER)
                            .sized(0.6f, 1.95f)
                            .canSpawnFarFromPlayer()
                            .build(new ResourceLocation(MistbornBaseMod.MOD_ID, "mistager_zinc").toString()));

    public static final RegistryObject<EntityType<MistagerChromiumEntity>> MISTAGER_CHROMIUM =
            ENTITIES.register("mistager_chromium",
                    () -> EntityType.Builder.of(MistagerChromiumEntity::new, MobCategory.MONSTER)
                            .sized(0.6f, 1.95f)
                            .canSpawnFarFromPlayer()
                            .build(new ResourceLocation(MistbornBaseMod.MOD_ID, "mistager_chromium").toString()));

    public static final RegistryObject<EntityType<MistagerCopperEntity>> MISTAGER_COPPER =
            ENTITIES.register("mistager_copper",
                    () -> EntityType.Builder.of(MistagerCopperEntity::new, MobCategory.MONSTER)
                            .sized(0.6f, 1.95f)
                            .canSpawnFarFromPlayer()
                            .build(new ResourceLocation(MistbornBaseMod.MOD_ID, "mistager_copper").toString()));

    public static final RegistryObject<EntityType<MistagerBronzeEntity>> MISTAGER_BRONZE =
            ENTITIES.register("mistager_bronze",
                    () -> EntityType.Builder.of(MistagerBronzeEntity::new, MobCategory.MONSTER)
                            .sized(0.6f, 1.95f)
                            .canSpawnFarFromPlayer()
                            .build(new ResourceLocation(MistbornBaseMod.MOD_ID, "mistager_bronze").toString()));

    public static final RegistryObject<EntityType<MistagerIronEntity>> MISTAGER_IRON =
            ENTITIES.register("mistager_iron",
                    () -> EntityType.Builder.of(MistagerIronEntity::new, MobCategory.MONSTER)
                            .sized(0.6f, 1.95f)
                            .canSpawnFarFromPlayer()
                            .build(new ResourceLocation(MistbornBaseMod.MOD_ID, "mistager_iron").toString()));

    public static final RegistryObject<EntityType<MistagerSteelEntity>> MISTAGER_STEEL =
            ENTITIES.register("mistager_steel",
                    () -> EntityType.Builder.of(MistagerSteelEntity::new, MobCategory.MONSTER)
                            .sized(0.6f, 1.95f)
                            .canSpawnFarFromPlayer()
                            .build(new ResourceLocation(MistbornBaseMod.MOD_ID, "mistager_steel").toString()));

    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }

}
