package com.roborescuemod.kernel;

import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public class ServerTicks {

	private Cycles cycles = null;

	public ServerTicks(World world) {
		cycles = new Cycles(world);
	}

	public void calcTicks() {
		cycles.calcCycles();
	}

	public void setCommandEvent(FMLServerStartingEvent event) {
		cycles.setCommandEvent(event);
	}

}
