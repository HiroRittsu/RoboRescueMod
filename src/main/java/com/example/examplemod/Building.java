package com.example.examplemod;

import java.awt.Polygon;
import java.util.ArrayList;

public class Building extends Polygon{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Point3D point;
	private int id;
	private int floor;
	private String material;
	private ArrayList<Edge> edges;
	private ArrayList<Integer[]> edge_ids;

	public Building(int id, int floor, String material, ArrayList<Integer[]> edge_ids) {
		this.id = id;
		this.floor = floor;
		this.material = material;
		this.edge_ids = edge_ids;
	}

	public int getId() {
		return this.id;
	}

	public Point3D getPosition() {
		return this.point;
	}

	public int getFloor() {
		return this.floor;
	}

	public String getMaterial() {
		return this.material;
	}

	public ArrayList<Edge> getEdgeId() {
		return this.edges;
	}

	public ArrayList<Integer[]> getEdgeIds() {
		return this.edge_ids;
	}

}