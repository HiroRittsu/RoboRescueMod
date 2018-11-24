package com.module.map.parts;

import com.module.commons.Point3D;

public class Node {

	public int entityID;
	public Point3D point; // GMLmap座標

	public Node(int entityID, Point3D point) {
		this.entityID = entityID;
		this.point = point;
	}

	public Point3D getPoint() {
		return point;
	}

	public int getID() {
		return entityID;
	}
}
