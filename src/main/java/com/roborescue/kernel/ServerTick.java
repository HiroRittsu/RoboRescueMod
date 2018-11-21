package com.roborescue.kernel;

import com.roborescue.information.Agentinfo;
import com.roborescue.information.Worldinfo;
import com.roborescue.socket.SocketClient;

import net.minecraft.world.World;

public class ServerTick {

	public Cycler cycler;
	public SocketClient socketClient;

	public ServerTick(World world) {
		cycler = new Cycler(world);
		socketClient = new SocketClient(12345, "localhost");
		new Worldinfo();
		new Agentinfo();
	}

	public void update() {
		cycler.update();
	}
}
