package kernel;

import information.Worldinfo;
import net.minecraft.world.World;
import render.Render;
import socket.SocketClient;

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
			if (Worldinfo.readyMap) {
				time++;
				SocketClient.publishCommand("ready_map");
			}
			break;

		case 0:
			render.renderScenario();
			if (Worldinfo.readyScenario) {
				time++;
				SocketClient.publishCommand("ready_scenario");
			}
			break;

		default:
			render.actionAgent();
			render.updateBuild();
			render.updateBlockade();
			break;
		}

	}

}
