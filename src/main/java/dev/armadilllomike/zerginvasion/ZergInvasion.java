package dev.armadilllomike.zerginvasion;

import dev.armadilllomike.zerginvasion.block.ModBlocks;
import dev.armadilllomike.zerginvasion.item.ModItemGroups;
import dev.armadilllomike.zerginvasion.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZergInvasion implements ModInitializer {
	public static final String MOD_ID = "zerginvasion";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
	}
}