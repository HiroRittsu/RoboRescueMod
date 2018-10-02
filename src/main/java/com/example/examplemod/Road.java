package com.example.examplemod;

import java.awt.Point;
import java.util.HashSet;

public class Road {

	Point position;
	private int id;
	private HashSet<Integer[]> edge_ids;

	public Road(int id, HashSet<Integer[]> edge_ids) {
		this.id = id;
		this.edge_ids = edge_ids;
	}

	public int getId() {
		return this.id;
	}

	public HashSet<Integer[]> getEdgeIds() {
		return this.edge_ids;
	}
}
