package com.roborescue.kernel;

import com.roborescue.infomation.Agentinfo;
import com.roborescue.infomation.Worldinfo;

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
