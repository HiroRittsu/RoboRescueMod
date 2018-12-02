package com.module.socket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.module.anget.StandardAgent;
import com.module.anget.ambulanceteam.AT;
import com.module.anget.civilian.Civilian;
import com.module.anget.firebrigade.FB;
import com.module.anget.policeforce.PF;
import com.module.commons.Point3D;
import com.module.information.Worldinfo;
import com.module.map.GMLMap;
import com.module.map.MinecraftMap;
import com.module.map.RescueMap;
import com.module.map.parts.Building;
import com.module.map.parts.Edge;
import com.module.map.parts.Node;
import com.module.map.parts.Road;

public class SocketReader {

	public GMLMap gmlMap;
	public RescueMap rescueMap;
	public MinecraftMap minecraftMap;
	public Map<Integer, Node> nodes;
	public Map<Integer, Edge> edges;
	public Map<Integer, Road> roads;
	public Map<Integer, Building> buildings;
	public ArrayList<Integer> neighbour;

	public SocketReader() {
		this.nodes = new HashMap<>();
		this.edges = new HashMap<>();
		this.roads = new HashMap<>();
		this.buildings = new HashMap<>();
		this.neighbour = new ArrayList<>();
	}

	public void readCommand(String msg) {
		String[] msgs = msg.split(",");
		switch (msgs[1]) {
		case "registry_map":
			// gmlマップ作成
			gmlMap = new GMLMap(nodes, edges, roads, buildings);
			System.out.println("gmlMap" + gmlMap.getCentroid());
			// rescueマップ作成
			rescueMap = new RescueMap(gmlMap);
			System.out.println("rescueMap" + rescueMap.getCentroid());
			// minecraftマップ作成
			minecraftMap = new MinecraftMap(gmlMap);
			System.out.println("minecraftMap " + minecraftMap.getCentroid());

			// Worldinfoに登録
			Worldinfo.gmlMap = gmlMap;
			Worldinfo.readyGmlMap = true;

			Worldinfo.rescueMap = rescueMap;
			Worldinfo.readyRescueMap = true;

			Worldinfo.minecraftMap = minecraftMap;
			Worldinfo.readyMinecraftMap = true;

			Worldinfo.neighbour = neighbour;
			Worldinfo.readyNeighbour = true;

			System.out.println("マップ登録完了");
			break;

		case "orient_scenario":
			Worldinfo.readyAgent = true;
			System.out.println("シナリオ反映");
			break;

		case "orient_stetas":
			Worldinfo.readyStetas = true;
			System.out.println("ステータス反映");
			break;

		default:
			System.out.println(msg);
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
			Point3D point = new Point3D((int) Double.parseDouble(msgs[i + 1]), (int) Double.parseDouble(msgs[i + 2]),
					(int) Double.parseDouble(msgs[i + 3]));
			point.y = 3; // minecraftのフラットワールドの高さ
			nodes.put(id, new Node(id, point));
		}
	}

	public void readEdge(String msg) {
		String[] msgs = msg.split(",");
		// {edge, entityID, firstID, endID}
		int id = Integer.parseInt(msgs[1]);
		Node first = nodes.get(Integer.parseInt(msgs[2]));
		Node end = nodes.get(Integer.parseInt(msgs[3]));
		edges.put(id, new Edge(id, first, end));
	}

	public void readRoad(String msg) {
		String[] msgs = msg.split(",");
		// {road, entityID, edgeID,・・・ }
		int id = Integer.parseInt(msgs[1]);
		ArrayList<Edge> result = new ArrayList<>();
		for (int i = 2; i < msgs.length; i++) {
			result.add(this.edges.get(Integer.parseInt(msgs[i])));
		}
		roads.put(id, new Road(id, result));
	}

	public void readBuilding(String msg) {
		String[] msgs = msg.split(",");
		// {building, entityID, floor, material, edgeID,・・・ }
		int id = Integer.parseInt(msgs[1]);
		int floor = Integer.parseInt(msgs[2]);
		ArrayList<Edge> result = new ArrayList<>();
		String material = msgs[3];
		for (int i = 4; i < msgs.length; i++) {
			result.add(this.edges.get(Integer.parseInt(msgs[i])));
		}
		buildings.put(id, new Building(id, floor, material, result));
	}

	public void readBuilding_neighbour(String msg) {
		System.out.println(msg);
		String[] msgs = msg.split(",");
		// {building_neighbour, edgeID,・・・ }
		for (int i = 1; i < msgs.length; i++) {
			neighbour.add(Integer.parseInt(msgs[i]));
		}
	}

	////////////////////////////////////////////

	public void readScenario(String msg) {
		String[] msgs = msg.split(",");
		switch (msgs[1]) {
		case "civilian":
			System.out.println("civilian");
			// {scenario, civilian, entityID, locarionID}
			Worldinfo.getAgents().put(Integer.parseInt(msgs[2]),
					new Civilian(Integer.parseInt(msgs[2]), Integer.parseInt(msgs[3])));
			break;

		case "policeforce":
			System.out.println("policeforce");
			// {scenario, policeforce, entityID, locarionID}
			Worldinfo.getAgents().put(Integer.parseInt(msgs[2]),
					new PF(Integer.parseInt(msgs[2]), Integer.parseInt(msgs[3])));
			break;

		case "firebrigade":
			System.out.println("firebrigade");
			// {scenario, firebrigade, entityID, locarionID}
			Worldinfo.getAgents().put(Integer.parseInt(msgs[2]),
					new FB(Integer.parseInt(msgs[2]), Integer.parseInt(msgs[3])));
			break;

		case "ambulanceteam":
			System.out.println("ambulanceteam");
			// {scenario, ambulanceteam, entityID, locarionID}
			Worldinfo.getAgents().put(Integer.parseInt(msgs[2]),
					new AT(Integer.parseInt(msgs[2]), Integer.parseInt(msgs[3])));
			break;

		case "fire":
			System.out.println("fire scenario");
			break;

		default:
			System.out.println("scenario例外受信");
			break;
		}
	}

	////////////////////////////////////////////////////////////////

	public void readCivilian_State(String msg) {
		String[] msgs = msg.split(",");
		// {civilian, entityID, HP, historyX, historyZ ・・・}
		if (msgs.length >= 3) {
			StandardAgent agent = Worldinfo.getAgents().get(Integer.parseInt(msgs[1]));
			agent.hp = Integer.parseInt(msgs[2]);
			for (int i = 3; i < msgs.length; i++) {
				agent.history_position.add(Integer.parseInt(msgs[i]));
			}
		}
	}

	public void readAT_State(String msg) {
		String[] msgs = msg.split(",");
		// {ambulanceteam, enriryID, HP, historyX, historyZ ・・・}
		if (msgs.length >= 3) {
			StandardAgent agent = Worldinfo.getAgents().get(Integer.parseInt(msgs[1]));
			agent.hp = Integer.parseInt(msgs[2]);
			for (int i = 3; i < msgs.length; i++) {
				agent.history_position.add(Integer.parseInt(msgs[i]));
			}
		}
	}

	public void readFB_State(String msg) {
		String[] msgs = msg.split(",");
		// {firebrigade, enriryID, HP, Water, historyX, historyZ ・・・}
		if (msgs.length >= 4) {
			StandardAgent agent = Worldinfo.getAgents().get(Integer.parseInt(msgs[1]));
			agent.hp = Integer.parseInt(msgs[2]);
			if (agent instanceof FB) {
				((FB) agent).water = Integer.parseInt(msgs[3]);
			}
			for (int i = 4; i < msgs.length; i++) {
				agent.history_position.add(Integer.parseInt(msgs[i]));
			}
		}
	}

	public void readPF_State(String msg) {
		String[] msgs = msg.split(",");
		// {policeforce, enriryID, HP, historyX, historyZ ・・・}
		if (msgs.length >= 3) {
			StandardAgent agent = Worldinfo.getAgents().get(Integer.parseInt(msgs[1]));
			agent.hp = Integer.parseInt(msgs[2]);
			for (int i = 3; i < msgs.length; i++) {
				agent.history_position.add(Integer.parseInt(msgs[i]));
			}
		}
	}

	public void readBlickade_State(String msg) {
		for (int i = 1; i < msg.split(",").length; i++) {

		}
	}
}
