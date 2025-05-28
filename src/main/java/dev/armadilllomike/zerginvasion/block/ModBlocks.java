package dev.armadilllomike.zerginvasion.block;

import dev.armadilllomike.zerginvasion.ZergInvasion;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.HashSet;
import java.util.Set;

public class ModBlocks {
    private static final Set<Block> REGISTERED_BLOCKS = new HashSet<>();

    public static final Block TEST_BLOCK = registerBlock("test_block",
            new Block(AbstractBlock.Settings.create().strength(3f).requiresTool()));

    public static final Block SPREADING_BLOCK = registerBlock("spreading_block",
            new SpreadingBlock(AbstractBlock.Settings.create().strength(2f).requiresTool(), TEST_BLOCK));

    public static final Block SPREADING_BLOCK_VARIANT = registerBlock("spreading_block_variant",
            new SpreadingBlock(AbstractBlock.Settings.create().strength(2f).requiresTool(), TEST_BLOCK));

    public static final Block ZERG_INFESTED_LOG = registerBlock("zerg_infested_log",
            new SpreadingBlock(AbstractBlock.Settings.create().strength(2f).requiresTool(), ModBlocks.SPREADING_BLOCK));

    public static final Block ZERG_INFESTED_LEAVES = registerBlock("zerg_infested_leaves",
        new ZergInfectedLeavesBlock(AbstractBlock.Settings.create()
        .strength(0.2f)
        .ticksRandomly()
        .sounds(BlockSoundGroup.GRASS)
        .nonOpaque()
        .allowsSpawning((state, world, pos, type) -> false)
        .suffocates((state, world, pos) -> false)
        .blockVision((state, world, pos) -> false),
    ModBlocks.ZERG_INFESTED_LOG));

    public static final Block ZERG_INFESTED_STONE = registerBlock("zerg_infested_stone",
            new SpreadingBlock(AbstractBlock.Settings.create().strength(1.5f).requiresTool(), ModBlocks.SPREADING_BLOCK));

    public static final Block ZERG_INFESTED_NETHERRACK = registerBlock("zerg_infested_netherrack",
            new SpreadingBlock(AbstractBlock.Settings.create().strength(1.5f).requiresTool(), ModBlocks.SPREADING_BLOCK));

    public static final Block ZERG_INFESTED_END_STONE = registerBlock("zerg_infested_end_stone",
            new SpreadingBlock(AbstractBlock.Settings.create().strength(1.5f).requiresTool(), ModBlocks.SPREADING_BLOCK));

    public static final Block ZERG_INFESTED_DIRT = registerBlock("zerg_infested_dirt",
            new SpreadingBlock(AbstractBlock.Settings.create().strength(1.5f).requiresTool(), ModBlocks.SPREADING_BLOCK));

    public static final Block ZERG_INFESTED_GRASS = registerBlock("zerg_infested_grass",
            new SpreadingBlock(AbstractBlock.Settings.create().strength(1.5f).requiresTool(), ModBlocks.SPREADING_BLOCK));

    private static Block registerBlock(String name, Block block) {
        REGISTERED_BLOCKS.add(block); // Track registered blocks
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(ZergInvasion.MOD_ID, name), block);
    }

    public static boolean isRegisteredBlock(Block block) {
        return REGISTERED_BLOCKS.contains(block);
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
            fabricItemGroupEntries.add(ZERG_INFESTED_LEAVES);
            fabricItemGroupEntries.add(ZERG_INFESTED_STONE);
            fabricItemGroupEntries.add(ZERG_INFESTED_NETHERRACK);
            fabricItemGroupEntries.add(ZERG_INFESTED_END_STONE);
            fabricItemGroupEntries.add(ZERG_INFESTED_DIRT);
            fabricItemGroupEntries.add(ZERG_INFESTED_GRASS);
        });
    }
}