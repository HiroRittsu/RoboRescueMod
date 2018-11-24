package com.module.map;

import com.module.commons.Point3Df;
import com.module.information.Worldinfo;

public class GMLMap extends StandardMap {

	private Point3Df max;
	private Point3Df min;
	private Point3Df centroid;

	public GMLMap() {
		this.nodes = Worldinfo.getNodes();
		this.edges = Worldinfo.getEdges();
		this.roads = Worldinfo.getRoads();
		this.buildings = Worldinfo.getBuildings();
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
