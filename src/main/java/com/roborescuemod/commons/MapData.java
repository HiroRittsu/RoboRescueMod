package com.roborescuemod.commons;

import java.util.ArrayList;
import java.util.Map;

import com.roborescuemod.commons.Point3D;
import com.roborescuemod.world.map.Building;
import com.roborescuemod.world.map.Edge;
import com.roborescuemod.world.map.Road;

public class MapData {

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

}
