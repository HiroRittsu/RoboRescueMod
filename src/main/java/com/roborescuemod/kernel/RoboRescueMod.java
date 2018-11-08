package com.roborescuemod.kernel;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import org.apache.logging.log4j.Logger;

import com.roborescuemod.agent.EntitySample;
import com.roborescuemod.agent.RenderSample;

@Mod(modid = RoboRescueMod.MODID, name = RoboRescueMod.NAME, version = RoboRescueMod.VERSION)
public class RoboRescueMod {
	public static final String MODID = "roborescuemod";
	public static final String NAME = "RoboRescue Mod";
	public static final String VERSION = "1.0";

	// private GMLMainNode gMainNode = new GMLMainNode();
	private ServerTicks serverTicks = null;
	private FMLServerStartingEvent event = null;
	// private MobileSockets mobileSockets = new MobileSockets();

	private World world = null;
	private BuildCommand buildCommand = null;

	private static Logger logger;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(this);
		logger = event.getModLog();
		// mobileSockets.client(12345);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		// some example code
		logger.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());

		EntityRegistry.registerModEntity((new ResourceLocation("SampleEntity")), EntitySample.class, "SampleEntity", 0,
				this, 250, 1, false);
		EntityRegistry.addSpawn(EntitySample.class, 20, 1, 4, EnumCreatureType.CREATURE, Biomes.PLAINS);

	}

	@SubscribeEvent
	public void ServerTick(TickEvent.ServerTickEvent event) {
		if (serverTicks != null)
			serverTicks.calcTicks();
	}

	@SubscribeEvent
	public void onPlayerLoggin(PlayerLoggedInEvent event) {
		world = DimensionManager.getWorld(0);
		serverTicks = new ServerTicks(world);
		serverTicks.setCommandEvent(this.event);
	}

	@EventHandler
	public void serverStarting(FMLServerStartingEvent event) {
		this.event = event;
	}
}
