package com.example.examplemod;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.dom4j.Document;

@Mod(modid = ExampleMod.MODID, name = ExampleMod.NAME, version = ExampleMod.VERSION)
public class ExampleMod {
	public static final String MODID = "examplemod";
	public static final String NAME = "Example Mod";
	public static final String VERSION = "1.0";

	private Map<Integer, Point> nodes = new HashMap<>();
	private Map<Integer, ArrayList<Point>> edges = new HashMap<>();
	private Map<Integer, ArrayList<Point>> roads = new HashMap<>();
	private ArrayList<Point> buidings = new ArrayList<>();

	private ArrayList<Integer> road_id = new ArrayList<>();
	private int load_index;

	private String GML_DIR = "/home/migly/git/rcrs-server/maps/gml/kobe/map/";

	private World world = null;
	private Document doc = GMLReader.openGML(GML_DIR + "map.gml");

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

		GMLReader reader = new GMLReader();

		nodes = reader.readNode(doc);
		edges = reader.readEdge(doc, nodes);
		roads = reader.readRoad(doc, edges);

		for (int id : roads.keySet())
			road_id.add(id);

	}

	@SubscribeEvent
	public void ServerTick(TickEvent.ServerTickEvent event) {

		if (road_id == null || world == null)
			return;

		if (load_index < road_id.size()) {
			System.out.println(road_id.get(load_index));
			for (Point node : roads.get(road_id.get(load_index))) {

				// FMLCommonHandler.instance().getMinecraftServerInstance().getCommandManager()
				// .executeCommand(FMLCommonHandler.instance().getMinecraftServerInstance(), "tp
				// @p 0 50 0");
				
				System.out.println(node.getX() + "," + node.getY());

				BlockPos pos = new BlockPos(node.getX(), 4, -1 * node.getY());
				world.setBlockState(pos, Blocks.IRON_BLOCK.getDefaultState());

			}
		}

		load_index++;

	}

	@SubscribeEvent
	public void onPlayerLoggin(PlayerLoggedInEvent event) {

		world = DimensionManager.getWorld(0);

	}
}
