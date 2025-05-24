package dev.armadilllomike.zerginvasion.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MultiBlockPlacerItem extends Item {
    private final Block[][][] structure;

    public MultiBlockPlacerItem(Settings settings, Block[][][] structure) {
        super(settings);
        this.structure = structure;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        if (world.isClient) return ActionResult.SUCCESS;

        BlockPos corePos = context.getBlockPos();
        ServerWorld serverWorld = (ServerWorld) world;

        // Place the multi-block structure
        for (int x = 0; x < structure.length; x++) {
            for (int y = 0; y < structure[x].length; y++) {
                for (int z = 0; z < structure[x][y].length; z++) {
                    Block block = structure[x][y][z];
                    if (block == null) continue;

                    BlockPos placePos = corePos.add(x, y, z);
                    BlockState state = block.getDefaultState();
                    serverWorld.setBlockState(placePos, state);
                }
            }
        }

        // Consume the item
        context.getStack().decrement(1);
        return ActionResult.SUCCESS;
    }
}
