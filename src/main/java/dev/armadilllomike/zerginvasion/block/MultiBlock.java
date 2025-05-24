package dev.armadilllomike.zerginvasion.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public class MultiBlock {
    private final Block coreBlock;
    private final Block[][][] structure;

    public MultiBlock(Block coreBlock, Block[][][] structure) {
        this.coreBlock = coreBlock;
        this.structure = structure;
    }

    public boolean isValidStructure(ServerWorld world, BlockPos corePos) {
        for (int x = 0; x < structure.length; x++) {
            for (int y = 0; y < structure[x].length; y++) {
                for (int z = 0; z < structure[x][y].length; z++) {
                    Block expectedBlock = structure[x][y][z];
                    if (expectedBlock == null) continue;

                    BlockPos checkPos = corePos.add(x - 1, y - 1, z - 1); // Adjust for structure offset
                    BlockState state = world.getBlockState(checkPos);

                    if (!state.isOf(expectedBlock)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void activate(ServerWorld world, BlockPos corePos) {
        if (isValidStructure(world, corePos)) {
            // Perform activation logic (e.g., replace blocks, spawn entities, etc.)
            world.setBlockState(corePos, coreBlock.getDefaultState());
        }
    }
}