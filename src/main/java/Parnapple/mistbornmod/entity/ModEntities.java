package Parnapple.mistbornmod.entity;

import Parnapple.mistbornmod.MistbornBaseMod;
import Parnapple.mistbornmod.entity.custom.MistagerPewterEntity;
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
                            .sized(1.0f, 2.0f)
                            .build(new ResourceLocation(MistbornBaseMod.MOD_ID, "mistager_pewter").toString()));

    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }

}
