package com.module.map;

import com.module.commons.Point3Df;

public class RescueMap extends StandardMap {

	public static Point3Df max;
	public static Point3Df min;
	public static Point3Df centroid;

	public RescueMap(GMLMap gmlMap) {
		this.nodes = convertRescueMap(gmlMap);

		Point3Df[] primary = calcPrimaryPoint(this.nodes);
		RescueMap.max = primary[0];
		RescueMap.min = primary[1];
		RescueMap.centroid = primary[2];

		this.edges = gmlMap.getEdges();
		this.roads = gmlMap.getRoads();
		this.buildings = gmlMap.getBuildins();
	}

}
