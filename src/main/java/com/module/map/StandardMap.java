package com.module.map;

import java.util.HashMap;
import java.util.Map;

import com.module.commons.Point3D;
import com.module.commons.Point3Df;
import com.module.map.parts.Building;
import com.module.map.parts.Edge;
import com.module.map.parts.Node;
import com.module.map.parts.Road;

public class StandardMap {

	public Map<Integer, Node> nodes = null;
	public Map<Integer, Edge> edges = null;
	public Map<Integer, Road> roads = null;
	public Map<Integer, Building> buildings = null;

	public Map<Integer, Node> getNodes() {
		return this.nodes;
	}

	public void setEdges(Map<Integer, Edge> edges) {
		this.edges = edges;
	}

	public Map<Integer, Edge> getEdges() {
		return this.edges;
	}

	public void setRoads(Map<Integer, Road> roads) {
		this.roads = roads;
	}

	public Map<Integer, Road> getRoads() {
		return this.roads;
	}

	public void setBuildings(Map<Integer, Building> buildings) {
		this.buildings = buildings;
	}

	public Map<Integer, Building> getBuildins() {
		return this.buildings;
	}
	
	public Point3D getPosition(int enetityID) {
		// get building entityID
		if (buildings.containsKey(enetityID)) {
			return buildings.get(enetityID).position;
		}
		// get road entityID
		if (roads.containsKey(enetityID)) {
			return roads.get(enetityID).getPosition();
		}
		return null;
	}

	////////////////////////////////////////////////////////////////////////////////////////////

	public static Point3Df[] calcPrimaryPoint(Map<Integer, Node> nodes) {

		// [0]: max, [1]:min, [2]:centroid
		Point3Df[] result = new Point3Df[3];

		for (int i = 0; i < result.length; i++)
			result[i] = new Point3Df(0, 0, 0);
		for (Map.Entry<Integer, Node> entry : nodes.entrySet()) {
			Node node = entry.getValue();
			if (result[1].getX() > node.getPoint().x)
				result[1].x = node.getPoint().x;
			if (result[1].getY() > node.getPoint().y)
				result[1].y = node.getPoint().y;
			if (result[1].getZ() > node.getPoint().z)
				result[1].z = node.getPoint().z;
			if (result[0].getX() < node.getPoint().x)
				result[0].x = node.getPoint().x;
			if (result[0].getY() < node.getPoint().y)
				result[0].y = node.getPoint().y;
			if (result[0].getZ() < node.getPoint().z)
				result[0].z = node.getPoint().z;
		}
		result[2].x = (result[0].getX() + result[1].getX()) / 2.0;
		result[2].y = (result[0].getY() + result[1].getY()) / 2.0;
		result[2].z = (result[0].getZ() + result[1].getZ()) / 2.0;
		return result;
	}

	public static Map<Integer, Node> convertRescueMap(GMLMap gmlMap) {

		Map<Integer, Node> result = new HashMap<>();

		Point3D distance = new Point3D((int) (-1 * gmlMap.getMinPoint().getX()),
				(int) (-1 * gmlMap.getMinPoint().getY()), (int) (-1 * gmlMap.getMinPoint().getZ()));
		for (Map.Entry<Integer, Node> entry : gmlMap.getNodes().entrySet()) {
			Node node = entry.getValue();
			Point3D newPoint = new Point3D((node.getPoint().x + distance.getX()) * 1000, node.getPoint().y,
					(node.getPoint().x + distance.getX()) * 1000);
			result.put(entry.getKey(), new Node(node.getID(), newPoint));
		}
		return result;
	}

	public static Map<Integer, Node> convertMinecraftMap(GMLMap gmlMap) {

		Point3D origin = new Point3D(0, 0, 0);
		Map<Integer, Node> result = new HashMap<>();

		Point3D distance = new Point3D((int) (origin.getX() - gmlMap.getCentroid().getX()),
				(int) (origin.getY() - gmlMap.getCentroid().getY()),
				(int) (origin.getZ() - gmlMap.getCentroid().getZ()));
		for (Map.Entry<Integer, Node> entry : gmlMap.getNodes().entrySet()) {
			Node node = entry.getValue();
			Point3D newPoint = new Point3D(node.getPoint().x - distance.getX(), node.getPoint().y,
					node.getPoint().z - distance.getZ());
			result.put(entry.getKey(), new Node(node.getID(), newPoint));
		}
		return result;
	}

}
