package com.example.examplemod;

import java.awt.Point;
import java.util.ArrayList;

public class Building {

	private Point point;
	private int id;
	private int floor;
	private String material;
	private ArrayList<Point> edge_point;
	private ArrayList<Integer> edge_id;

	public Building(int id, int floor, String material, ArrayList<Point> edge_points) {
		this.id = id;
		this.floor = floor;
		this.material = material;
		this.edge_point = edge_points;
	}

	public int getId() {
		return this.id;
	}

	public void setPosition(Point point) {
		this.point = point;
	}

	public Point getPosition() {
		return this.point;
	}

	public int getFloor() {
		return this.floor;
	}

	public String getMaterial() {
		return this.material;
	}

	public ArrayList<Point> getEdge_point() {
		return this.edge_point;
	}

	public ArrayList<Integer> getEdge_id() {
		return this.edge_id;
	}

}