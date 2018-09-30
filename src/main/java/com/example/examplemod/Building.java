package com.example.examplemod;

import java.awt.Point;
import java.util.ArrayList;

public class Building {

	private Point point;
	private int id;
	private int floor;
	private String material;
	private ArrayList<Integer> edge_id;

	public Building(int id, int floor, String material, ArrayList<Integer> edge_ids) {
		this.id = id;
		this.floor = floor;
		this.material = material;
	}

	public int getId() {
		return this.id;
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

	public ArrayList<Integer> getEdgeId() {
		return this.edge_id;
	}

}