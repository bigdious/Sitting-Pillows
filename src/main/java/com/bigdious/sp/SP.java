package com.bigdious.sp;

import com.bigdious.sp.config.ConfigSetup;
import com.google.common.reflect.Reflection;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;

@Mod(SP.MODID)
public class SP {
	public static final String MODID = "sp";

	public static final Logger LOGGER = LogManager.getLogger();

	public SP(IEventBus bus, Dist dist) {
		bus.addListener(this::registerPacket);
		bus.addListener(this::gatherData);
		SPEntities.ENTITIES.register(bus);
		SPItems.ITEMS.register(bus);
		bus.addListener(SPTab::addToTabs);

		if (dist.isClient()) {
			bus.addListener(SPEvents::registerEntityLayers);
			bus.addListener(SPEvents::registerEntityRenderers);
			bus.addListener(SPEvents::registerItemColors);
		}
		bus.addListener(SPEvents::commonSetup);

		Reflection.initialize(ConfigSetup.class);
		bus.addListener(ConfigSetup::loadConfigs);
		bus.addListener(ConfigSetup::reloadConfigs);
	}

	private void gatherData(GatherDataEvent event) {

	}

	public void registerPacket(RegisterPayloadHandlersEvent event) {
		PayloadRegistrar registrar = event.registrar(MODID).versioned("1.0.0");
//		registrar.playToClient(SyncCommonConfigPacket.TYPE, SyncCommonConfigPacket.STREAM_CODEC, SyncCommonConfigPacket::handle);
	}
	public static ResourceLocation prefix(String name) {
		return ResourceLocation.fromNamespaceAndPath(MODID, name.toLowerCase(Locale.ROOT));
	}

}
