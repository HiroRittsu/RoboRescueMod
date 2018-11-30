package com.module.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.module.commons.Point3D;
import com.module.commons.Point3Df;
import com.module.information.Worldinfo;
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

	////////////////////////////////////////////////////////////////////////////////////

	public static Map<Integer, Node> convertRescueMap_node(GMLMap gmlMap) {
		Map<Integer, Node> result = new HashMap<>();
		Point3Df distance = new Point3Df(-1 * gmlMap.getMinPoint().getX(), -1 * gmlMap.getMinPoint().getY(),
				-1 * gmlMap.getMinPoint().getZ());
		for (Map.Entry<Integer, Node> entry : gmlMap.getNodes().entrySet()) {
			result.put(entry.getKey(),
					new Node(entry.getKey(),
							new Point3D((int) ((entry.getValue().point.getX() + distance.getX()) * 1000),
									(int) ((entry.getValue().point.getY() + distance.getY()) * 1000),
									(int) ((entry.getValue().point.getZ() + distance.getZ()) * 1000))));
		}
		return result;
	}

	public static Map<Integer, Edge> convertRescueMap_edge(GMLMap gmlMap, Map<Integer, Node> nodes) {
		Map<Integer, Edge> result = new HashMap<>();
		for (Map.Entry<Integer, Edge> entry : gmlMap.getEdges().entrySet()) {
			result.put(entry.getKey(), new Edge(entry.getKey(), nodes.get(entry.getValue().getStartNode().getID()),
					nodes.get(entry.getValue().getEndNode().getID())));
		}
		return result;
	}

	public static Map<Integer, Road> convertRescueMap_road(GMLMap gmlMap, Map<Integer, Edge> edges) {
		Map<Integer, Road> result = new HashMap<>();
		for (Map.Entry<Integer, Road> entry : gmlMap.getRoads().entrySet()) {
			ArrayList<Edge> new_edges = new ArrayList<>();
			for (Edge edge : entry.getValue().getEdges()) {
				new_edges.add(edges.get(edge.getId()));
			}
			result.put(entry.getKey(), new Road(entry.getKey(), new_edges));
		}
		return result;
	}

	public static Map<Integer, Building> convertRescueMap_building(GMLMap gmlMap, Map<Integer, Edge> edges) {
		Map<Integer, Building> result = new HashMap<>();
		for (Map.Entry<Integer, Building> entry : gmlMap.getBuildins().entrySet()) {
			ArrayList<Edge> new_edges = new ArrayList<>();
			for (Edge edge : entry.getValue().getEdges()) {
				new_edges.add(edges.get(edge.getId()));
			}
			result.put(entry.getKey(), new Building(entry.getKey(), entry.getValue().getFloor(),
					entry.getValue().getMaterial(), new_edges));
		}
		return result;
	}

	////////////////////////////////////////////////////////////////////////////////////

	public static Map<Integer, Node> convertMinecraftMap_node(GMLMap gmlMap) {
		Point3Df origin = new Point3Df(0, 0, 0);
		Map<Integer, Node> result = new HashMap<>();
		Point3Df distance = new Point3Df(origin.getX() - gmlMap.getCentroid().getX(),
				origin.getY() - gmlMap.getCentroid().getY(), origin.getZ() - gmlMap.getCentroid().getZ());

		for (Map.Entry<Integer, Node> entry : gmlMap.getNodes().entrySet()) {
			result.put(entry.getKey(),
					new Node(entry.getKey(),
							new Point3D(entry.getValue().point.getX() + (int) distance.getX(),
									entry.getValue().point.getY() + (int) distance.getY(),
									entry.getValue().point.getZ() + (int) distance.getZ())));
		}
		return result;
	}

	public static Map<Integer, Edge> convertMinecraftMap_edge(GMLMap gmlMap, Map<Integer, Node> nodes) {
		Map<Integer, Edge> result = new HashMap<>();
		for (Map.Entry<Integer, Edge> entry : gmlMap.getEdges().entrySet()) {
			result.put(entry.getKey(), new Edge(entry.getKey(), nodes.get(entry.getValue().getStartNode().getID()),
					nodes.get(entry.getValue().getEndNode().getID())));
		}
		return result;
	}

	public static Map<Integer, Road> convertMinecraftMap_road(GMLMap gmlMap, Map<Integer, Edge> edges) {
		Map<Integer, Road> result = new HashMap<>();
		for (Map.Entry<Integer, Road> entry : gmlMap.getRoads().entrySet()) {
			ArrayList<Edge> new_edges = new ArrayList<>();
			for (Edge edge : entry.getValue().getEdges()) {
				new_edges.add(edges.get(edge.getId()));
			}
			result.put(entry.getKey(), new Road(entry.getKey(), new_edges));
		}
		return result;
	}

	public static Map<Integer, Building> convertMinecraftMap_building(GMLMap gmlMap, Map<Integer, Edge> edges) {
		Map<Integer, Building> result = new HashMap<>();
		for (Map.Entry<Integer, Building> entry : gmlMap.getBuildins().entrySet()) {
			ArrayList<Edge> new_edges = new ArrayList<>();
			for (Edge edge : entry.getValue().getEdges()) {
				new_edges.add(edges.get(edge.getId()));
			}
			result.put(entry.getKey(), new Building(entry.getKey(), entry.getValue().getFloor(),
					entry.getValue().getMaterial(), new_edges));
		}
		return result;
	}

	public Point3D toMinecraftPoint(Point3D rescue_point) {

		/*
		 * Point3Df distance = new Point3Df((double) (Worldinfo.rescueMap.centroid.x),
		 * (double) (Worldinfo.rescueMap.centroid.y), (double)
		 * (Worldinfo.rescueMap.centroid.z));
		 * 
		 * return new Point3D((int) (rescue_point.x - distance.x) / 1000, (int)
		 * (rescue_point.y - distance.y) / 1000, (int) (rescue_point.z - distance.z) /
		 * -1000);
		 */
		return new Point3D((int) (rescue_point.x - Worldinfo.rescueMap.centroid.x) / 1000,
				(int) (rescue_point.y - Worldinfo.rescueMap.centroid.y) / 1000,
				(int) (rescue_point.z - Worldinfo.rescueMap.centroid.z) / -1000);
	}

}
