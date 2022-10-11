package winterfox.betterfences.mixins;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.CrossCollisionBlock;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(CrossCollisionBlock.class)
public interface IMixinCrossCollisionBlock {
    @Accessor("NORTH")
    BooleanProperty getNorth();

    @Accessor("SOUTH")
    BooleanProperty getSouth();

    @Accessor("EAST")
    BooleanProperty getEast();

    @Accessor("WEST")
    BooleanProperty getWest();

    @Accessor("WATERLOGGED")
    BooleanProperty getWaterlogged();

    @Accessor("PROPERTY_BY_DIRECTION")
    Map<Direction, BooleanProperty> getPropertyByDirection();
}
