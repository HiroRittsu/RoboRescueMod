package com.roborescue.render;

import com.roborescue.information.Worldinfo;
import com.roborescue.render.map.RenderMap;

import net.minecraft.world.World;

public class Render {

	public World world;
	public RenderMap renderMap;
	private int build_index = 0;
	private int road_index = 0;

	public Render(World world) {
		this.world = world;
		this.renderMap = new RenderMap();
	}

	public void renderMap() {
		if (Worldinfo.canMinecraftMap()) {
			// Road
			if (road_index != -1) {
				if (renderMap.drawRoad(road_index, Worldinfo.minecraftMap, world)) {
					road_index++;
				} else {
					road_index = -1;
				}
			}
			// Building
			if (build_index != -1) {
				if (renderMap.drawBuildings(build_index, Worldinfo.minecraftMap, world)) {
					build_index++;
					System.out.println("building_index: " + build_index);
				} else {
					build_index = -1;
				}
			}
			if (road_index == -1 && build_index == -1) {
				Worldinfo.readyMap = true;
			}
		}
	}

	public void actionAgent() {

	}

	public void updateBuild() {

	}

	public void updateBlockade() {

	}

}
