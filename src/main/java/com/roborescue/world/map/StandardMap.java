package com.roborescue.world.map;

import java.util.ArrayList;
import java.util.Map;

import com.roborescue.commons.Point3D;
import com.roborescue.world.map.parts.Building;
import com.roborescue.world.map.parts.Edge;
import com.roborescue.world.map.parts.Road;

public class StandardMap {

	public Map<Integer, Point3D> nodes = null;
	public Map<Integer, Edge> edges = null;
	public ArrayList<Road> roads = null;
	public ArrayList<Building> buildings = null;

	public Map<Integer, Point3D> getNodes() {
		return this.nodes;
	}

	public void setEdges(Map<Integer, Edge> edges) {
		this.edges = edges;
	}

	public Map<Integer, Edge> getEdges() {
		return this.edges;
	}

	public void setRoads(ArrayList<Road> roads) {
		this.roads = roads;
	}

	public ArrayList<Road> getRoads() {
		return this.roads;
	}

	public void setBuildings(ArrayList<Building> buildings) {
		this.buildings = buildings;
	}

	public ArrayList<Building> getBuildins() {
		return this.buildings;
	}

	public Point3D getPosition(int enetityID) {

		int sumX = 0;
		int sumY = 0;
		int sumZ = 0;
		int count = 0;

		// get building entytyID
		for (Building building : buildings) {
			if (building.getId() == enetityID) {
				for (int edge_id : building.getEdgeIds()) {
					for (int node_id : edges.get(edge_id).getNodeID()) {
						sumX = nodes.get(node_id).x;
						sumY = nodes.get(node_id).y;
						sumZ = nodes.get(node_id).z;
						count++;
					}
				}
				return new Point3D(-sumX / count, sumY / count, sumZ / count);
			}
		}

		// get road entityID
		for (Road road : roads) {
			if (road.getId() == enetityID) {
				for (int edge_id : road.getEdgeIds()) {
					for (int node_id : edges.get(edge_id).getNodeID()) {
						sumX = nodes.get(node_id).x;
						sumY = nodes.get(node_id).y;
						sumZ = nodes.get(node_id).z;
						count++;
					}
				}
				return new Point3D(-sumX / count, sumY / count, sumZ / count);
			}
		}

		return null;
	}

}
