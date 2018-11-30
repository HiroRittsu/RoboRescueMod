package com.module.map;

import com.module.commons.Point3Df;

public class RescueMap extends StandardMap {

	public Point3Df max;
	public Point3Df min;
	public Point3Df centroid;

	public RescueMap(GMLMap gmlMap) {
		this.nodes = convertRescueMap(gmlMap);

		Point3Df[] primary = calcPrimaryPoint(this.nodes);
		this.max = primary[0];
		this.min = primary[1];
		this.centroid = primary[2];

		this.edges = gmlMap.getEdges();
		this.roads = gmlMap.getRoads();
		this.buildings = gmlMap.getBuildins();
	}

}
