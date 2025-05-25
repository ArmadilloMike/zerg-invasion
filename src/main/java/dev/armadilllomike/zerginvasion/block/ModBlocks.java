package dev.armadilllomike.zerginvasion.block;

import dev.armadilllomike.zerginvasion.ZergInvasion;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block TEST_BLOCK = registerBlock("test_block",
            new Block(AbstractBlock.Settings.create().strength(3f)
                    .requiresTool()));

    public static final Block SPREADING_BLOCK = registerBlock("spreading_block",
            new SpreadingBlock(AbstractBlock.Settings.create().strength(2f).requiresTool(), TEST_BLOCK));

    public static final Block SPREADING_BLOCK_VARIANT = registerBlock("spreading_block_variant",
            new SpreadingBlock(AbstractBlock.Settings.create().strength(2f).requiresTool(), TEST_BLOCK));

    public static final Block ZERG_INFESTED_LOG = registerBlock("zerg_infested_log",
            new SpreadingBlock(AbstractBlock.Settings.create().strength(2f).requiresTool(), Blocks.ACACIA_LOG));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(ZergInvasion.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(ZergInvasion.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        ZergInvasion.LOGGER.info("Registering Mod Blocks for " + ZergInvasion.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SEARCH).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(TEST_BLOCK);
            fabricItemGroupEntries.add(SPREADING_BLOCK);
            fabricItemGroupEntries.add(SPREADING_BLOCK_VARIANT);
            fabricItemGroupEntries.add(ZERG_INFESTED_LOG);
        });
    }
    //TODO Add custom zerg multi blocks
}

