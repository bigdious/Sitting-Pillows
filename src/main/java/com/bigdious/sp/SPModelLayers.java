package com.bigdious.sp;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class SPModelLayers {
	public static final ModelLayerLocation SITTING_PILLOW = register("sitting_pillow");

	private static ModelLayerLocation register(String name) {
		return register(name, "main");
	}

	private static ModelLayerLocation register(String name, String type) {
		return new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(SP.MODID, name), type);
	}
}
