package com.module.roborescuemod;

import com.module.socket.SocketClient;

import net.minecraft.world.World;

public class ServerTick {

	public Cycler cycler;
	public SocketClient socketClient;

	public ServerTick(World world) {
		cycler = new Cycler(world);
	}

	public void update() {
		cycler.update();
	}
}
