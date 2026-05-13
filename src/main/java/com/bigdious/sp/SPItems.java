package com.bigdious.sp;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;
import java.util.function.Supplier;

public class SPItems {
	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(SP.MODID);

	public static final DeferredItem<Item> SITTING_PILLOW = register("sitting_pillow", SittingPillowItem::new, () -> new Item.Properties().stacksTo(64));

	public static <T extends Item> DeferredItem<T> register(String name, Function<Item.Properties, T> item, Supplier<Item.Properties> properties) {
		return ITEMS.register(name, () -> item.apply(properties.get()));
	}

}
