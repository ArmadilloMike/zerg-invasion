package dev.armadilllomike.zerginvasion.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.TransparentBlock;  // Add this import
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.util.math.random.Random;

import java.util.Objects;
import java.util.Set;

public class SpreadingBlock extends TransparentBlock implements SpreadingBlockTranslucent {  // Change to extend TransparentBlock
    private static final Set<Block> INFECTABLE_LOGS = Set.of(
            Blocks.ACACIA_LOG,
            Blocks.MANGROVE_LOG,
            Blocks.JUNGLE_LOG,
            Blocks.CHERRY_LOG,
            Blocks.BIRCH_LOG,
            Blocks.DARK_OAK_LOG,
            Blocks.OAK_LOG,
            Blocks.SPRUCE_LOG
    );
    static final Set<Block> INFECTABLE_LEAVES = Set.of(
            Blocks.ACACIA_LEAVES,
            Blocks.MANGROVE_LEAVES,
            Blocks.JUNGLE_LEAVES,
            Blocks.CHERRY_LEAVES,
            Blocks.BIRCH_LEAVES,
            Blocks.DARK_OAK_LEAVES,
            Blocks.OAK_LEAVES,
            Blocks.SPRUCE_LEAVES,
            Blocks.AZALEA_LEAVES,
            Blocks.FLOWERING_AZALEA_LEAVES
    );

    private final Block targetBlock;

    public SpreadingBlock(Settings settings, Block targetBlock) {
        super(settings);
        this.targetBlock = targetBlock;
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        int delayTime = random.nextBetween(0, 99); // Using Minecraft's random system
        super.scheduledTick(state, world, pos, random);

        BlockPos.Mutable mutablePos = new BlockPos.Mutable();

        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -1; z <= 1; z++) {
                    if (x == 0 && y == 0 && z == 0) continue;

                    mutablePos.set(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
                    BlockState neighborState = world.getBlockState(mutablePos);
                    Block neighborBlock = neighborState.getBlock();

                    // Replace blocks with the new spreading block variant
                    if (neighborBlock == Blocks.STONE) {
                        world.setBlockState(mutablePos, ModBlocks.SPREADING_BLOCK_VARIANT.getDefaultState());
                    } else if ((Objects.equals(ModBlocks.TEST_BLOCK, neighborState.getBlock()))) {
                        world.setBlockState(mutablePos, ModBlocks.SPREADING_BLOCK.getDefaultState());
                    } else if (INFECTABLE_LOGS.contains(neighborBlock)) {
                        world.setBlockState(mutablePos, ModBlocks.ZERG_INFESTED_LOG.getDefaultState());
                    } else if (INFECTABLE_LEAVES.contains(neighborBlock)) {
                        world.setBlockState(mutablePos, ModBlocks.ZERG_INFECTED_LEAVES.getDefaultState());
                    }
                }
            }
        }

        world.scheduleBlockTick(pos, this, 20 + delayTime);
    }

    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        super.onBlockAdded(state, world, pos, oldState, notify);
        if (!world.isClient) {
            ServerWorld serverWorld = (ServerWorld) world;
            int delayTime = serverWorld.getRandom().nextBetween(0, 99);
            serverWorld.scheduleBlockTick(pos, this, 20 + delayTime);
        }
    }

}