package com.module.map.parts;

import java.util.ArrayList;

public class Building extends Area {

	private int floor;
	private String material;

	public Building(int entityID, int floor, String material, ArrayList<Edge> edges) {
		super(entityID, edges);
		this.floor = floor;
		this.material = material;
	}

	public int getFloor() {
		return this.floor;
	}

	public String getMaterial() {
		return this.material;
	}

}