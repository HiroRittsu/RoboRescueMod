package kernel;

import net.minecraft.world.World;
import socket.SocketClient;

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
