package winterfox.betterfences.registry;

import java.util.function.Supplier;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;
import winterfox.betterfences.BetterFences;
import winterfox.betterfences.block.OnlyFenceBlock;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BlockRegistry {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
            BetterFences.MODID);

    public static final RegistryObject<Block> ONLYFENCE_OAK = BLOCKS.register("onlyfence_oak",
            () -> new OnlyFenceBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD), Blocks.OAK_FENCE));
    public static final RegistryObject<Block> ONLYFENCE_ACACIA = BLOCKS.register("onlyfence_acacia",
            () -> new OnlyFenceBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD), Blocks.ACACIA_FENCE));
    public static final RegistryObject<Block> ONLYFENCE_DARK_OAK = BLOCKS.register("onlyfence_dark_oak",
            () -> new OnlyFenceBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD), Blocks.DARK_OAK_FENCE));
    public static final RegistryObject<Block> ONLYFENCE_SPRUCE = BLOCKS.register("onlyfence_spruce",
            () -> new OnlyFenceBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD), Blocks.SPRUCE_FENCE));
    public static final RegistryObject<Block> ONLYFENCE_BIRCH = BLOCKS.register("onlyfence_birch",
            () -> new OnlyFenceBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD), Blocks.BIRCH_FENCE));
    public static final RegistryObject<Block> ONLYFENCE_JUNGLE = BLOCKS.register("onlyfence_jungle",
            () -> new OnlyFenceBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD), Blocks.JUNGLE_FENCE));
    public static final RegistryObject<Block> ONLYFENCE_CRIMSON = BLOCKS.register("onlyfence_crimson",
            () -> new OnlyFenceBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD), Blocks.CRIMSON_FENCE));
    public static final RegistryObject<Block> ONLYFENCE_WARPED = BLOCKS.register("onlyfence_warped",
            () -> new OnlyFenceBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD), Blocks.WARPED_FENCE));
    public static final RegistryObject<Block> ONLYFENCE_MANGROVE = BLOCKS.register("onlyfence_mangrove",
            () -> new OnlyFenceBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD), Blocks.MANGROVE_FENCE));

    @SubscribeEvent
    public static void onRegisterItems(final RegisterEvent event) {
        if(event.getRegistryKey().equals(ForgeRegistries.Keys.ITEMS)) {
            BLOCKS.getEntries().forEach((blockRegistryObject) -> {
                Block block = blockRegistryObject.get();
                Item.Properties properties = new Item.Properties();
                Supplier<Item> blockItemFactory = () -> new BlockItem(block, properties);
                event.register(ForgeRegistries.Keys.ITEMS, blockRegistryObject.getId(), blockItemFactory);
            });
        }
    }
}
