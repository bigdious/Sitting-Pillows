package com.bigdious.sp.config;

public class SPConfig {

	// -- COMMON CONFIG --
	public static boolean killPillowsTicking = false;


	static void rebakeCommonOptions(SPCommonConfig config) {
		killPillowsTicking = config.killPillowsTicking.get();
	}
}
