package com.roborescuemod.buildmap;

import java.util.Map;

import com.roborescuemod.commons.MapData;
import com.roborescuemod.commons.Point3D;
import com.roborescuemod.commons.Point3Df;
import com.roborescuemod.commons.PointConverter;

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
