package com.example.examplemod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class GMLMainNode extends CommandBase {

	public Map<Integer, Point3D> nodes = new HashMap<>();
	public Map<Integer, Edge> edges = new HashMap<>();
	public ArrayList<Road> roads = new ArrayList<>();
	public ArrayList<Building> buildings = new ArrayList<>();

	public int road_index = 0;
	public int building_index = 0;

	public World world = null;
	public Document doc;

	public void setWorld(World world) {
		this.world = world;
	}

	public void init(String Path) {

		GMLReader reader = new GMLReader();
		doc = GMLReader.openGML(Path + "map.gml");

		nodes = reader.readNode(doc);
		edges = reader.readEdge(doc);
		roads = reader.readRoads(doc, edges);
		buildings = reader.readBuildings(doc, edges);

		// 初期化
		DrawMap.resetField(world, nodes);
	}

	public void drawRoad() {
		if (world == null || this.roads == null)
			return;
		if (this.roads.size() > this.road_index) {
			DrawMap.drawRoad(this.road_index, this.roads, this.nodes, world);
			this.road_index++;
		}
	}

	public void drawBuildings() {
		if (world == null || this.buildings == null)
			return;
		if (this.buildings.size() > this.building_index) {
			DrawMap.drawBuildings(this.building_index, this.buildings, this.nodes, world);
			this.building_index++;
		}
	}

	@Override
	public String getName() {
		return "loadGML";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return null;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		init(args[0]);
		this.road_index = 0;
		this.building_index = 0;
	}
}
