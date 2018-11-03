package com.roborescuemod.commons;

import java.util.Map;

public class PointConverter {

	public void calcPrimaryPoint(Map<Integer, Point3D> nodes, Point3Df max, Point3Df min, Point3Df centroid) {

		max.x = Double.MIN_VALUE;
		max.y = Double.MIN_VALUE;
		max.z = Double.MIN_VALUE;
		
		for (Point3D p : nodes.values()) {

			if (p.getX() > min.getX()) {
				
			}

		}

	}
}
