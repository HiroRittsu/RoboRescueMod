package com.roborescuemod.buildmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.roborescuemod.commons.Point3D;

public class MinecraftMap {

	public Map<Integer, Point3D> nodes = new HashMap<>();
	public Map<Integer, Edge> edges = new HashMap<>();
	public ArrayList<Road> roads = new ArrayList<>();
	public ArrayList<Building> buildings = new ArrayList<>();

	public MinecraftMap() {
		
	}

	public Point3D getCentroid() {
		
		return null;
	}

	public Point3D getMaxPoint() {
		return null;
	}

	public Point3D getMinPoint() {
		return null;
	}

}
