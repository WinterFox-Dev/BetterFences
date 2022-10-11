package winterfox.betterfences.interfaces;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public interface IMixinFenceBlock {
    public void setDoesConnectToBlocks(boolean val, Level level, BlockPos pos, Direction dir);
    public Boolean getDoesConnectToBlocks(Level level, BlockPos pos, Direction dir);
}
