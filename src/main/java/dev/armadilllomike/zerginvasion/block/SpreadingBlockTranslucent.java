package dev.armadilllomike.zerginvasion.block;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public interface SpreadingBlockTranslucent {
    public default boolean isTranslucent(BlockState state, BlockView world, BlockPos pos) {
        return true;
    }
}
