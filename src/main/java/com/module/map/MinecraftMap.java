package com.module.map;

import com.module.commons.Point3D;
import com.module.commons.Point3Df;
import com.module.map.parts.Building;
import com.module.map.parts.Edge;
import com.module.map.parts.Node;
import com.module.map.parts.Road;

public class MinecraftMap extends StandardMap {

	public static Point3Df max;
	public static Point3Df min;
	public static Point3Df centroid;

	public MinecraftMap(GMLMap gmlMap) {
		this.nodes = convertMinecraftMap(gmlMap);
		Point3Df[] primary = calcPrimaryPoint(nodes);
		MinecraftMap.max = primary[0];
		MinecraftMap.min = primary[1];
		MinecraftMap.centroid = primary[2];

		this.edges = gmlMap.getEdges();
		this.roads = gmlMap.getRoads();
		this.buildings = gmlMap.getBuildins();
	}

	public Point3D getPosition(int enetityID) {

		int sumX = 0;
		int sumY = 0;
		int sumZ = 0;
		int count = 0;

		// get building entytyID
		for (Building building : buildings) {
			if (building.getID() == enetityID) {
				return building.getPosition();
			}
		}

		// get road entityID
		for (Road road : roads) {
			if (road.getID() == enetityID) {
				for (Edge edge : road.getEdges()) {
					for (Node node : edge.getNodes()) {
						sumX += node.getPoint().x;
						sumY += node.getPoint().y;
						sumZ += node.getPoint().z;
						count++;
					}
				}
				System.out.println("sumY" + sumY);
				return new Point3D(sumX / count, sumY / count, -sumZ / count);
			}
		}

		return null;
	}
}
