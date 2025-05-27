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
    private static Object ModBlocks;
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
                        entries.add(dev.armadilllomike.zerginvasion.block.ModBlocks.ZERG_INFESTED_LOG);
                        entries.add(dev.armadilllomike.zerginvasion.block.ModBlocks.ZERG_INFECTED_LEAVES);
                    }).build());
    public static final ItemGroup MULTIBLOCK_PLACERS = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(ZergInvasion.MOD_ID, "multiblock_placers"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.MULTI_BLOCK_PLACER))
                    .displayName(Text.translatable("itemgroup.ZergInvasion.multiblock_placers"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.MULTI_BLOCK_PLACER);
                    }).build());


    public static void registerItemGroups() {
        ZergInvasion.LOGGER.info("Registering Item Groups for " + ZergInvasion.MOD_ID);
    }
}
