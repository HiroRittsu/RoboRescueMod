package com.example.examplemod;

import java.awt.Point;
import java.util.ArrayList;

public class Road {

	Point position;
	private int id;
	private ArrayList<Integer[]> edge_ids;

	public Road(int id, ArrayList<Integer[]> edge_ids) {
		this.id = id;
		this.edge_ids = edge_ids;
	}

	public int getId() {
		return this.id;
	}

	public ArrayList<Integer[]> getEdgeId() {
		return this.edge_ids;
	}

	public ArrayList<Integer[]> getEdgeIds() {
		return this.edge_ids;
	}
}
