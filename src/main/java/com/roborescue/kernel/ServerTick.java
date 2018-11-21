package com.roborescue.kernel;

import com.roborescue.information.Agentinfo;
import com.roborescue.information.Worldinfo;

import net.minecraft.world.World;

public class ServerTick {

	public Cycler cycler;

	public ServerTick(World world) {
		cycler = new Cycler(world);
		new Worldinfo();
		new Agentinfo();
	}

	public void update() {
		cycler.update();
	}
}
