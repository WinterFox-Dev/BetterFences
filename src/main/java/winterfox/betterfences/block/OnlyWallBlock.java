package winterfox.betterfences.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;

public class OnlyWallBlock extends WallBlock {

    private static Block superWall;

    public OnlyWallBlock(Properties props, Block superWall) {
        super(props);
        this.superWall = superWall;
    }

    public static boolean isExceptionForConnection(BlockState connectingBlock) {
        return !(connectingBlock.getBlock() instanceof WallBlock);
    }

    private boolean connectsTo(BlockState p_58021_, boolean p_58022_, Direction p_58023_) {
        Block block = p_58021_.getBlock();
        boolean flag = block instanceof FenceGateBlock && FenceGateBlock.connectsToDirection(p_58021_, p_58023_);
        return p_58021_.is(BlockTags.WALLS) || !isExceptionForConnection(p_58021_) && p_58022_ || block instanceof IronBarsBlock || flag;
    }


   /* @Override
    public ItemStack getCloneItemStack(BlockGetter p_49823_, BlockPos p_49824_, BlockState p_49825_) {
        return new ItemStack(superWall);
    }*/

    private boolean isSameWall(BlockState state) {
        return state.is(BlockTags.WALLS) && state.is(BlockTags.WALLS) == this.defaultBlockState().is(BlockTags.WALLS);
    }
}
