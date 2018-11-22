package com.roborescue.kernel;

import com.roborescue.render.Render;

import net.minecraft.world.World;

public class Cycler {

	private int time;
	private Render render;

	public World world;

	public Cycler(World world) {
		this.world = world;
		this.time = -1;
		this.render = new Render(world);
	}

	public void update() {

		switch (time) {
		case -1:
			render.renderMap();
			if (render.readyMap) {
				time++;
			}
			break;

		case 0:

			break;

		default:
			render.actionAgent();
			render.updateBuild();
			render.updateBlockade();
			break;
		}

	}

}
