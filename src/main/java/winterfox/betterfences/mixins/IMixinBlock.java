package winterfox.betterfences.mixins;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Block.class)
public interface IMixinBlock {
    @Accessor("stateDefinition")
    StateDefinition<Block, BlockState> getStateDefinition();
}
