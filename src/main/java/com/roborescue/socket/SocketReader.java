package com.roborescue.socket;

import java.util.HashMap;
import java.util.Map;

import com.roborescue.commons.Point3D;
import com.roborescue.world.map.parts.Edge;

public class SocketReader {

	////////////////////////////////////////////

	public void readNode(String msg) {
		String[] msgs = msg.split(",");
		// {entityID, x, y, z}
		Map<Integer, Point3D> node = new HashMap<>();
		for (int i = 1; i < msgs.length; i += 4) {
			Point3D point3d = new Point3D((int) Double.parseDouble(msgs[i + 1]), (int) Double.parseDouble(msgs[i + 2]),
					(int) Double.parseDouble(msgs[i + 3]));
			node.put(Integer.parseInt(msgs[i]), point3d);
		}
	}

	public void readEdge(String msg) {
		String[] msgs = msg.split(",");
		// {entityID, firstID, endID}
		Map<Integer, Edge> edges = new HashMap<>();
		for (int i = 1; i < msgs.length; i += 3) {
			Integer[] nodes = new Integer[2];
			nodes[0] = Integer.parseInt(msgs[i + 1]);
			nodes[1] = Integer.parseInt(msgs[i + 2]);
			Edge edge = new Edge(Integer.parseInt(msgs[i]), nodes);
			edges.put(Integer.parseInt(msgs[i]), edge);
		}
	}

	public void readRoad(String msg) {
		for (int i = 1; i < msg.split(",").length; i++) {

		}
	}

	public void readBuilding(String msg) {
		for (int i = 1; i < msg.split(",").length; i++) {

		}
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
