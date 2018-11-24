package com.module.map.parts;

import java.util.ArrayList;

import com.module.commons.Point3D;
import com.module.information.Worldinfo;

public class Area {

	public int entityID;
	public Point3D position;
	public ArrayList<Edge> edges;

	public Area(int entityID, ArrayList<Edge> edges) {
		this.entityID = entityID;
		this.edges = edges;
		this.position = calcPosition(edges);
	}

	public int getID() {
		return entityID;
	}

	public Point3D getPosition() {
		return position;
	}

	public ArrayList<Edge> getEdges() {
		return edges;
	}

	//////////////////////////////////////////////////////

	public Point3D calcPosition(ArrayList<Edge> edges) {

		int sumX = 0;
		int sumY = 0;
		int sumZ = 0;
		int count = 0;

		for (Edge edge : edges) {
			sumX += edge.getPosition().x;
			sumY += edge.getPosition().y;
			sumZ += edge.getPosition().z;
			count++;
		}
		return new Point3D(sumX / count, sumY / count, -sumZ / count);
	}

	public static ArrayList<Edge> getEdge_list(ArrayList<Integer> edge_ids) {
		ArrayList<Edge> edges = new ArrayList<>();
		for (int id : edge_ids) {
			edges.add(Worldinfo.edges.get(id));
		}
		return edges;
	}
}
