package com.example.examplemod;

import java.util.ArrayList;
import java.util.Map;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class DrawMap {

	private static ArrayList<Vec3d> completionLine(Vec3d start, Vec3d end) {
		int nextX = (int) start.x;
		int nextY = (int) start.z;
		int deltaX = (int) (end.x - start.x);
		int deltaY = (int) (end.z - start.z);
		int stepX, stepY;
		int fraction;

		ArrayList<Vec3d> result = new ArrayList<>();

		if (deltaX < 0)
			stepX = -1;
		else
			stepX = 1;
		if (deltaY < 0)
			stepY = -1;
		else
			stepY = 1;

		deltaX = Math.abs(deltaX * 2);
		deltaY = Math.abs(deltaY * 2);

		result.add(new Vec3d(nextX, 0, nextY));

		if (deltaX > deltaY) {
			fraction = deltaY - deltaX / 2;
			while (nextX != end.x) {
				if (fraction >= 0) {
					nextY += stepY;
					fraction -= deltaX;
				}
				nextX += stepX;
				fraction += deltaY;
				result.add(new Vec3d(nextX, 0, nextY));
			}
		} else {
			fraction = deltaX - deltaY / 2;
			while (nextY != end.z) {
				if (fraction >= 0) {
					nextX += stepX;
					fraction -= deltaY;
				}
				nextY += stepY;
				fraction += deltaX;
				result.add(new Vec3d(nextX, 0, nextY));
			}
		}

		return result;
	}

	public static void drawBuildings(int index, ArrayList<Building> buildings, Map<Integer, Vec3d> nodes, World world)
			throws NullPointerException {

		ArrayList<Vec3d> vec3ds = new ArrayList<>();

		if (index < buildings.size()) {

			Building building = buildings.get(index);

			for (Integer[] ids : building.getEdgeIds()) { // node points

				vec3ds = completionLine(nodes.get(ids[0]), nodes.get(ids[1])); // completion

				for (int i = 0; i <= building.getFloor(); i++) {
					for (Vec3d vec3d : vec3ds) { // draw

						BlockPos pos = new BlockPos(vec3d.x, vec3d.y + 4 + (4 * i), -1 * vec3d.z);
						world.setBlockState(pos, Blocks.PLANKS.getDefaultState());
					}
				}
			}
		}
	}

	public static void drawRoad(int index, ArrayList<Road> roads, Map<Integer, Vec3d> nodes, World world)
			throws NullPointerException {

		ArrayList<Vec3d> vec3ds = new ArrayList<>();

		if (index < roads.size()) {

			Road road = roads.get(index);

			System.out.println(road.getId());

			for (Integer[] ids : road.getEdgeIds()) { // node points
				System.out.println(nodes.get(ids[0]) + " " + nodes.get(ids[1]));
				vec3ds = completionLine(nodes.get(ids[0]), nodes.get(ids[1])); // completion

				for (Vec3d vec3d : vec3ds) { // draw
					BlockPos pos = new BlockPos(vec3d.x, 4, -1 * vec3d.z);
					world.setBlockState(pos, Blocks.DOUBLE_STONE_SLAB.getDefaultState());
				}
			}
		}
	}

}
