package com.roborescuemod.world.map;

import java.awt.Polygon;
import java.util.ArrayList;

import com.roborescuemod.commons.Point3D;

public class Road extends Polygon{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Point3D position;
	private int id;
	private ArrayList<Integer[]> edge_ids;

	public Road(int id, ArrayList<Integer[]> edge_ids) {
		this.id = id;
		this.edge_ids = edge_ids;
	}

	public int getId() {
		return this.id;
	}

	public ArrayList<Integer[]> getEdgeIds() {
		return this.edge_ids;
	}
}
