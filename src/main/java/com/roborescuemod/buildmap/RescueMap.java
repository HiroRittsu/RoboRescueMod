package com.roborescuemod.buildmap;

import java.util.Map;

import com.roborescuemod.commons.MapData;
import com.roborescuemod.commons.Point3D;
import com.roborescuemod.commons.Point3Df;
import com.roborescuemod.commons.PointConverter;

public class RescueMap extends MapData {

	private PointConverter converter = new PointConverter();
	private Point3Df max;
	private Point3Df min;
	private Point3Df centroid;

	public RescueMap(Map<Integer, Point3D> nodes) {
		Point3Df[] primary = converter.calcPrimaryPoint(nodes);
		this.max = primary[0];
		this.min = primary[1];
		this.centroid = primary[2];

		this.nodes = converter.convertPoint(nodes, this.centroid, new Point3D(0, 0, 0));

		primary = converter.calcPrimaryPoint(this.nodes);
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
