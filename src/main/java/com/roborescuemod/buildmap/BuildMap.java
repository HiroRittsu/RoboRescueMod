package com.roborescuemod.buildmap;

import java.io.InputStream;
import org.dom4j.Document;

import net.minecraft.world.World;

public class BuildMap {

	private GMLReader gReader = new GMLReader();
	private DrawMap drawMap = new DrawMap();
	private MinecraftMap rescueMap = new MinecraftMap();
	private Document doc = null;
	private World world = null;
	private int road_index = 0;
	private int building_index = 0;

	public BuildMap(World world, InputStream inputStream) {
		doc = gReader.openStream(inputStream);
		readMap(doc, this.rescueMap);

		// reset field
		drawMap.resetField(world, rescueMap.nodes);
		this.world = world;
	}

	public BuildMap(World world, String Path) {
		doc = gReader.openGML(Path);
		readMap(doc, this.rescueMap);

		// reset field
		drawMap.resetField(world, rescueMap.nodes);
		this.world = world;
	}

	private void readMap(Document doc, MinecraftMap rescueMap) {
		rescueMap.nodes = gReader.readNode(doc);
		rescueMap.edges = gReader.readEdge(doc);
		rescueMap.roads = gReader.readRoads(doc, rescueMap.edges);
		rescueMap.buildings = gReader.readBuildings(doc, rescueMap.edges);
	}

	private boolean drawRoad() {

		if (world == null || rescueMap.roads == null)
			return false;

		if (rescueMap.roads.size() <= this.road_index)
			return true;

		drawMap.drawRoad(this.road_index, rescueMap.roads, rescueMap.nodes, world);
		this.road_index++;

		return false;
	}

	private boolean drawBuildings() {

		if (world == null || rescueMap.buildings == null)
			return false;

		if (rescueMap.buildings.size() <= this.building_index)
			return true;

		drawMap.drawBuildings(this.building_index, rescueMap.buildings, rescueMap.nodes, world);
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
