package com.example.examplemod;

import java.util.ArrayList;

import net.minecraft.util.math.Vec3d;

public class Building {

	private Vec3d point;
	private int id;
	private int floor;
	private String material;
	private ArrayList<Vec3d> edge_point;
	private ArrayList<Integer> edge_id;

	public Building(int id, int floor, String material, ArrayList<Vec3d> edge_point) {
		this.id = id;
		this.floor = floor;
		this.material = material;
		this.edge_point = edge_point;
	}

	public int getId() {
		return this.id;
	}

	public void setPosition(Vec3d point) {
		this.point = point;
	}

	public Vec3d getPosition() {
		return this.point;
	}

	public int getFloor() {
		return this.floor;
	}

	public String getMaterial() {
		return this.material;
	}

	public ArrayList<Vec3d> getEdge_point() {
		return this.edge_point;
	}

	public ArrayList<Integer> getEdge_id() {
		return this.edge_id;
	}

}