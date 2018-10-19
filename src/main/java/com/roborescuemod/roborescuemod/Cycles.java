package com.roborescuemod.roborescuemod;

import java.io.InputStream;
import java.nio.file.Path;

import com.roborescuemod.buildmap.BuildMap;
import com.roborescuemod.communication.OriginalSocket;
import com.roborescuemod.communication.SocketClient;

import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public class Cycles {

	private static InputStream inputStream = null;
	private static BuildMap buildMap = null;
	private static OriginalSocket originalSocket = null;

	private World world = null;

	private int cycle = -1;
	private boolean start = false;
	private BuildCommand buildCommand = null;
	private String Path = null;

	public Cycles(World world) {
		// socket
		new Thread(new Runnable() {

			SocketClient client = null;

			@Override
			public void run() {

				client = new SocketClient(6591, "localhost");

				System.out.println("########################################");

				// originalSocket = client.connectMapData(6591,"localhost");

				while (true) {
					//System.out.println("debug");
					System.out.println("listen to server");
					System.out.println(client.subscribeText());
					// inputStream = client.subscribeGML();

					// System.out.println("debug");
				}
			}

		}).start();

		this.world = world;
	}

	public void setCommandEvent(FMLServerStartingEvent event) {
		event.registerServerCommand(buildCommand = new BuildCommand());
	}

	public void calcCycles() {

		if (world == null)
			return;

		/*
		 * set inputstream if (socketAPI_map != null && inputStream == null) inputStream
		 * = socketAPI_map.subscribeFileData();
		 */

		// ready build map
		if (buildMap == null) {

			if (inputStream != null) {
				buildMap = new BuildMap(this.world, inputStream);
			} else if ((this.Path = buildCommand.popPath()) != null) {
				buildMap = new BuildMap(this.world, this.Path);
			}

			if (buildMap == null)
				return;
			else
				cycle = -1;
		}

		///// cycle/////////////////////////
		if (cycle == -1) {
			// build map
			if (buildMap.buildMap() == 0) {
				cycle = 0;
				buildMap = null;
				inputStream = null;
				this.Path = null;
			}

		} else if (cycle == 0) {

		} else {

		}
	}

}
