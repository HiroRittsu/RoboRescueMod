package com.module.map;

import java.util.Map;

import com.module.commons.Point3Df;
import com.module.map.parts.Building;
import com.module.map.parts.Edge;
import com.module.map.parts.Node;
import com.module.map.parts.Road;

public class GMLMap extends StandardMap {

	private Point3Df max;
	private Point3Df min;
	private Point3Df centroid;

	public GMLMap(Map<Integer, Node> nodes, Map<Integer, Edge> edges, Map<Integer, Road> roads,
			Map<Integer, Building> buildings) {
		this.nodes = nodes;
		this.edges = edges;
		this.roads = roads;
		this.buildings = buildings;
		Point3Df[] primary = calcPrimaryPoint(nodes);
		this.max = primary[0];
		this.min = primary[1];
		this.centroid = primary[2];
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
