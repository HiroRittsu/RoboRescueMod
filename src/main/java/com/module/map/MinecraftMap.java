package com.module.map;

import java.util.Map;

import com.module.commons.Point3Df;
import com.module.map.parts.Node;

public class MinecraftMap extends StandardMap {

	public Point3Df max;
	public Point3Df min;
	public Point3Df centroid;

	public Map<Integer, Node> minecraft_node;

	public MinecraftMap(GMLMap gmlMap) {
		this.nodes = convertMinecraftMap(gmlMap);
		Point3Df[] primary = calcPrimaryPoint(nodes);
		this.max = primary[0];
		this.min = primary[1];
		this.centroid = primary[2];

		this.edges = gmlMap.getEdges();
		this.roads = gmlMap.getRoads();
		this.buildings = gmlMap.getBuildins();
	}
}
