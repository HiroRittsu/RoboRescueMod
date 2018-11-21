package com.roborescue.world.map;

import com.roborescue.commons.Point3Df;
import com.roborescue.commons.PointConverter;
import com.roborescue.world.MapData;

public class MinecraftMap extends MapData {

	public static Point3Df max;
	public static Point3Df min;
	public static Point3Df centroid;

	public MinecraftMap(GMLMap gmlMap) {
		this.nodes = PointConverter.convertMinecraftMap(gmlMap);

		Point3Df[] primary = PointConverter.calcPrimaryPoint(this.nodes);
		MinecraftMap.max = primary[0];
		MinecraftMap.min = primary[1];
		MinecraftMap.centroid = primary[2];

		this.edges = gmlMap.getEdges();
		this.roads = gmlMap.getRoads();
		this.buildings = gmlMap.getBuildins();
	}
}
