package com.module.map;

import com.module.commons.Point3Df;

public class RescueMap extends StandardMap {

	public Point3Df max;
	public Point3Df min;
	public Point3Df centroid;

	public RescueMap(GMLMap gmlMap) {
		this.nodes = convertRescueMap_node(gmlMap);
		this.edges = convertRescueMap_edge(gmlMap, this.nodes);

		Point3Df[] primary = calcPrimaryPoint(this.nodes);
		this.max = primary[0];
		this.min = primary[1];
		this.centroid = primary[2];

		this.roads = convertRescueMap_road(gmlMap, this.edges);
		this.buildings = convertRescueMap_building(gmlMap, this.edges);
	}

	public Point3Df getCentroid() {
		return this.centroid;
	}

	public Point3Df getMaxPoint() {
		return this.max;
	}

	public Point3Df getMinPoint() {
		return this.min;
	}
}
