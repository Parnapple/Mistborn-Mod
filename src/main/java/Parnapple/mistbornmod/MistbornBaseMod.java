package Parnapple.mistbornmod;

import Parnapple.mistbornmod.block.ModBlocks;
import Parnapple.mistbornmod.block.container.ModContainers;
import Parnapple.mistbornmod.crafting.ModRecipeSerializers;
import Parnapple.mistbornmod.event.FeruchemistCapEvents;
import Parnapple.mistbornmod.world.OreGeneration;
import Parnapple.mistbornmod.block.entity.ModBlockEntityTypes;
import Parnapple.mistbornmod.capability.ModCapabilities;
import Parnapple.mistbornmod.command.FeruchemyArgumentType;
import Parnapple.mistbornmod.effect.ModEffects;
import Parnapple.mistbornmod.entity.villager.VillagerProfessions;
import Parnapple.mistbornmod.item.ModItems;
import net.minecraft.commands.synchronization.ArgumentTypes;
import net.minecraft.commands.synchronization.EmptyArgumentSerializer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MistbornBaseMod.MOD_ID)
public class MistbornBaseMod
{

    public static final String MOD_ID = "mistbornmod";
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();



    public MistbornBaseMod() {
        // Register the setup method for modloading
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        eventBus.addListener(ModCapabilities::register);

        ModEffects.register(eventBus);
        VillagerProfessions.register(eventBus);
        ModItems.register(eventBus);
        ModBlocks.register(eventBus);
        ModBlockEntityTypes.register(eventBus);
        ModContainers.register(eventBus);
        ModRecipeSerializers.register(eventBus);



        eventBus.addListener(this::setup);

        /*
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
*/
        MinecraftForge.EVENT_BUS.register(FeruchemistCapEvents.class);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }





    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        event.enqueueWork(() -> {
            VillagerProfessions.fillTradeData();
            VillagerProfessions.registerMetallurgistPOI();
        });

        event.enqueueWork(OreGeneration::registerOres);

        ArgumentTypes.register("feruchemy_argument", FeruchemyArgumentType.class, new EmptyArgumentSerializer<>(() -> FeruchemyArgumentType.INSTANCE));
    }




    /*
    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.messageSupplier().get()).
                collect(Collectors.toList()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
        }
    }

     */
}
