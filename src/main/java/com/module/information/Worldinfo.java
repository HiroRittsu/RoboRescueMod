package com.module.information;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
	public static boolean readyNeighbour;
	public static boolean readyAgent;
	public static boolean readyStetas;
	public static boolean completeMap;
	public static boolean completeScenario;
	public static boolean completeStetas;
	public static int time;
	public static ArrayList<Integer> neighbours;
	public static Map<Integer, StandardAgent> agents;
	public static ArrayList<String> stetas;

	public Worldinfo() {
		readyGmlMap = false;
		readyRescueMap = false;
		readyMinecraftMap = false;
		readyNeighbour = false;
		readyAgent = false;
		readyStetas = false;
		completeMap = false;
		completeScenario = false;
		completeStetas = false;
		time = -1;
		neighbours = new ArrayList<>();
		agents = new HashMap<>();
		stetas = new ArrayList<>();
	}

	public static Map<Integer, StandardAgent> getAgents() {
		return agents;
	}

	public static ArrayList<String> getStates() {
		return stetas;
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

	public static boolean canNeighbour() {
		return readyNeighbour;
	}

	public static boolean canStetas() {
		return readyStetas;
	}

}
