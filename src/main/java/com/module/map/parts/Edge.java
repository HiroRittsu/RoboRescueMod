package com.module.map.parts;

import com.module.commons.Point3D;

public class Edge {

	Point3D position;
	int entityID;
	Node[] nodes;

	public Edge(int entityID, Node first, Node end) {
		this.entityID = entityID;
		this.nodes = new Node[2];
		nodes[0] = first;
		nodes[1] = end;
		this.position = calcPosition(first, end);
	}

	public int getId() {
		return this.entityID;
	}

	public Point3D getPosition() {
		return position;
	}

	public Integer[] getNodeID() {
		Integer[] ids = new Integer[2];
		ids[0] = nodes[0].entityID;
		ids[1] = nodes[1].entityID;
		return ids;
	}

	public Node[] getNodes() {
		return nodes;
	}

	public Node getStartNode() {
		return nodes[0];
	}

	public Node getEndNode() {
		return nodes[1];
	}

	public Point3D calcPosition(Node first, Node end) {
		return new Point3D((first.getPoint().x + end.getPoint().x) / 2, (first.getPoint().y + end.getPoint().y) / 2,
				(first.getPoint().z + end.getPoint().z) / 2);
	}
}
