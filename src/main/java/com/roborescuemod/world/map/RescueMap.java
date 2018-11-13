package com.roborescuemod.world.map;

import com.roborescuemod.commons.MapData;
import com.roborescuemod.commons.Point3Df;
import com.roborescuemod.commons.PointConverter;

public class RescueMap extends MapData {

	public static Point3Df max;
	public static Point3Df min;
	public static Point3Df centroid;

	public RescueMap(GMLMap gmlMap) {
		this.nodes = PointConverter.convertRescueMap(gmlMap);

		Point3Df[] primary = PointConverter.calcPrimaryPoint(this.nodes);
		RescueMap.max = primary[0];
		RescueMap.min = primary[1];
		RescueMap.centroid = primary[2];

		this.edges = gmlMap.getEdges();
		this.roads = gmlMap.getRoads();
		this.buildings = gmlMap.getBuildins();
	}

}
