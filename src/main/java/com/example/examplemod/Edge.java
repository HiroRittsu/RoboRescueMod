package com.example.examplemod;

import net.minecraft.util.math.Vec3d;

public class Edge {

	Vec3d position;
	int id;
	Integer[] nodes;

	public Edge(int id, Integer[] nodes) {
		this.id = id;
		this.nodes = nodes;
	}

	public int getId() {
		return this.id;
	}

	public double getStartId() {
		return nodes[0];
	}

	public double getEndId() {
		return nodes[1];
	}
}
