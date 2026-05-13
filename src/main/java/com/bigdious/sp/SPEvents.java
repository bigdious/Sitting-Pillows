package com.bigdious.sp;

import net.minecraft.world.item.component.DyedItemColor;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

public class SPEvents {
	public static void registerEntityLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(SPModelLayers.SITTING_PILLOW, SittingPillowModel::create);
	}

	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(SPEntities.SITTING_PILLOW.get(), SittingPillowRenderer::new);
	}

	public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
		event.register((stack, index) -> index != 1 ? -1 : DyedItemColor.getOrDefault(stack, -1),
			SPItems.SITTING_PILLOW.get()
		);
	}

	public static void commonSetup(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			SPCauldronInteractions.register();
		});
	}
}
