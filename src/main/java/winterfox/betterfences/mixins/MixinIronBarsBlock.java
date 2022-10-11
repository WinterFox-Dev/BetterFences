package winterfox.betterfences.mixins;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Fluids;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import winterfox.betterfences.interfaces.IMixinIronBarsBlock;

import java.util.Map;

@Mixin(IronBarsBlock.class)
public abstract class MixinIronBarsBlock extends MixinBlock implements IMixinCrossCollisionBlock, IMixinIronBarsBlock {
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
    private void betterFences_MixinIronBarsBlock_Constructor(BlockBehaviour.Properties props, CallbackInfo ci) {
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
    private void betterFences_MixinIronBarsBlock_createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder, CallbackInfo ci) {
        pBuilder.add(ATTACHED_NORTH);
        pBuilder.add(ATTACHED_EAST);
        pBuilder.add(ATTACHED_WEST);
        pBuilder.add(ATTACHED_SOUTH);
        pBuilder.add(ATTACHED_UP);
        pBuilder.add(ATTACHED_DOWN);
    }

    @Overwrite
    public BlockState updateShape(BlockState barState, Direction dir, BlockState updateState, LevelAccessor levelAccessor, BlockPos fencePos, BlockPos updatePos) {
        if (barState.getValue(getWaterlogged())) {
            levelAccessor.scheduleTick(fencePos, Fluids.WATER, Fluids.WATER.getTickDelay(levelAccessor));
        }

        if(dir.getAxis().getPlane() == Direction.Plane.HORIZONTAL) {
            //west, east, north, south update to block
            if(barState.getValue(ATTACHED_FROM_DIRECTION.get(dir))) {
                //if its attached run the normal vanilla code
                return barState.setValue(getPropertyByDirection().get(dir), this.attachsTo(
                        updateState, updateState.isFaceSturdy(levelAccessor, updatePos, dir.getOpposite())));
            } else {
                //if its not attached use modified connectsTo function
                return barState.setValue(getPropertyByDirection().get(dir), updateState.getBlock() instanceof IronBarsBlock);
            }
        } else {
            //top or bottom of block update, just pass the super as the fence structure doesn't change
            return super.updateShape(barState, dir, updateState, levelAccessor, fencePos, updatePos);
        }
    }

    @Shadow
    public boolean attachsTo(BlockState p_53330_, boolean p_53331_) {
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
                    .setValue(IronBarsBlock.EAST, val ? attachsTo(eastState, eastState.isFaceSturdy(level, east, Direction.WEST)) : (eastState.getBlock() instanceof IronBarsBlock))
                    .setValue(ATTACHED_FROM_DIRECTION.get(dir), val);
            case WEST -> level.getBlockState(pos)
                    .setValue(IronBarsBlock.WEST, val ? attachsTo(westState, westState.isFaceSturdy(level, west, Direction.EAST)) : (westState.getBlock() instanceof IronBarsBlock))
                    .setValue(ATTACHED_FROM_DIRECTION.get(dir), val);
            case NORTH -> level.getBlockState(pos)
                    .setValue(IronBarsBlock.NORTH, val ? attachsTo(northState, northState.isFaceSturdy(level, north, Direction.SOUTH)) : (northState.getBlock() instanceof IronBarsBlock))
                    .setValue(ATTACHED_FROM_DIRECTION.get(dir), val);
            case SOUTH -> level.getBlockState(pos)
                    .setValue(IronBarsBlock.SOUTH, val ? attachsTo(southState, southState.isFaceSturdy(level, south, Direction.NORTH)) : (southState.getBlock() instanceof IronBarsBlock))
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
