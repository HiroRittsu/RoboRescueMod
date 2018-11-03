package com.roborescuemod.kernel;

import java.io.InputStream;
import java.nio.file.Path;

import com.roborescuemod.buildmap.BuildMap;
import com.roborescuemod.communication.OriginalSocket;
import com.roborescuemod.communication.SocketClient;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public class Cycles {

	private static InputStream inputStream = null;
	private static BuildMap buildMap = null;

	private World world = null;
	public static Entity zombie;

	private int cycle = -1;
	private boolean start = false;
	private BuildCommand buildCommand = null;
	private String Path = null;

	private SocketClient client = null;

	public Cycles(World world) {
		client = new SocketClient();
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
				cycle = -1;
			} else if ((this.Path = buildCommand.popPath()) != null) {
				buildMap = new BuildMap(this.world, this.Path);
				cycle = -1;
			}

			/*
			 * if (buildMap == null) return; else cycle = -1;
			 */
			if (buildMap == null && cycle == -1)
				return;
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

			System.out.println("##############spwn");

			zombie = new EntityZombie(world);

			zombie.setPosition(100, 10, 100);

			world.spawnEntity(zombie);

			cycle++;

		} else {

		}
	}

}
