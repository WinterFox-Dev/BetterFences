package winterfox.betterfences.mixins;

import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Block.class)
public abstract class MixinBlock extends MixinBlockBehaviour implements IMixinBlock{
    @Shadow
    public final BlockState defaultBlockState() {
        throw new IllegalStateException("Mixin failed to shadow defaultBlockState()");
    }

    @Shadow
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_49915_) {
        throw new IllegalStateException("Mixin failed to shadow createBlockStateDefinition()");
    }

    @Shadow
    public BlockState getStateForPlacement(BlockPlaceContext p_49820_) {
        throw new IllegalStateException("Mixin failed to shadow getStateForPlacement()");
    }

    @Shadow
    protected final void registerDefaultState(BlockState p_49960_) {
        throw new IllegalStateException("Mixin failed to shadow registerDefaultState()");
    }

}
