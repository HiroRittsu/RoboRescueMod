package com.roborescuemod.buildmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.roborescuemod.commons.Point3D;

public class RescueMap {

	public Map<Integer, Point3D> nodes = new HashMap<>();
	public Map<Integer, Edge> edges = new HashMap<>();
	public ArrayList<Road> roads = new ArrayList<>();
	public ArrayList<Building> buildings = new ArrayList<>();

	public RescueMap() {

	}

	private Point3D calcCentroid(ArrayList<Point3D> points) {

		int count = 0;
		int sum_x = 0;
		int sum_y = 0;
		int sum_z = 0;

		for (Point3D point3d : points) {
			sum_x += point3d.x;
			sum_y += point3d.y;
			sum_z += point3d.z;
			count++;
		}

		return (count != 0 ? new Point3D(sum_x / count, sum_y / count, sum_z / count) : null);
	}

}
