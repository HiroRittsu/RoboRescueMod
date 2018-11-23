package com.roborescue.socket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.roborescue.commons.Point3D;
import com.roborescue.information.Worldinfo;
import com.roborescue.world.map.GMLMap;
import com.roborescue.world.map.MinecraftMap;
import com.roborescue.world.map.RescueMap;
import com.roborescue.world.map.parts.Building;
import com.roborescue.world.map.parts.Edge;
import com.roborescue.world.map.parts.Road;

public class SocketReader {

	public Map<Integer, Point3D> nodes = new HashMap<>();
	public Map<Integer, Edge> edges = new HashMap<>();
	public ArrayList<Road> roads = new ArrayList<>();
	public ArrayList<Building> buildings = new ArrayList<>();
	public GMLMap gmlMap;
	public RescueMap rescueMap;
	public MinecraftMap minecraftMap;

	public void readCommand(String msg) {
		String[] msgs = msg.split(",");
		switch (msgs[1]) {
		case "registry_map":
			// gmlマップ作成
			gmlMap = new GMLMap(nodes);
			gmlMap.setEdges(edges);
			gmlMap.setRoads(roads);
			gmlMap.setBuildings(buildings);

			// rescueマップ作成
			rescueMap = new RescueMap(gmlMap);
			// minecraftマップ作成
			minecraftMap = new MinecraftMap(gmlMap);

			// Worldinfoに登録
			Worldinfo.gmlMap = gmlMap;
			Worldinfo.readyGmlMap = true;

			Worldinfo.rescueMap = rescueMap;
			Worldinfo.readyRescueMap = true;

			Worldinfo.minecraftMap = minecraftMap;
			Worldinfo.readyMinecraftMap = true;

			System.out.println("マップ登録完了");
			break;

		default:
			System.out.println("Command例外受信");
			break;
		}
	}

	////////////////////////////////////////////
	public void readNode(String msg) {
		String[] msgs = msg.split(",");
		// {node, entityID, x, y, z}
		for (int i = 1; i < msgs.length; i += 4) {
			int id = Integer.parseInt(msgs[i]);
			Point3D point3d = new Point3D((int) Double.parseDouble(msgs[i + 1]), (int) Double.parseDouble(msgs[i + 2]),
					(int) Double.parseDouble(msgs[i + 3]));
			nodes.put(id, point3d);
		}
	}

	public void readEdge(String msg) {
		String[] msgs = msg.split(",");
		// {edge, entityID, firstID, endID}
		for (int i = 1; i < msgs.length; i += 3) {
			int id = Integer.parseInt(msgs[i]);
			Integer[] nodes = new Integer[2];
			nodes[0] = Integer.parseInt(msgs[i + 1]);
			nodes[1] = Integer.parseInt(msgs[i + 2]);
			Edge edge = new Edge(id, nodes);
			edges.put(edge.getId(), edge);
		}
	}

	public void readRoad(String msg) {
		String[] msgs = msg.split(",");
		// {road, entityID, edgeID,・・・ }
		int id = Integer.parseInt(msgs[1]);
		ArrayList<Integer> edge_ids = new ArrayList<>();
		for (int i = 2; i < msgs.length; i++) {
			edge_ids.add(Integer.parseInt(msgs[i]));
		}
		roads.add(new Road(id, edge_ids));
	}

	public void readBuilding(String msg) {
		String[] msgs = msg.split(",");
		// {building, entityID, floor, material, edgeID,・・・ }
		int id = Integer.parseInt(msgs[1]);
		int floor = Integer.parseInt(msgs[2]);
		String material = msgs[3];
		ArrayList<Integer> edge_ids = new ArrayList<>();
		for (int i = 4; i < msgs.length; i++) {
			edge_ids.add(Integer.parseInt(msgs[i]));
		}
		buildings.add(new Building(id, floor, material, edge_ids));
	}

	////////////////////////////////////////////

	public void readScenario(String msg) {
		String[] msgs = msg.split(",");
		switch (msgs[1]) {
		case "civilian":
			
			break;

		case "policeforce":

			break;

		case "firebrigade":

			break;

		case "ambulanceteam":

			break;

		case "fire":

			break;

		default:
			System.out.println("scenario例外受信");
			break;
		}

		for (int i = 1; i < msgs.length; i++) {

		}
	}

	////////////////////////////////////////////////////////////////

	public void readCivilian(String msg) {
		for (int i = 1; i < msg.split(",").length; i++) {

		}
	}

	public void readAT(String msg) {
		for (int i = 1; i < msg.split(",").length; i++) {

		}
	}

	public void readFB(String msg) {
		for (int i = 1; i < msg.split(",").length; i++) {

		}
	}

	public void readPF(String msg) {
		for (int i = 1; i < msg.split(",").length; i++) {

		}
	}

	public void readBlickade(String msg) {
		for (int i = 1; i < msg.split(",").length; i++) {

		}
	}

}
