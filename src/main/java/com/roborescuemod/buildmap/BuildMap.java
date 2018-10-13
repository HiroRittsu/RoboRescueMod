package com.roborescuemod.buildmap;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.dom4j.Document;

import com.roborescuemod.commons.DrawMap;
import com.roborescuemod.commons.GMLReader;
import com.roborescuemod.commons.Point3D;

import net.minecraft.world.World;

public class BuildMap {

	public Map<Integer, Point3D> nodes = new HashMap<>();
	public Map<Integer, Edge> edges = new HashMap<>();
	public ArrayList<Road> roads = new ArrayList<>();
	public ArrayList<Building> buildings = new ArrayList<>();
	private GMLReader gReader = new GMLReader();
	private DrawMap drawMap = new DrawMap();
	private Document doc = null;
	private World world = null;
	private int road_index = 0;
	private int building_index = 0;

	public BuildMap(World world, InputStream inputStream) {
		doc = gReader.openStream(inputStream);
		nodes = gReader.readNode(doc);
		edges = gReader.readEdge(doc);
		roads = gReader.readRoads(doc, edges);
		buildings = gReader.readBuildings(doc, edges);

		// reset field
		drawMap.resetField(world, nodes);

		this.world = world;
	}

	private boolean drawRoad() {

		if (world == null || this.roads == null)
			return false;

		if (this.roads.size() <= this.road_index)
			return true;

		drawMap.drawRoad(this.road_index, this.roads, this.nodes, world);
		this.road_index++;

		return false;
	}

	private boolean drawBuildings() {

		if (world == null || this.buildings == null)
			return false;

		if (this.buildings.size() <= this.building_index)
			return true;

		drawMap.drawBuildings(this.building_index, this.buildings, this.nodes, world);
		this.building_index++;

		return false;
	}

	public int buildMap() {
		if (drawBuildings() && drawRoad()) {
			return 0;
		}
		return 1;
	}

}
