package com.roborescuemod.commons;

import java.util.Map;

public class PointConverter {

	public void calcPrimaryPoint(Map<Integer, Point3D> nodes, Point3Df max, Point3Df min, Point3Df centroid) {

		max.resetPoint("MIN");
		min.resetPoint("MAX");

		for (Point3D p : nodes.values()) {

			if (min.getX() > p.getX())
				min.x = p.getX();

			if (min.getY() > p.getY())
				min.y = p.getY();

			if (min.getZ() > p.getZ())
				min.z = p.getZ();

			if (max.getX() < p.getX())
				max.x = p.getX();

			if (max.getY() < p.getY())
				max.y = p.getY();

			if (max.getZ() < p.getZ())
				max.z = p.getZ();

		}

		centroid.x = (max.getX() + min.getX()) / 2.0;
		centroid.y = (max.getY() + min.getY()) / 2.0;
		centroid.z = (max.getZ() + min.getZ()) / 2.0;

	}
}
