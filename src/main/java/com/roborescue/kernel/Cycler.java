package com.roborescue.kernel;

import net.minecraft.world.World;

public class Cycler {

	private int time;

	public World world;

	public Cycler(World world) {
		this.world = world;
		this.time = -1;
	}

	public void update() {

		switch (time) {
		case -1:

			break;

		case 0:

			break;

		default:
			break;
		}

	}

}
