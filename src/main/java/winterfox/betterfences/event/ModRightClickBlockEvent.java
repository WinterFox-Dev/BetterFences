package winterfox.betterfences.event;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import winterfox.betterfences.interfaces.IMixinFenceBlock;
import winterfox.betterfences.interfaces.IMixinIronBarsBlock;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModRightClickBlockEvent {
    @SubscribeEvent
    public static void handleAxesOnFence(PlayerInteractEvent.RightClickBlock event) {
        Level level = event.getLevel();
        Player player = event.getEntity();
        ItemStack stack = event.getItemStack();
        BlockPos pos = event.getPos();

        if (stack.getItem() instanceof AxeItem) {
            if (level.getBlockState(pos).getBlock() instanceof FenceBlock rightClicked) {
                if (event.getFace() != Direction.UP && event.getFace() != Direction.DOWN) {
                    if (!level.isClientSide() && event.getHand() == InteractionHand.MAIN_HAND) {
                        boolean newState = !(((IMixinFenceBlock) (Object) rightClicked).getDoesConnectToBlocks(level, pos, event.getFace()));
                        ((IMixinFenceBlock) (Object) rightClicked).setDoesConnectToBlocks(newState, level, pos, event.getFace());

                        stack.hurtAndBreak(1, player, (p) -> {
                            p.broadcastBreakEvent(p.getUsedItemHand());
                        });
                    }
                    if (level.isClientSide()) {
                        level.playSound(player, pos, SoundEvents.WOOD_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void handlePickaxeOnIronBars(PlayerInteractEvent.RightClickBlock event) {
        Level level = event.getLevel();
        Player player = event.getEntity();
        ItemStack stack = event.getItemStack();
        BlockPos pos = event.getPos();

        if (stack.getItem() instanceof PickaxeItem) {
            if (level.getBlockState(pos).getBlock() instanceof IronBarsBlock rightClicked) {
                if (event.getFace() != Direction.UP && event.getFace() != Direction.DOWN) {
                    if (!level.isClientSide() && event.getHand() == InteractionHand.MAIN_HAND) {
                        boolean newState = !(((IMixinIronBarsBlock) (Object) rightClicked).getDoesConnectToBlocks(level, pos, event.getFace()));
                        ((IMixinIronBarsBlock) (Object) rightClicked).setDoesConnectToBlocks(newState, level, pos, event.getFace());

                        stack.hurtAndBreak(1, player, (p) -> {
                            p.broadcastBreakEvent(p.getUsedItemHand());
                        });
                    }
                    if (level.isClientSide()) {
                        level.playSound(player, pos, SoundEvents.METAL_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
                    }
                }
            }
        }
    }
}
