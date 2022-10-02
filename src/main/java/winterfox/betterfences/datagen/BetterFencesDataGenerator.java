package winterfox.betterfences.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import winterfox.betterfences.BetterFences;

@Mod.EventBusSubscriber(modid = BetterFences.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BetterFencesDataGenerator {
    @SubscribeEvent
    public static void registerDataProviders(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper fh = event.getExistingFileHelper();
        boolean forClient = event.includeClient();
        boolean forServer = event.includeServer();
        gen.addProvider(forClient, new BetterFencesBlockstateProvider(gen, BetterFences.MODID, fh));
    }

}