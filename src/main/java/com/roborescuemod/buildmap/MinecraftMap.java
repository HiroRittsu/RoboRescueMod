package com.roborescuemod.buildmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.roborescuemod.commons.Point3D;
import com.roborescuemod.commons.Point3Df;
import com.roborescuemod.commons.PointConverter;

public class MinecraftMap {

	private Map<Integer, Point3D> nodes = null;
	private Map<Integer, Edge> edges = null;
	private ArrayList<Road> roads = null;
	private ArrayList<Building> buildings = null;
	private PointConverter converter = new PointConverter();
	private Point3Df max;
	private Point3Df min;
	private Point3Df centroid;

	public MinecraftMap(Map<Integer, Point3D> nodes) {
		Point3Df[] primary = converter.calcPrimaryPoint(nodes);
		this.max = primary[0];
		this.min = primary[1];
		this.centroid = primary[2];

		this.nodes = converter.convertPoint(nodes, this.centroid, new Point3D(0, 0, 0));

		primary = converter.calcPrimaryPoint(this.nodes);
		this.max = primary[0];
		this.min = primary[1];
		this.centroid = primary[2];
		System.out.println("重心:" + this.centroid);
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

	public Point3Df getCentroid() {
		return this.centroid;
	}

	public Point3Df getMaxPoint() {
		return this.max;
	}

	public Point3Df getMinPoint() {
		return this.min;
	}

}
