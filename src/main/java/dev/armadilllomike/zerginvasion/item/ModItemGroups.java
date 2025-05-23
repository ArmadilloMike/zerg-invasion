package dev.armadilllomike.zerginvasion.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import dev.armadilllomike.zerginvasion.ZergInvasion;
import dev.armadilllomike.zerginvasion.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup TEST_ITEMS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(ZergInvasion.MOD_ID, "test_items"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.TEST_ITEM))
                    .displayName(Text.translatable("itemgroup.ZergInvasion.test_items_group"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.TEST_ITEM);
                    }).build());

    private static Object ModBlocks;
    public static final ItemGroup TEST_BLOCK_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(ZergInvasion.MOD_ID, "test_blocks"),
            FabricItemGroup.builder().icon(() -> new ItemStack(dev.armadilllomike.zerginvasion.block.ModBlocks.TEST_BLOCK))
                    .displayName(Text.translatable("itemgroup.ZergInvasion.test_block_group"))
                    .entries((displayContext, entries) -> {
                        entries.add(dev.armadilllomike.zerginvasion.block.ModBlocks.TEST_BLOCK);
                    }).build());
    public static final ItemGroup SPREADING_BLOCK_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(ZergInvasion.MOD_ID, "spreading_blocks"),
            FabricItemGroup.builder().icon(() -> new ItemStack(dev.armadilllomike.zerginvasion.block.ModBlocks.SPREADING_BLOCK))
                    .displayName(Text.translatable("itemgroup.ZergInvasion.spreading_block_group"))
                    .entries((displayContext, entries) -> {
                        entries.add(dev.armadilllomike.zerginvasion.block.ModBlocks.SPREADING_BLOCK);
                        entries.add(dev.armadilllomike.zerginvasion.block.ModBlocks.SPREADING_BLOCK_VARIANT);
                    }).build());
    public static final ItemGroup INFESTED_BLOCK_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(ZergInvasion.MOD_ID, "infested_blocks"),
            FabricItemGroup.builder().icon(() -> new ItemStack(dev.armadilllomike.zerginvasion.block.ModBlocks.SPREADING_BLOCK))
                    .displayName(Text.translatable("itemgroup.ZergInvasion.infested_block_group"))
                    .entries((displayContext, entries) -> {
                        entries.add(dev.armadilllomike.zerginvasion.block.ModBlocks.ZERG_INFESTED_ACACIA_LOG);
                    }).build());


    public static void registerItemGroups() {
        ZergInvasion.LOGGER.info("Registering Item Groups for " + ZergInvasion.MOD_ID);
    }
}
