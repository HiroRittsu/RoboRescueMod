package com.module.information;

import java.util.HashMap;

import com.module.anget.StandardAgent;
import com.module.map.GMLMap;
import com.module.map.MinecraftMap;
import com.module.map.RescueMap;

public class Worldinfo {

	public static MinecraftMap minecraftMap;
	public static RescueMap rescueMap;
	public static GMLMap gmlMap;
	public static boolean readyMinecraftMap;
	public static boolean readyRescueMap;
	public static boolean readyGmlMap;
	public static boolean readyMap;
	public static boolean readyScenario;
	public static boolean readyAgent;
	public static int time;
	public static HashMap<Integer, StandardAgent> agents;

	public Worldinfo() {
		readyGmlMap = false;
		readyRescueMap = false;
		readyMinecraftMap = false;
		readyMap = false;
		readyAgent = false;
		time = -1;
		agents = new HashMap<>();
	}

	public static HashMap<Integer, StandardAgent> getAgents() {
		return agents;
	}

	public static boolean canSpawnAgent() {
		return readyAgent;
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
