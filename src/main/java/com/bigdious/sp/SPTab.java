package com.bigdious.sp;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

public class SPTab {
	public static void addToTabs(BuildCreativeModeTabContentsEvent event) {
		if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
			event.insertAfter(new ItemStack(Items.WARPED_FUNGUS_ON_A_STICK), SPItems.SITTING_PILLOW.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
		}
	}
}
