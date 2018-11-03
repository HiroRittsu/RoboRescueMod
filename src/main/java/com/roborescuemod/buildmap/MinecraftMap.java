package com.roborescuemod.buildmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.roborescuemod.commons.Point3D;
import com.roborescuemod.commons.PointConverter;

public class MinecraftMap {

	private Map<Integer, Point3D> nodes = null;
	private Map<Integer, Edge> edges = null;
	private ArrayList<Road> roads = null;
	private ArrayList<Building> buildings = null;
	private PointConverter converter = new PointConverter();

	public MinecraftMap(Map<Integer, Point3D> nodes) {
		for (Point3D p : nodes.values()) {

		}
	}

	public void setNodes(Map<Integer, Point3D> nodes) {
		this.nodes = nodes;
	}

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

	public Point3D getCentroid() {

		return null;
	}

	public Point3D getMaxPoint() {
		return null;
	}

	public Point3D getMinPoint() {
		return null;
	}

}
