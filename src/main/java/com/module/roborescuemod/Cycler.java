package com.module.roborescuemod;

import com.module.information.Worldinfo;
import com.module.render.Render;
import com.module.socket.SocketClient;

import net.minecraft.world.World;

public class Cycler {

	private Render render;

	public World world;

	public Cycler(World world) {
		this.world = world;
		this.render = new Render(world);
	}

	public void update() {

		switch (Worldinfo.time) {
		case -1:
			render.renderMap();
			if (Worldinfo.completeMap) {
				Worldinfo.time++;
				SocketClient.publishCommand("ready_map");
			}
			break;

		case 0:
			render.renderScenario();
			if (Worldinfo.completeScenario) {
				Worldinfo.time++;
				SocketClient.publishCommand("ready_scenario");
			}
			break;

		default:
			render.renderStetas();
			if (Worldinfo.completeStetas) {
				Worldinfo.readyStetas = false;
				Worldinfo.completeStetas = false;
				SocketClient.publishCommand("next");
			}
			break;
		}

	}

}
