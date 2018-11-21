package com.roborescue.kernel;

import net.minecraft.world.World;

public class Cycler {

	public World world;

	public Cycler(World world) {
		this.world = world;
	}

	public void update() {
		System.out.println("cycle");
	}

}
