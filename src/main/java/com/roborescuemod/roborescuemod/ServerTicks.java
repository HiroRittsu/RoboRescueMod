package com.roborescuemod.roborescuemod;

import net.minecraft.world.World;

public class ServerTicks {

	private Cycles cycles = null;

	public ServerTicks(World world) {
		cycles = new Cycles(world);
	}

	public void calcTicks() {
		cycles.calcCycles();
	}

}
