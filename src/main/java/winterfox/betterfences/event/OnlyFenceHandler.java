package winterfox.betterfences.event;

import java.util.EventListenerProxy;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.monster.warden.WardenAi;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.WallSide;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegisterEvent;
import winterfox.betterfences.block.OnlyWallBlock;
import winterfox.betterfences.registry.BlockRegistry;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class OnlyFenceHandler {
    static Map<Block, Block> fence = new HashMap<Block, Block>();
    static Map<Block, Block> wall = new HashMap<Block, Block>();

    private static void createFenceMap() {
        fence.put(Blocks.ACACIA_FENCE, BlockRegistry.ONLYFENCE_ACACIA.get());
        fence.put(Blocks.BIRCH_FENCE, BlockRegistry.ONLYFENCE_BIRCH.get());
        fence.put(Blocks.CRIMSON_FENCE, BlockRegistry.ONLYFENCE_CRIMSON.get());
        fence.put(Blocks.DARK_OAK_FENCE, BlockRegistry.ONLYFENCE_DARK_OAK.get());
        fence.put(Blocks.JUNGLE_FENCE, BlockRegistry.ONLYFENCE_JUNGLE.get());
        fence.put(Blocks.MANGROVE_FENCE, BlockRegistry.ONLYFENCE_MANGROVE.get());
        fence.put(Blocks.OAK_FENCE, BlockRegistry.ONLYFENCE_OAK.get());
        fence.put(Blocks.SPRUCE_FENCE, BlockRegistry.ONLYFENCE_SPRUCE.get());
        fence.put(Blocks.WARPED_FENCE, BlockRegistry.ONLYFENCE_WARPED.get());

        fence.put(BlockRegistry.ONLYFENCE_ACACIA.get(), Blocks.ACACIA_FENCE);
        fence.put(BlockRegistry.ONLYFENCE_BIRCH.get(), Blocks.BIRCH_FENCE);
        fence.put(BlockRegistry.ONLYFENCE_CRIMSON.get(), Blocks.CRIMSON_FENCE);
        fence.put(BlockRegistry.ONLYFENCE_DARK_OAK.get(), Blocks.DARK_OAK_FENCE);
        fence.put(BlockRegistry.ONLYFENCE_JUNGLE.get(), Blocks.JUNGLE_FENCE);
        fence.put(BlockRegistry.ONLYFENCE_MANGROVE.get(), Blocks.MANGROVE_FENCE);
        fence.put(BlockRegistry.ONLYFENCE_OAK.get(), Blocks.OAK_FENCE);
        fence.put(BlockRegistry.ONLYFENCE_SPRUCE.get(), Blocks.SPRUCE_FENCE);
        fence.put(BlockRegistry.ONLYFENCE_WARPED.get(), Blocks.WARPED_FENCE);
    }

    private static void createWallMap() {
        wall.put(BlockRegistry.onlyfence_cobblestone_wall.get(), Blocks.COBBLESTONE_WALL);
        wall.put(BlockRegistry.onlyfence_mossy_cobblestone_wall.get(), Blocks.MOSSY_COBBLESTONE_WALL);
        wall.put(BlockRegistry.onlyfence_brick_wall.get(), Blocks.BRICK_WALL);
        wall.put(BlockRegistry.onlyfence_prismarine_wall.get(), Blocks.PRISMARINE_WALL);
        wall.put(BlockRegistry.onlyfence_red_sandstone_wall.get(), Blocks.RED_SANDSTONE_WALL);
        wall.put(BlockRegistry.onlyfence_mossy_stone_brick_wall.get(), Blocks.MOSSY_STONE_BRICK_WALL);
        wall.put(BlockRegistry.onlyfence_granite_wall.get(), Blocks.GRANITE_WALL);
        wall.put(BlockRegistry.onlyfence_stone_brick_wall.get(), Blocks.STONE_BRICK_WALL);
        wall.put(BlockRegistry.onlyfence_nether_brick_wall.get(), Blocks.NETHER_BRICK_WALL);
        wall.put(BlockRegistry.onlyfence_andesite_wall.get(), Blocks.ANDESITE_WALL);
        wall.put(BlockRegistry.onlyfence_red_nether_brick_wall.get(), Blocks.RED_NETHER_BRICK_WALL);
        wall.put(BlockRegistry.onlyfence_sandstone_wall.get(), Blocks.SANDSTONE_WALL);
        wall.put(BlockRegistry.onlyfence_end_stone_brick_wall.get(), Blocks.END_STONE_BRICK_WALL);
        wall.put(BlockRegistry.onlyfence_diorite_wall.get(), Blocks.DIORITE_WALL);
        wall.put(BlockRegistry.onlyfence_blackstone_wall.get(), Blocks.BLACKSTONE_WALL);
        wall.put(BlockRegistry.onlyfence_polished_blackstone_brick_wall.get(), Blocks.POLISHED_BLACKSTONE_BRICK_WALL);
        wall.put(BlockRegistry.onlyfence_polished_blackstone_wall.get(), Blocks.POLISHED_BLACKSTONE_WALL);
        wall.put(BlockRegistry.onlyfence_cobbled_deepslate_wall.get(), Blocks.COBBLED_DEEPSLATE_WALL);
        wall.put(BlockRegistry.onlyfence_polished_deepslate_wall.get(), Blocks.POLISHED_DEEPSLATE_WALL);
        wall.put(BlockRegistry.onlyfence_deepslate_tile_wall.get(), Blocks.DEEPSLATE_TILE_WALL);
        wall.put(BlockRegistry.onlyfence_deepslate_brick_wall.get(), Blocks.DEEPSLATE_BRICK_WALL);
        wall.put(BlockRegistry.onlyfence_mud_brick_wall.get(), Blocks.MUD_BRICK_WALL);

        wall.put(Blocks.COBBLESTONE_WALL, BlockRegistry.onlyfence_cobblestone_wall.get());
        wall.put(Blocks.MOSSY_COBBLESTONE_WALL, BlockRegistry.onlyfence_mossy_cobblestone_wall.get());
        wall.put(Blocks.BRICK_WALL, BlockRegistry.onlyfence_brick_wall.get());
        wall.put(Blocks.PRISMARINE_WALL, BlockRegistry.onlyfence_prismarine_wall.get());
        wall.put(Blocks.RED_SANDSTONE_WALL, BlockRegistry.onlyfence_red_sandstone_wall.get());
        wall.put(Blocks.MOSSY_STONE_BRICK_WALL, BlockRegistry.onlyfence_mossy_stone_brick_wall.get());
        wall.put(Blocks.GRANITE_WALL, BlockRegistry.onlyfence_granite_wall.get());
        wall.put(Blocks.STONE_BRICK_WALL, BlockRegistry.onlyfence_stone_brick_wall.get());
        wall.put(Blocks.NETHER_BRICK_WALL, BlockRegistry.onlyfence_nether_brick_wall.get());
        wall.put(Blocks.ANDESITE_WALL, BlockRegistry.onlyfence_andesite_wall.get());
        wall.put(Blocks.RED_NETHER_BRICK_WALL, BlockRegistry.onlyfence_red_nether_brick_wall.get());
        wall.put(Blocks.SANDSTONE_WALL, BlockRegistry.onlyfence_sandstone_wall.get());
        wall.put(Blocks.END_STONE_BRICK_WALL, BlockRegistry.onlyfence_end_stone_brick_wall.get());
        wall.put(Blocks.DIORITE_WALL, BlockRegistry.onlyfence_diorite_wall.get());
        wall.put(Blocks.BLACKSTONE_WALL, BlockRegistry.onlyfence_blackstone_wall.get());
        wall.put(Blocks.POLISHED_BLACKSTONE_BRICK_WALL, BlockRegistry.onlyfence_polished_blackstone_brick_wall.get());
        wall.put(Blocks.POLISHED_BLACKSTONE_WALL, BlockRegistry.onlyfence_polished_blackstone_wall.get());
        wall.put(Blocks.COBBLED_DEEPSLATE_WALL, BlockRegistry.onlyfence_cobbled_deepslate_wall.get());
        wall.put(Blocks.POLISHED_DEEPSLATE_WALL, BlockRegistry.onlyfence_polished_deepslate_wall.get());
        wall.put(Blocks.DEEPSLATE_TILE_WALL, BlockRegistry.onlyfence_deepslate_tile_wall.get());
        wall.put(Blocks.DEEPSLATE_BRICK_WALL, BlockRegistry.onlyfence_deepslate_brick_wall.get());
        wall.put(Blocks.MUD_BRICK_WALL, BlockRegistry.onlyfence_mud_brick_wall.get());
    }

    @SubscribeEvent
    public static void handlePickaxeOnWall(PlayerInteractEvent.RightClickBlock event) {
        if(wall.isEmpty()) { createWallMap(); }

        Level level = event.getLevel();
        Player player = event.getEntity();
        ItemStack stack = event.getItemStack();
        BlockPos pos = event.getPos();

        if(stack.getItem() instanceof PickaxeItem) {
            if(level.getBlockState(pos).getBlock() instanceof WallBlock rightClicked) {
                level.setBlockAndUpdate(pos, wall.get(rightClicked).defaultBlockState());
                level.updateNeighborsAt(pos.west(), null);
                level.updateNeighborsAt(pos.east(), null);
                level.updateNeighborsAt(pos.south(), null);
                level.updateNeighborsAt(pos.north(), null);
                level.updateNeighborsAt(pos.above(), null);
                level.updateNeighborsAt(pos.below(), null);
                level.playSound(player, pos, SoundEvents.STONE_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
                if(player instanceof ServerPlayer) {
                    CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer) player, pos, stack);
                }
                stack.hurtAndBreak(1, player, (p) -> {
                    p.broadcastBreakEvent(p.getUsedItemHand());
                });

            }
        }
    }

    @SubscribeEvent
    public static void handleAxesOnFence(PlayerInteractEvent.RightClickBlock event) {
        if(fence.isEmpty()) { createFenceMap(); }

        Level level = event.getLevel();
        Player player = event.getEntity();
        ItemStack stack = event.getItemStack();
        BlockPos pos = event.getPos();

        if(stack.getItem() instanceof AxeItem) {
            if(level.getBlockState(pos).getBlock() instanceof FenceBlock rightClicked) {
                level.setBlockAndUpdate(pos, setBlockStateFromBlock(event, (FenceBlock)fence.get(rightClicked)));
                level.playSound(player, pos, SoundEvents.WOOD_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
                if(player instanceof ServerPlayer) {
                    CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer) player, pos, stack);
                }
                stack.hurtAndBreak(1, player, (p) -> {
                    p.broadcastBreakEvent(p.getUsedItemHand());
                });

            }
        }
    }

    public static BlockState setBlockStateFromBlock(PlayerInteractEvent.RightClickBlock event, FenceBlock block) {
        BlockPos west = event.getPos().west();
        BlockState westState = event.getLevel().getBlockState(west);
        BlockPos east = event.getPos().east();
        BlockState eastState = event.getLevel().getBlockState(east);
        BlockPos north = event.getPos().north();
        BlockState northState = event.getLevel().getBlockState(north);
        BlockPos south = event.getPos().south();
        BlockState southState = event.getLevel().getBlockState(south);
        return block.defaultBlockState()
                .setValue(FenceBlock.NORTH, block.connectsTo(northState, northState.isFaceSturdy(event.getLevel(), north, Direction.SOUTH), Direction.SOUTH))
                .setValue(FenceBlock.EAST, block.connectsTo(eastState, eastState.isFaceSturdy(event.getLevel(), east, Direction.WEST), Direction.WEST))
                .setValue(FenceBlock.WEST, block.connectsTo(westState, westState.isFaceSturdy(event.getLevel(), west, Direction.EAST), Direction.EAST))
                .setValue(FenceBlock.SOUTH, block.connectsTo(southState, southState.isFaceSturdy(event.getLevel(), south, Direction.NORTH), Direction.NORTH));
    }

}
