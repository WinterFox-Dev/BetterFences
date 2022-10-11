package winterfox.betterfences.mixins;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import winterfox.betterfences.BetterFences;
import winterfox.betterfences.interfaces.IMixinFenceBlock;

import java.util.Map;

@Mixin(FenceBlock.class)
public abstract class MixinFenceBlock extends MixinBlock implements IMixinFenceBlock, IMixinCrossCollisionBlock {
    private static final BooleanProperty ATTACHED_NORTH = BooleanProperty.create("attachednorth");
    private static final BooleanProperty ATTACHED_EAST = BooleanProperty.create("attachedeast");
    private static final BooleanProperty ATTACHED_SOUTH = BooleanProperty.create("attachedsouth");
    private static final BooleanProperty ATTACHED_WEST = BooleanProperty.create("attachedwest");
    private static final BooleanProperty ATTACHED_UP = BooleanProperty.create("attachedup");
    private static final BooleanProperty ATTACHED_DOWN = BooleanProperty.create("attachedown");

    private static final Map<Direction, BooleanProperty> ATTACHED_FROM_DIRECTION = ImmutableMap.copyOf(Util.make(Maps.newEnumMap(Direction.class), (map) -> {
        map.put(Direction.NORTH, ATTACHED_NORTH);
        map.put(Direction.EAST, ATTACHED_EAST);
        map.put(Direction.SOUTH, ATTACHED_SOUTH);
        map.put(Direction.WEST, ATTACHED_WEST);
        map.put(Direction.UP, ATTACHED_UP);
        map.put(Direction.DOWN, ATTACHED_DOWN);
    }));

    @Inject(method = "<init>", at = @At("TAIL"))
    private void betterFences_MixinFenceBlockConstructor(BlockBehaviour.Properties props, CallbackInfo ci) {
        registerDefaultState(getStateDefinition().any()
                .setValue(getNorth(), Boolean.FALSE)
                .setValue(getEast(), Boolean.FALSE)
                .setValue(getSouth(), Boolean.FALSE)
                .setValue(getWest(), Boolean.FALSE)
                .setValue(getWaterlogged(), Boolean.FALSE)
                .setValue(ATTACHED_NORTH, Boolean.TRUE)
                .setValue(ATTACHED_EAST, Boolean.TRUE)
                .setValue(ATTACHED_SOUTH, Boolean.TRUE)
                .setValue(ATTACHED_WEST, Boolean.TRUE)
                .setValue(ATTACHED_UP, Boolean.TRUE)
                .setValue(ATTACHED_DOWN, Boolean.TRUE));
    }

    @Inject(method = "createBlockStateDefinition(Lnet/minecraft/world/level/block/state/StateDefinition$Builder;)V", at = @At("TAIL"))
    private void betterFences_MixinFenceBlock_createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder, CallbackInfo ci) {
        pBuilder.add(ATTACHED_NORTH);
        pBuilder.add(ATTACHED_EAST);
        pBuilder.add(ATTACHED_WEST);
        pBuilder.add(ATTACHED_SOUTH);
        pBuilder.add(ATTACHED_UP);
        pBuilder.add(ATTACHED_DOWN);
    }

    @Overwrite
    public BlockState updateShape(BlockState fenceState, Direction dir, BlockState updateState, LevelAccessor levelAccessor, BlockPos fencePos, BlockPos updatePos) {
        if (fenceState.getValue(getWaterlogged())) {
            levelAccessor.scheduleTick(fencePos, Fluids.WATER, Fluids.WATER.getTickDelay(levelAccessor));
        }

        if(dir.getAxis().getPlane() == Direction.Plane.HORIZONTAL) {
            //west, east, north, south update to block
            if(fenceState.getValue(ATTACHED_FROM_DIRECTION.get(dir))) {
                //if its attached run the normal vanilla code
                return fenceState.setValue(getPropertyByDirection().get(dir), this.connectsTo(
                        updateState, updateState.isFaceSturdy(levelAccessor, updatePos, dir.getOpposite()), dir));
            } else {
                //if its not attached use modified connectsTo function
                return fenceState.setValue(getPropertyByDirection().get(dir), updateState.getBlock() instanceof FenceBlock);
            }
        } else {
            //top or bottom of block update, just pass the super as the fence structure doesn't change
            return super.updateShape(fenceState, dir, updateState, levelAccessor, fencePos, updatePos);
        }
    }

    @Shadow
    public boolean connectsTo(BlockState p_53330_, boolean p_53331_, Direction p_53332_) {
        throw new IllegalStateException("Mixin failed to shadow connectsTo()");
    }

    public BlockState getStateFromNeighbors(BlockPos pos, Level level, boolean val, Direction dir) {
        BlockPos west = pos.west();
        BlockState westState = level.getBlockState(west);
        BlockPos east = pos.east();
        BlockState eastState = level.getBlockState(east);
        BlockPos north = pos.north();
        BlockState northState = level.getBlockState(north);
        BlockPos south = pos.south();
        BlockState southState = level.getBlockState(south);

        return switch (dir) {
            case UP, DOWN -> level.getBlockState(pos)
                    .setValue(ATTACHED_FROM_DIRECTION.get(dir), val);
            case EAST -> level.getBlockState(pos)
                    .setValue(FenceBlock.EAST, val ? connectsTo(eastState, eastState.isFaceSturdy(level, east, Direction.WEST), dir) : (eastState.getBlock() instanceof FenceBlock))
                    .setValue(ATTACHED_FROM_DIRECTION.get(dir), val);
            case WEST -> level.getBlockState(pos)
                    .setValue(FenceBlock.WEST, val ? connectsTo(westState, westState.isFaceSturdy(level, west, Direction.EAST), dir) : (westState.getBlock() instanceof FenceBlock))
                    .setValue(ATTACHED_FROM_DIRECTION.get(dir), val);
            case NORTH -> level.getBlockState(pos)
                    .setValue(FenceBlock.NORTH, val ? connectsTo(northState, northState.isFaceSturdy(level, north, Direction.SOUTH), dir) : (northState.getBlock() instanceof FenceBlock))
                    .setValue(ATTACHED_FROM_DIRECTION.get(dir), val);
            case SOUTH -> level.getBlockState(pos)
                    .setValue(FenceBlock.SOUTH, val ? connectsTo(southState, southState.isFaceSturdy(level, south, Direction.NORTH) ,dir) : (southState.getBlock() instanceof FenceBlock))
                    .setValue(ATTACHED_FROM_DIRECTION.get(dir), val);
        };

    }

    @Override
    public void setDoesConnectToBlocks(boolean val, Level level, BlockPos pos, Direction dir) {
        level.setBlockAndUpdate(pos, getStateFromNeighbors(pos, level, val, dir));
    }

    @Override
    public Boolean getDoesConnectToBlocks(Level level, BlockPos pos, Direction dir) {
        return level.getBlockState(pos).getValue(ATTACHED_FROM_DIRECTION.get(dir));
    }
}
