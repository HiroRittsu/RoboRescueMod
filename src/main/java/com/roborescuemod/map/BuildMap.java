package com.roborescuemod.map;

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
		this.doc = gReader.openStream(inputStream);
		this.gmlMap = readMap(this.doc);
		this.minecraftMap = new MinecraftMap(this.gmlMap);
		this.rescueMap = new RescueMap(this.gmlMap);

		// reset field
		this.drawMap.resetField(world, this.minecraftMap);
		this.world = world;
	}

	public BuildMap(World world, String Path) {
		this.doc = gReader.openGML(Path);
		this.gmlMap = readMap(this.doc);
		this.minecraftMap = new MinecraftMap(this.gmlMap);
		this.rescueMap = new RescueMap(this.gmlMap);

		// reset field
		this.drawMap.resetField(world, this.minecraftMap);
		this.world = world;
	}

	public MinecraftMap getMinecraftMap() {
		return this.minecraftMap;
	}

	public RescueMap getRescueMap() {
		return this.rescueMap;
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

		if (this.world == null || this.minecraftMap.getRoads() == null)
			return false;

		if (this.minecraftMap.getRoads().size() <= this.road_index)
			return true;

		this.drawMap.drawRoad(this.road_index, this.minecraftMap, this.world);
		this.road_index++;

		return false;
	}

	private boolean drawBuildings() {

		if (this.world == null || this.minecraftMap.getBuildins() == null)
			return false;

		if (this.minecraftMap.getRoads().size() <= this.building_index)
			return true;

		this.drawMap.drawBuildings(this.building_index, this.minecraftMap, this.world);
		this.building_index++;

		return false;
	}

}
