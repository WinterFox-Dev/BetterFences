package winterfox.betterfences.interfaces;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import winterfox.betterfences.mixins.IMixinCrossCollisionBlock;

public interface IMixinIronBarsBlock {

    public void setDoesConnectToBlocks(boolean val, Level level, BlockPos pos, Direction dir);
    public Boolean getDoesConnectToBlocks(Level level, BlockPos pos, Direction dir);
}
