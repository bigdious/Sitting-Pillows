package com.bigdious.sp;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class SPEntities {
	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Registries.ENTITY_TYPE, SP.MODID);

	public static final DeferredHolder<EntityType<?>, EntityType<SittingPillow>> SITTING_PILLOW = register(ResourceLocation.fromNamespaceAndPath(SP.MODID, "sitting_pillow"), EntityType.Builder.of(SittingPillow::new, MobCategory.MISC).sized(0.8F, 0.1F));

	private static <T extends Entity> DeferredHolder<EntityType<?>, EntityType<T>> register(ResourceLocation id, EntityType.Builder<T> builder) {
		return ENTITIES.register(id.getPath(), () -> builder.build(id.toString()));
	}
}
