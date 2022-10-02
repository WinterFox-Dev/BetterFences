package winterfox.betterfences.registry;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;
import winterfox.betterfences.BetterFences;
import winterfox.betterfences.block.OnlyFenceBlock;
import winterfox.betterfences.block.OnlyWallBlock;
import winterfox.betterfences.registry.ItemRegistry;

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

    public static final RegistryObject<Block> onlyfence_cobblestone_wall = BLOCKS.register("onlyfence_cobblestone_wall",
            () -> new OnlyWallBlock(Block.Properties.copy(Blocks.COBBLESTONE_WALL), Blocks.COBBLESTONE_WALL));

    public static final RegistryObject<Block> onlyfence_mossy_cobblestone_wall = BLOCKS.register("onlyfence_mossy_cobblestone_wall",
            () -> new OnlyWallBlock(Block.Properties.copy(Blocks.MOSSY_COBBLESTONE_WALL), Blocks.MOSSY_COBBLESTONE_WALL));

    public static final RegistryObject<Block> onlyfence_brick_wall = BLOCKS.register("onlyfence_brick_wall",
            () -> new OnlyWallBlock(Block.Properties.copy(Blocks.BRICK_WALL), Blocks.BRICK_WALL));

    public static final RegistryObject<Block> onlyfence_prismarine_wall = BLOCKS.register("onlyfence_prismarine_wall",
            () -> new OnlyWallBlock(Block.Properties.copy(Blocks.PRISMARINE_WALL), Blocks.PRISMARINE_WALL));

    public static final RegistryObject<Block> onlyfence_red_sandstone_wall = BLOCKS.register("onlyfence_red_sandstone_wall",
            () -> new OnlyWallBlock(Block.Properties.copy(Blocks.RED_SANDSTONE_WALL), Blocks.RED_SANDSTONE_WALL));

    public static final RegistryObject<Block> onlyfence_mossy_stone_brick_wall = BLOCKS.register("onlyfence_mossy_stone_brick_wall",
            () -> new OnlyWallBlock(Block.Properties.copy(Blocks.MOSSY_STONE_BRICK_WALL), Blocks.MOSSY_STONE_BRICK_WALL));

    public static final RegistryObject<Block> onlyfence_granite_wall = BLOCKS.register("onlyfence_granite_wall",
            () -> new OnlyWallBlock(Block.Properties.copy(Blocks.GRANITE_WALL), Blocks.GRANITE_WALL));

    public static final RegistryObject<Block> onlyfence_stone_brick_wall = BLOCKS.register("onlyfence_stone_brick_wall",
            () -> new OnlyWallBlock(Block.Properties.copy(Blocks.STONE_BRICK_WALL), Blocks.STONE_BRICK_WALL));

    public static final RegistryObject<Block> onlyfence_nether_brick_wall = BLOCKS.register("onlyfence_nether_brick_wall",
            () -> new OnlyWallBlock(Block.Properties.copy(Blocks.NETHER_BRICK_WALL), Blocks.NETHER_BRICK_WALL));

    public static final RegistryObject<Block> onlyfence_andesite_wall = BLOCKS.register("onlyfence_andesite_wall",
            () -> new OnlyWallBlock(Block.Properties.copy(Blocks.ANDESITE_WALL), Blocks.ANDESITE_WALL));

    public static final RegistryObject<Block> onlyfence_red_nether_brick_wall = BLOCKS.register("onlyfence_red_nether_brick_wall",
            () -> new OnlyWallBlock(Block.Properties.copy(Blocks.RED_NETHER_BRICK_WALL), Blocks.RED_NETHER_BRICK_WALL));

    public static final RegistryObject<Block> onlyfence_sandstone_wall = BLOCKS.register("onlyfence_sandstone_wall",
            () -> new OnlyWallBlock(Block.Properties.copy(Blocks.SANDSTONE_WALL), Blocks.SANDSTONE_WALL));

    public static final RegistryObject<Block> onlyfence_end_stone_brick_wall = BLOCKS.register("onlyfence_end_stone_brick_wall",
            () -> new OnlyWallBlock(Block.Properties.copy(Blocks.END_STONE_BRICK_WALL), Blocks.END_STONE_BRICK_WALL));

    public static final RegistryObject<Block> onlyfence_diorite_wall = BLOCKS.register("onlyfence_diorite_wall",
            () -> new OnlyWallBlock(Block.Properties.copy(Blocks.DIORITE_WALL), Blocks.DIORITE_WALL));

    public static final RegistryObject<Block> onlyfence_blackstone_wall = BLOCKS.register("onlyfence_blackstone_wall",
            () -> new OnlyWallBlock(Block.Properties.copy(Blocks.BLACKSTONE_WALL), Blocks.BLACKSTONE_WALL));

    public static final RegistryObject<Block> onlyfence_polished_blackstone_brick_wall = BLOCKS.register("onlyfence_polished_blackstone_brick_wall",
            () -> new OnlyWallBlock(Block.Properties.copy(Blocks.POLISHED_BLACKSTONE_BRICK_WALL), Blocks.POLISHED_BLACKSTONE_BRICK_WALL));

    public static final RegistryObject<Block> onlyfence_polished_blackstone_wall = BLOCKS.register("onlyfence_polished_blackstone_wall",
            () -> new OnlyWallBlock(Block.Properties.copy(Blocks.POLISHED_BLACKSTONE_WALL), Blocks.POLISHED_BLACKSTONE_WALL));

    public static final RegistryObject<Block> onlyfence_cobbled_deepslate_wall = BLOCKS.register("onlyfence_cobbled_deepslate_wall",
            () -> new OnlyWallBlock(Block.Properties.copy(Blocks.COBBLED_DEEPSLATE_WALL), Blocks.COBBLED_DEEPSLATE_WALL));

    public static final RegistryObject<Block> onlyfence_polished_deepslate_wall = BLOCKS.register("onlyfence_polished_deepslate_wall",
            () -> new OnlyWallBlock(Block.Properties.copy(Blocks.POLISHED_DEEPSLATE_WALL), Blocks.POLISHED_DEEPSLATE_WALL));

    public static final RegistryObject<Block> onlyfence_deepslate_tile_wall = BLOCKS.register("onlyfence_deepslate_tile_wall",
            () -> new OnlyWallBlock(Block.Properties.copy(Blocks.DEEPSLATE_TILE_WALL), Blocks.DEEPSLATE_TILE_WALL));

    public static final RegistryObject<Block> onlyfence_deepslate_brick_wall = BLOCKS.register("onlyfence_deepslate_brick_wall",
            () -> new OnlyWallBlock(Block.Properties.copy(Blocks.DEEPSLATE_BRICK_WALL), Blocks.DEEPSLATE_BRICK_WALL));

    public static final RegistryObject<Block> onlyfence_mud_brick_wall = BLOCKS.register("onlyfence_mud_brick_wall",
            () -> new OnlyWallBlock(Block.Properties.copy(Blocks.MUD_BRICK_WALL), Blocks.MUD_BRICK_WALL));







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
