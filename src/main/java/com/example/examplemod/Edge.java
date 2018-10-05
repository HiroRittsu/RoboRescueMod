package com.example.examplemod;

import java.awt.Polygon;

public class Edge extends Polygon {

	Point3D position;
	int id;
	Integer[] nodes;

	public Edge(int id, Integer[] nodes) {
		this.id = id;
		this.nodes = nodes;
	}

	public int getId() {
		return this.id;
	}

	public Integer[] getNodeID() {
		return this.nodes;
	}

	public double getStartId() {
		return nodes[0];
	}

	public double getEndId() {
		return nodes[1];
	}
}
