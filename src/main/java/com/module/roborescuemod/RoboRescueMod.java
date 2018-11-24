package com.module.roborescuemod;

import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
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

import org.apache.logging.log4j.Logger;

import com.module.socket.SocketClient;

@Mod(modid = RoboRescueMod.MODID, name = RoboRescueMod.NAME, version = RoboRescueMod.VERSION)
public class RoboRescueMod {
	public static final String MODID = "roborescuemod";
	public static final String NAME = "RoboRescue Mod";
	public static final String VERSION = "2.0";
	public static Minecraft mc = Minecraft.getMinecraft();
	public ServerTick serverTick;
	public SocketClient socketClient;
	public World world;

	private static Logger logger;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(this);
		logger = event.getModLog();
		socketClient = new SocketClient(12345, "localhost");
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		// some example code
		logger.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
	}

	@SubscribeEvent
	public void onPlayerLoggin(PlayerLoggedInEvent event) {
		world = DimensionManager.getWorld(0);
		serverTick = new ServerTick(world);
	}

	@SubscribeEvent
	public void ServerTick(TickEvent.ServerTickEvent event) {
		if (serverTick != null) {
			serverTick.update();
		}
	}
}
