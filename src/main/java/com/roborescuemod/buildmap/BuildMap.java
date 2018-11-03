package com.roborescuemod.buildmap;

import java.io.InputStream;
import org.dom4j.Document;

import net.minecraft.world.World;

public class BuildMap {

	private GMLReader gReader = new GMLReader();
	private DrawMap drawMap = new DrawMap();
	private MinecraftMap minecraftMap = null;
	private Document doc = null;
	private World world = null;
	private int road_index = 0;
	private int building_index = 0;

	public BuildMap(World world, InputStream inputStream) {
		doc = gReader.openStream(inputStream);
		this.minecraftMap = readMap(doc);

		// reset field
		drawMap.resetField(world, this.minecraftMap);
		this.world = world;
	}

	public BuildMap(World world, String Path) {
		doc = gReader.openGML(Path);
		this.minecraftMap = readMap(doc);

		// reset field
		drawMap.resetField(world, this.minecraftMap);
		this.world = world;
	}

	private MinecraftMap readMap(Document doc) {

		MinecraftMap minecraftMap = new MinecraftMap(gReader.readNode(doc));

		minecraftMap.setEdges(gReader.readEdge(doc));
		minecraftMap.setRoads(gReader.readRoads(doc, minecraftMap.getEdges()));
		minecraftMap.setBuildings(gReader.readBuildings(doc, minecraftMap.getEdges()));

		return minecraftMap;
	}

	private boolean drawRoad() {

		if (world == null || minecraftMap.getRoads() == null)
			return false;

		if (minecraftMap.getRoads().size() <= this.road_index)
			return true;

		drawMap.drawRoad(this.road_index, minecraftMap, world);
		this.road_index++;

		return false;
	}

	private boolean drawBuildings() {

		if (world == null || minecraftMap.getBuildins() == null)
			return false;

		if (minecraftMap.getRoads().size() <= this.building_index)
			return true;

		drawMap.drawBuildings(this.building_index, minecraftMap, world);
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
