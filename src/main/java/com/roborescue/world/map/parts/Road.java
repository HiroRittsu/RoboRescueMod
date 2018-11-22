package com.roborescue.world.map.parts;

import java.awt.Polygon;
import java.util.ArrayList;

import com.roborescue.commons.Point3D;

public class Road extends Polygon {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Point3D position;
	private int id;
	// private ArrayList<Integer[]> edge_ids;
	private ArrayList<Integer> edge_id;

	/*
	 * public Road(int id, ArrayList<Integer[]> edge_ids) { this.id = id;
	 * this.edge_ids = edge_ids; }
	 */

	public Road(int id, ArrayList<Integer> edge_id) {
		this.id = id;
		this.edge_id = edge_id;
	}

	public int getId() {
		return this.id;
	}

	public ArrayList<Integer> getEdgeIds() {
		return this.edge_id;
	}

}
