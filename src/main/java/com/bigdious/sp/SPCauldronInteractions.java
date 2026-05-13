package com.bigdious.sp;

import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

public interface SPCauldronInteractions extends CauldronInteraction {

	static void register() {
		WATER.map().put(SPItems.SITTING_PILLOW.get(), DYED_ITEM);
	}
}
