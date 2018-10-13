package com.roborescuemod.roborescuemod;

import java.io.InputStream;

import com.roborescuemod.buildmap.BuildMap;
import com.roborescuemod.communication.SocketClient;

import net.minecraft.world.World;

public class Cycles {

	SocketClient socketClient_map = new SocketClient();
	private static InputStream inputStream;
	private static BuildMap buildMap = null;

	private World world = null;

	private int cycle = -1;
	private boolean start = false;

	public Cycles(World world) {
		// socket
		new Thread(new Runnable() {
			@Override
			public void run() {
				inputStream = socketClient_map.connectMapData(6591).subscribeFileData();
			}
		}).start();

		this.world = world;
	}

	public void calcCycles() {

		if (world != null && inputStream != null) {

			// ready build map
			if (buildMap == null) {
				buildMap = new BuildMap(this.world, inputStream);
			}

			if (cycle == -1) {
				// build map
				if (buildMap.buildMap() == 0)
					cycle = 0;

			} else if (cycle == 0) {

			} else {

			}
		}
	}

}
