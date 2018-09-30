package com.example.examplemod;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.dom4j.Document;

@Mod(modid = RoboRescueMod.MODID, name = RoboRescueMod.NAME, version = RoboRescueMod.VERSION)
public class RoboRescueMod {
	public static final String MODID = "examplemod";
	public static final String NAME = "Example Mod";
	public static final String VERSION = "1.0";

	private Map<Integer, Vec3d> nodes = new HashMap<>();
	private Map<Integer, Edge> edges = new HashMap<>();
	private ArrayList<Road> roads = new ArrayList<>();
	private ArrayList<Building> buildings = new ArrayList<>();

	private int road_index = 0;
	private int building_index = 0;

	private World world = null;
	private Document doc;

	private static Logger logger;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(this);
		logger = event.getModLog();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		// some example code
		logger.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());

		String GML_DIR = "/home/migly/git/rcrs-server/maps/gml/test/map/";
		// String GML_DIR = "/home/migly/git/rcrs-server-master/maps/gml/ritsumei/";

		GMLReader reader = new GMLReader();
		doc = GMLReader.openGML(GML_DIR + "map.gml");

		nodes = reader.readNode(doc);
		edges = reader.readEdge(doc);
		roads = reader.readRoads(doc, edges);
		buildings = reader.readBuildings(doc, edges);

	}

	@SubscribeEvent
	public void ServerTick(TickEvent.ServerTickEvent event) {

		if (world == null) {
			System.out.println("world is null");
			return;
		}

		if (roads == null) {
			System.out.println("building is null");
			return;
		}

		DrawMap.drawRoad(road_index, roads, nodes, world);
		road_index++;

		if (buildings == null) {
			System.out.println("building is null");
			return;
		}

		DrawMap.drawBuildings(building_index, buildings, nodes, world);
		building_index++;

	}

	@SubscribeEvent
	public void onPlayerLoggin(PlayerLoggedInEvent event) {

		world = DimensionManager.getWorld(0);

	}
}
