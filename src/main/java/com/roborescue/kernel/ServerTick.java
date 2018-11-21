package com.roborescue.kernel;

import net.minecraft.world.World;

public class ServerTick {

	public Cycler cycler;

	public ServerTick(World world) {
		cycler = new Cycler(world);
	}

	public void update() {
		cycler.update();
	}
}
