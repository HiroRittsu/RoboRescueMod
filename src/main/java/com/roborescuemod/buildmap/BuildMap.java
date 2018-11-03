package com.roborescuemod.buildmap;

import java.io.InputStream;
import org.dom4j.Document;

import net.minecraft.world.World;

public class BuildMap {

	private GMLReader gReader = new GMLReader();
	private DrawMap drawMap = new DrawMap();
	private GMLMap gmlMap = null;
	private MinecraftMap minecraftMap = null;
	private RescueMap rescueMap = null;
	private Document doc = null;
	private World world = null;
	private int road_index = 0;
	private int building_index = 0;

	public BuildMap(World world, InputStream inputStream) {
		doc = gReader.openStream(inputStream);
		gmlMap = readMap(doc);
		minecraftMap = new MinecraftMap(gmlMap);
		rescueMap = new RescueMap(gmlMap);

		// reset field
		drawMap.resetField(world, minecraftMap);
		this.world = world;
	}

	public BuildMap(World world, String Path) {
		doc = gReader.openGML(Path);
		gmlMap = readMap(doc);
		minecraftMap = new MinecraftMap(gmlMap);
		rescueMap = new RescueMap(gmlMap);

		// reset field
		drawMap.resetField(world, minecraftMap);
		this.world = world;
	}

	public MinecraftMap getMinecraftMap() {
		return this.minecraftMap;
	}

	public RescueMap getRescueMap() {
		return rescueMap;
	}

	public int buildMap() {
		if (drawBuildings() && drawRoad()) {
			return 0;
		}
		return 1;
	}

	private GMLMap readMap(Document doc) {

		GMLMap gmlMap = new GMLMap(gReader.readNode(doc));
		gmlMap.setEdges(gReader.readEdge(doc));
		gmlMap.setRoads(gReader.readRoads(doc, gmlMap.getEdges()));
		gmlMap.setBuildings(gReader.readBuildings(doc, gmlMap.getEdges()));

		return gmlMap;
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

}
