package com.roborescue.information;

import com.roborescue.world.map.GMLMap;
import com.roborescue.world.map.MinecraftMap;
import com.roborescue.world.map.RescueMap;

public class Worldinfo {

	public static MinecraftMap minecraftMap;
	public static RescueMap rescueMap;
	public static GMLMap gmlMap;

	public static boolean canMinecraftMap() {
		return false;
	}

	public static boolean canRescueMap() {
		return false;
	}

	public static boolean canGMLMap() {
		return false;
	}

}
