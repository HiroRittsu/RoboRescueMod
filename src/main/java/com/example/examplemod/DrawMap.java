package com.example.examplemod;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class DrawMap {

	private static Point toPoint(Vec3d vec3d) {
		return new Point((int) vec3d.x, (int) vec3d.z);
	}

	private static Vec3d calcCentroid(HashSet<Vec3d> points) {

		int count = 0;
		double sum_x = 0;
		double sum_y = 0;
		double sum_z = 0;

		for (Vec3d vec3d : points) {
			sum_x += vec3d.x;
			sum_y += vec3d.y;
			sum_z += vec3d.z;
			count++;
		}

		return (count != 0 ? new Vec3d(sum_x / count, sum_y / count, sum_z / count) : null);
	}

	private static Point[] serchArea(Point point) {

		Point[] targets = new Point[4];

		targets[0] = new Point(point.x, point.y + 1);
		targets[1] = new Point(point.x + 1, point.y);
		targets[2] = new Point(point.x, point.y - 1);
		targets[3] = new Point(point.x - 1, point.y);

		return targets;
	}

	private static HashSet<Point> completionArea(HashSet<Vec3d> edges) {

		// ダイクストラ/////

		ArrayList<Point> open = new ArrayList<>();
		HashSet<Point> closed = new HashSet<>();

		// 重心計算
		Vec3d edges_centroid = calcCentroid(edges);

		// 開始地点を格納
		open.add(toPoint(edges_centroid));

		// 探索済み座標格納
		for (Vec3d vec3d : edges)
			closed.add(toPoint(vec3d));

		// 深さ優先探索
		while (!open.isEmpty()) {

			closed.add(open.get(0)); // set

			Point[] targets = serchArea(open.remove(0)); // pop

			for (Point target : targets) {
				if (!closed.contains(target))
					open.add(target);
			}
		}

		return closed;
	}

	private static HashSet<Vec3d> completionLine(Vec3d start, Vec3d end) {
		int nextX = (int) start.x;
		int nextY = (int) start.z;
		int deltaX = (int) (end.x - start.x);
		int deltaY = (int) (end.z - start.z);
		int stepX, stepY;
		int fraction;

		HashSet<Vec3d> result = new HashSet<>();

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

		HashSet<Vec3d> edges = new HashSet<>();
		HashSet<Point> area = new HashSet<>();

		if (index < buildings.size()) {

			Building building = buildings.get(index);

			for (Integer[] ids : building.getEdgeIds()) { // node points

				edges = completionLine(nodes.get(ids[0]), nodes.get(ids[1])); // completion
				area = completionArea(edges);

				for (int i = 0; i <= building.getFloor() * 4; i++) {
					for (Point point : area) { // draw

						BlockPos pos = new BlockPos(point.x, 3 + i, -1 * point.y);
						world.setBlockState(pos, Blocks.PLANKS.getDefaultState());
					}
				}
			}
		}
	}

	public static void drawRoad(int index, ArrayList<Road> roads, Map<Integer, Vec3d> nodes, World world)
			throws NullPointerException {

		HashSet<Vec3d> edges = new HashSet<>();
		HashSet<Point> area = new HashSet<>();

		if (index < roads.size()) {

			Road road = roads.get(index);

			System.out.println(road.getId());

			for (Integer[] ids : road.getEdgeIds()) { // node points
				System.out.println(nodes.get(ids[0]) + " " + nodes.get(ids[1]));
				edges = completionLine(nodes.get(ids[0]), nodes.get(ids[1])); // completion
				area = completionArea(edges);

				for (Point point : area) { // draw
					BlockPos pos = new BlockPos(point.x, 3, -1 * point.y);
					world.setBlockState(pos, Blocks.DOUBLE_STONE_SLAB.getDefaultState());
				}
			}
		}
	}

}
