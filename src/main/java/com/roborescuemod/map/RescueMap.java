package com.roborescuemod.map;

import com.roborescuemod.commons.MapData;
import com.roborescuemod.commons.Point3Df;
import com.roborescuemod.commons.PointConverter;

public class RescueMap extends MapData {

	private Point3Df max;
	private Point3Df min;
	private Point3Df centroid;

	public RescueMap(GMLMap gmlMap) {
		this.nodes = PointConverter.convertRescueMap(gmlMap);

		Point3Df[] primary = PointConverter.calcPrimaryPoint(this.nodes);
		this.max = primary[0];
		this.min = primary[1];
		this.centroid = primary[2];

		this.edges = gmlMap.getEdges();
		this.roads = gmlMap.getRoads();
		this.buildings = gmlMap.getBuildins();
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
