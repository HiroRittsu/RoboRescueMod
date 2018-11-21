package com.roborescue.world.map;

import java.util.Map;

import com.roborescue.world.MapData;
import com.roborescue.commons.Point3D;
import com.roborescue.commons.Point3Df;
import com.roborescue.commons.PointConverter;

public class GMLMap extends MapData {

	private Point3Df max;
	private Point3Df min;
	private Point3Df centroid;

	public GMLMap(Map<Integer, Point3D> nodes) {
		this.nodes = nodes;
		Point3Df[] primary = PointConverter.calcPrimaryPoint(this.nodes);
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
