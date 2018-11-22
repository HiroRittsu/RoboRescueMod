package com.roborescue.render;

import com.roborescue.information.Worldinfo;
import com.roborescue.render.map.SetMap;

import net.minecraft.world.World;

public class Render {

	public World world;
	public SetMap setMap;
	private int build_index = 0;
	private int road_index = 0;
	public boolean readyMap = false;

	public Render(World world) {
		this.world = world;
		this.setMap = new SetMap();
	}

	public void renderMap() {
		if (Worldinfo.canMinecraftMap()) {
			// Road
			if (road_index != -1) {
				if (setMap.drawRoad(road_index, Worldinfo.minecraftMap, world)) {
					road_index++;
				} else {
					road_index = -1;
				}
			}
			// Building
			if (build_index != -1) {
				if (setMap.drawBuildings(build_index, Worldinfo.minecraftMap, world)) {
					build_index++;
					System.out.println("building_index: " + build_index);
				} else {
					build_index = -1;
				}
			}
			if (road_index == -1 && build_index == -1) {
				readyMap = true;
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
