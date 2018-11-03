package com.roborescuemod.commons;

import java.util.HashMap;
import java.util.Map;

import com.roborescuemod.buildmap.MinecraftMap;

public class PointConverter {

	public Point3Df[] calcPrimaryPoint(Map<Integer, Point3D> nodes, Point3Df max, Point3Df min, Point3Df centroid) {

		// [0]: max, [1]:min, [2]:centroid
		Point3Df[] result = new Point3Df[3];
		
		for (int i = 0; i < result.length; i++)
			result[i] = new Point3Df(0, 0, 0);

		if (max == null)
			max = new Point3Df(0, 0, 0);

		if (min == null)
			min = new Point3Df(0, 0, 0);

		if (centroid == null)
			centroid = new Point3Df(0, 0, 0);

		max.resetPoint("MIN");
		min.resetPoint("MAX");

		for (Point3D p : nodes.values()) {

			if (min.getX() > p.getX())
				result[1].x = p.getX();

			if (min.getY() > p.getY())
				result[1].y = p.getY();

			if (min.getZ() > p.getZ())
				result[1].z = p.getZ();

			if (max.getX() < p.getX())
				result[0].x = p.getX();

			if (max.getY() < p.getY())
				result[0].y = p.getY();

			if (max.getZ() < p.getZ())
				result[0].z = p.getZ();

		}

		result[2].x = (max.getX() + min.getX()) / 2.0;
		result[2].y = (max.getY() + min.getY()) / 2.0;
		result[2].z = (max.getZ() + min.getZ()) / 2.0;

		return result;

	}

	public Map<Integer, Point3D> convertPoint(MinecraftMap minecraftMap, Point3D origin) {

		Map<Integer, Point3D> result = new HashMap<>();

		for (Map.Entry<Integer, Point3D> entry : minecraftMap.getNodes().entrySet()) {

			result.put(entry.getKey(), new Point3D(entry.getValue().getX() - origin.getX(),
					entry.getValue().getY() - origin.getY(), entry.getValue().getZ() - origin.getZ()));

		}

		return result;
	}

	public Map<Integer, Point3D> convertPoint(Map<Integer, Point3D> nodes, Point3Df centroid, Point3D origin) {

		Map<Integer, Point3D> result = new HashMap<>();

		for (Map.Entry<Integer, Point3D> entry : nodes.entrySet()) {

			result.put(entry.getKey(), new Point3D(entry.getValue().getX() - origin.getX(),
					entry.getValue().getY() - origin.getY(), entry.getValue().getZ() - origin.getZ()));

		}

		return result;

	}
}
