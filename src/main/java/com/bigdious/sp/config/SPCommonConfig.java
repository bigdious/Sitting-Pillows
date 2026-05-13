package com.bigdious.sp.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public final class SPCommonConfig {
	final ModConfigSpec.BooleanValue killPillowsTicking;

	public SPCommonConfig(ModConfigSpec.Builder builder) {

		this.killPillowsTicking = builder
			.translation("config.sp.kill_pillows_ticking")
			.comment(ConfigComments.KILL_PILLOWS_TICKING)
			.define("killPillowsTicking", false);
	}
}
