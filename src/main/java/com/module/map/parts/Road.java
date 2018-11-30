package com.module.map.parts;

import java.util.ArrayList;

public class Road extends Area {

	/*
	 * public Road(int entityID, ArrayList<Integer> edge_ids) { super(entityID,
	 * getEdge_list(edge_ids)); }
	 */

	public Road(int entityID, ArrayList<Edge> edges) {
		super(entityID, edges);
	}

}
