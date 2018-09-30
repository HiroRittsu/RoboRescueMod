package com.example.examplemod;

import java.awt.Point;
import java.util.ArrayList;

public class Road {

	Point position;
	private int id;
	private ArrayList<Point> edge_point;
	private ArrayList<Integer> edge_id;

	public Road(int id, ArrayList<Point> edge_points) {
		this.id = id;
		this.edge_point = edge_points;
	}

	public int getId() {
		return this.id;
	}

	public ArrayList<Point> getEdge_point() {
		return this.edge_point;
	}
}
