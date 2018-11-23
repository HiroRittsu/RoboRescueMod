package com.roborescue.information;

import com.roborescue.world.map.GMLMap;
import com.roborescue.world.map.MinecraftMap;
import com.roborescue.world.map.RescueMap;

public class Worldinfo {

	public static MinecraftMap minecraftMap;
	public static RescueMap rescueMap;
	public static GMLMap gmlMap;
	public static boolean readyMinecraftMap;
	public static boolean readyRescueMap;
	public static boolean readyGmlMap;
	public static boolean readyMap;
	public static int time;

	public Worldinfo() {
		readyGmlMap = false;
		readyRescueMap = false;
		readyMinecraftMap = false;
		readyMap = false;
		time = -1;
	}

	public static boolean canMinecraftMap() {
		return readyMinecraftMap;
	}

	public static boolean canRescueMap() {
		return readyRescueMap;
	}

	public static boolean canGmlMap() {
		return readyGmlMap;
	}

}
