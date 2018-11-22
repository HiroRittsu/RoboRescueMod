package com.roborescue.socket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.roborescue.commons.Point3D;
import com.roborescue.world.map.parts.Building;
import com.roborescue.world.map.parts.Edge;
import com.roborescue.world.map.parts.Road;

public class SocketReader {

	////////////////////////////////////////////

	public void readNode(String msg) {
		String[] msgs = msg.split(",");
		// {entityID, x, y, z}
		Map<Integer, Point3D> node = new HashMap<>();
		for (int i = 1; i < msgs.length; i += 4) {
			int id = Integer.parseInt(msgs[i]);
			Point3D point3d = new Point3D((int) Double.parseDouble(msgs[i + 1]), (int) Double.parseDouble(msgs[i + 2]),
					(int) Double.parseDouble(msgs[i + 3]));
			node.put(id, point3d);
		}
	}

	public void readEdge(String msg) {
		String[] msgs = msg.split(",");
		// {entityID, firstID, endID}
		Map<Integer, Edge> edges = new HashMap<>();
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
		// {entityID, edgeID,・・・ }
		int id = Integer.parseInt(msgs[0]);
		ArrayList<Integer> edge_ids = new ArrayList<>();
		for (int i = 1; i < msgs.length; i++) {
			edge_ids.add(Integer.parseInt(msgs[i]));
		}
		Road road = new Road(id, edge_ids);
	}

	public void readBuilding(String msg) {
		String[] msgs = msg.split(",");
		// {entityID, floor, material, edgeID,・・・ }
		int id = Integer.parseInt(msgs[0]);
		int floor = Integer.parseInt(msgs[1]);
		String material = msgs[2];
		ArrayList<Integer> edge_ids = new ArrayList<>();
		for (int i = 3; i < msgs.length; i++) {
			edge_ids.add(Integer.parseInt(msgs[i]));
		}
		Building building = new Building(id, floor, material, edge_ids);
	}

	////////////////////////////////////////////

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
