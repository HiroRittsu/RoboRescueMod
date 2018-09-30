package com.example.examplemod;

import java.awt.Point;
import java.util.ArrayList;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DrawMap {

	public static void drawBuildings(int index, ArrayList<Building> buildings, World world) {

		if (buildings == null) {
			System.out.println("building is null");
			return;
		}

		if (world == null) {
			System.out.println("world is null");
			return;
		}

		if (index < buildings.size()) {

			Building building = buildings.get(index);

			for (int i = 0; i <= building.getFloor(); i++) {
				for (Point point : building.getEdge_point()) {
					BlockPos pos = new BlockPos(point.x, 4 + i * 4, -1 * point.y);
					world.setBlockState(pos, Blocks.PLANKS.getDefaultState());
				}
			}
		}
	}

	public static void drawRoad(int index, ArrayList<Road> roads, World world) {

		if (roads == null) {
			System.out.println("building is null");
			return;
		}

		if (world == null) {
			System.out.println("world is null");
			return;
		}

		if (index < roads.size()) {

			Road road = roads.get(index);

			for (Point point : road.getEdge_point()) {
				BlockPos pos = new BlockPos(point.x, 4, -1 * point.y);
				world.setBlockState(pos, Blocks.DOUBLE_STONE_SLAB.getDefaultState());
			}

		}

	}

}
