package com.roborescuemod.roborescuemod;

import java.io.InputStream;
import java.nio.file.Path;

import com.roborescuemod.buildmap.BuildMap;
import com.roborescuemod.communication.SocketAPI;
import com.roborescuemod.communication.SocketClient;

import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public class Cycles {

	SocketClient socketClient_map = new SocketClient();
	private static InputStream inputStream;
	private static BuildMap buildMap = null;
	private static SocketAPI socketAPI_map = null;

	private World world = null;

	private int cycle = -1;
	private boolean start = false;
	private BuildCommand buildCommand = null;
	private String Path = null;

	public Cycles(World world) {
		// socket
		new Thread(new Runnable() {
			@Override
			public void run() {
				// inputStream = socketClient_map.connectMapData(6591).subscribeFileData();
				socketAPI_map = socketClient_map.connectMapData(6591);
			}
		}).start();

		this.world = world;

	}

	public void setCommandEvent(FMLServerStartingEvent event) {
		//event.registerServerCommand(new BuildCommand());
	}

	public void calcCycles() {
		
		System.out.println("cycles ##################################");

		if (world == null)
			return;

		// set inputstream
		if (socketAPI_map != null && inputStream == null)
			inputStream = socketAPI_map.subscribeFileData();

		// ready build map
		if (buildMap == null) {
			if (inputStream != null) {
				buildMap = new BuildMap(this.world, inputStream);
			}

			if ((this.Path = buildCommand.getPath()) != null) {
				buildMap = new BuildMap(this.world, this.Path);
			}

			if (buildMap == null)
				return;
		}

		///// cycle/////////////////////////
		if (cycle == -1) {
			// build map
			if (buildMap.buildMap() == 0) {
				cycle = 0;
			}

		} else if (cycle == 0) {

		} else {

		}
	}

}
