package dev.armadilllomike.zerginvasion.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class ZergInfectedLeavesBlock extends LeavesBlock {
    private final Block targetBlock;

    public ZergInfectedLeavesBlock(Settings settings, Block targetBlock) {
        super(settings);
        this.targetBlock = targetBlock;
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.scheduledTick(state, world, pos, random);
        
        int delayTime = random.nextBetween(0, 99);
        
        BlockPos.Mutable mutablePos = new BlockPos.Mutable();
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -1; z <= 1; z++) {
                    if (x == 0 && y == 0 && z == 0) continue;

                    mutablePos.set(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
                    BlockState neighborState = world.getBlockState(mutablePos);
                    Block neighborBlock = neighborState.getBlock();

                    if (SpreadingBlock.INFECTABLE_LEAVES.contains(neighborBlock)) {
                        world.setBlockState(mutablePos, ModBlocks.ZERG_INFECTED_LEAVES.getDefaultState()
                            .with(DISTANCE, state.get(DISTANCE))
                            .with(PERSISTENT, state.get(PERSISTENT)));
                    }
                }
            }
        }

        world.scheduleBlockTick(pos, this, 20 + delayTime);
    }
}