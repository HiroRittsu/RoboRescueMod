package com.module.map;

import com.module.commons.Point3Df;

public class MinecraftMap extends StandardMap {

	public Point3Df max;
	public Point3Df min;
	public Point3Df centroid;

	public MinecraftMap(GMLMap gmlMap) {
		this.nodes = convertMinecraftMap_node(gmlMap);
		this.edges = convertMinecraftMap_edge(gmlMap, this.nodes);

		Point3Df[] primary = calcPrimaryPoint(nodes);
		this.max = primary[0];
		this.min = primary[1];
		this.centroid = primary[2];

		this.roads = convertMinecraftMap_road(gmlMap, this.edges);
		this.buildings = convertMinecraftMap_building(gmlMap, this.buildings);
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
