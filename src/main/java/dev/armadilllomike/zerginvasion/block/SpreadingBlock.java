package dev.armadilllomike.zerginvasion.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.util.math.random.Random;



public class SpreadingBlock extends Block {
    private final java.util.Random generator = new java.util.Random();
    private final Block targetBlock;

    public SpreadingBlock(Settings settings, Block targetBlock) {
        super(settings);
        this.targetBlock = targetBlock;
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        int delayTime = generator.nextInt(100); // Generates a random delay between 0 and 99
        super.scheduledTick(state, world, pos, random);

        for (BlockPos neighborPos : BlockPos.iterate(pos.add(-1, -1, -1), pos.add(1, 1, 1))) {
            if (neighborPos.equals(pos)) continue;

            BlockState neighborState = world.getBlockState(neighborPos);
            // Replace blocks with the new spreading block variant
            if (neighborState.isOf(Blocks.STONE)) {
                world.setBlockState(neighborPos, ModBlocks.SPREADING_BLOCK_VARIANT.getDefaultState());
            }
            if (neighborState.isOf(ModBlocks.TEST_BLOCK)) {
                world.setBlockState(neighborPos, ModBlocks.SPREADING_BLOCK.getDefaultState());
            }
            if (neighborState.isOf(Blocks.ACACIA_LOG)) {
                world.setBlockState(neighborPos, ModBlocks.ZERG_INFESTED_ACACIA_LOG.getDefaultState());
            }
        }

        world.scheduleBlockTick(pos, this, 20 + delayTime);
    }

    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        int delayTime = generator.nextInt(100); // Generates a random delay between 0 and 99
        super.onBlockAdded(state, world, pos, oldState, notify);
        if (!world.isClient) {
            ((ServerWorld) world).scheduleBlockTick(pos, this, 20 + delayTime);
        }
    }
}