package com.example.examplemod;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DrawMap {

	private static Point3D calcStartPoint() {
		return null;
	}

	private static Point3D calcCentroid(ArrayList<Point3D> points) {

		int count = 0;
		int sum_x = 0;
		int sum_y = 0;
		int sum_z = 0;

		for (Point3D point3d : points) {
			sum_x += point3d.x;
			sum_y += point3d.y;
			sum_z += point3d.z;
			count++;
		}

		return (count != 0 ? new Point3D(sum_x / count, sum_y / count, sum_z / count) : null);
	}

	private static Point3D[] serchArea(Point3D point3d) {

		Point3D[] targets = new Point3D[4];

		targets[0] = new Point3D(point3d.x, 0, point3d.z + 1);
		targets[1] = new Point3D(point3d.x + 1, 0, point3d.z);
		targets[2] = new Point3D(point3d.x, 0, point3d.z - 1);
		targets[3] = new Point3D(point3d.x - 1, 0, point3d.z);

		return targets;
	}

	private static HashSet<Point3D> completionArea(ArrayList<Point3D> flame) {

		// ダイクストラ/////

		HashSet<Point3D> open = new HashSet<>();
		HashSet<Point3D> closed = new HashSet<>();
		Polygon polygon = new Polygon();
		Area area;
		Shape shape;
		Point3D temp;

		// 重心計算
		Point3D edges_centroid = calcCentroid(flame);

		if (!edges_centroid.equals(new Point3D(-19, 0, 54))) {
			closed.add(new Point3D(0, 0, 0));
			return closed;
		}

		// 開始地点を格納
		open.add(edges_centroid);

		// 探索済み座標格納
		for (Point3D point3d : flame) {
			closed.add(point3d);
			polygon.addPoint(point3d.x, point3d.z);
		}

		polygon.addPoint(flame.get(0).x, flame.get(0).z);

		area = new Area(polygon);

		System.out.println("#########################################");

		// if (closed.contains(open.iterator().next())) {
		// System.out.println("重複");
		// return closed;
		// }

		if (area.contains(-19, 54)) {
			System.out.println("含まれる");
		} else {
			System.out.println("含まれない");
		}

		/*
		 * if (area.contains(-20, 54)) { System.out.println("含まれる"); } else {
		 * System.out.println("含まれない"); }
		 * 
		 * if (area.contains(-21, 54)) { System.out.println("含まれる"); } else {
		 * System.out.println("含まれない"); }
		 * 
		 * if (area.contains(-22, 54)) { System.out.println("含まれる"); } else {
		 * System.out.println("含まれない"); }
		 */

		if (!area.contains(new Point(edges_centroid.x, edges_centroid.z))) {
			System.out.println(edges_centroid.x + " " + edges_centroid.z);
			System.out.println("重心外部");
			return closed;
		}

		// 深さ優先探索
		while (!open.isEmpty()) {

			// System.out.println("closesize :" + closed.size());

			// System.out.println("opensize :" + open.size());
			if (open.size() > 5000) {
				System.out.println("edges_centroid" + edges_centroid);
				break;
			}

			temp = open.iterator().next(); // pop
			open.remove(temp);

			closed.add(temp); // set

			Point3D[] targets = serchArea(temp);

			for (Point3D target : targets) {
				if (!closed.contains(target))
					open.add(target);
			}
		}

		return closed;
	}

	private static ArrayList<Point3D> completionLine(Point3D start, Point3D end) {
		int nextX = (int) start.x;
		int nextZ = (int) start.z;
		int deltaX = (int) (end.x - start.x);
		int deltaZ = (int) (end.z - start.z);
		int stepX, stepZ;
		int fraction;

		ArrayList<Point3D> result = new ArrayList<>();

		if (deltaX < 0)
			stepX = -1;
		else
			stepX = 1;
		if (deltaZ < 0)
			stepZ = -1;
		else
			stepZ = 1;

		deltaX = Math.abs(deltaX * 2);
		deltaZ = Math.abs(deltaZ * 2);

		result.add(new Point3D(nextX, 0, nextZ));

		if (deltaX > deltaZ) {
			fraction = deltaZ - deltaX / 2;
			while (nextX != end.x) {
				if (fraction >= 0) {
					nextZ += stepZ;
					fraction -= deltaX;
				}
				nextX += stepX;
				fraction += deltaZ;
				result.add(new Point3D(nextX, 0, nextZ));
			}
		} else {
			fraction = deltaX - deltaZ / 2;
			while (nextZ != end.z) {
				if (fraction >= 0) {
					nextX += stepX;
					fraction -= deltaZ;
				}
				nextZ += stepZ;
				fraction += deltaX;
				result.add(new Point3D(nextX, 0, nextZ));
			}
		}

		return result;
	}

	public static void drawBuildings(int index, ArrayList<Building> buildings, Map<Integer, Point3D> nodes, World world)
			throws NullPointerException {

		ArrayList<Point3D> edges = new ArrayList<>();
		ArrayList<Point3D> flame = new ArrayList<>();
		HashSet<Point3D> area = new HashSet<>();

		if (index < buildings.size()) {

			Building building = buildings.get(index);

			for (Integer[] ids : building.getEdgeIds()) { // node points すべてのエッジを書き出し

				edges = completionLine(nodes.get(ids[0]), nodes.get(ids[1])); // completion

				flame.addAll(edges);

			}

			area = completionArea(flame);

			for (int i = 0; i <= building.getFloor(); i++) {
				for (Point3D point : area) { // draw

					BlockPos pos = new BlockPos(point.x, 3 + i * 4, -1 * point.z);
					world.setBlockState(pos, Blocks.PLANKS.getDefaultState());
				}
			}
		}
	}

	public static void drawRoad(int index, ArrayList<Road> roads, Map<Integer, Point3D> nodes, World world)
			throws NullPointerException {

		ArrayList<Point3D> edges = new ArrayList<>();
		ArrayList<Point3D> flame = new ArrayList<>();
		HashSet<Point3D> area = new HashSet<>();

		if (index < roads.size()) {

			Road road = roads.get(index);

			for (Integer[] ids : road.getEdgeIds()) { // node points

				edges = completionLine(nodes.get(ids[0]), nodes.get(ids[1])); // completion

				flame.addAll(edges);

			}

			area = completionArea(flame);

			for (Point3D point : area) { // draw
				BlockPos pos = new BlockPos(point.x, 3, -1 * point.z);
				world.setBlockState(pos, Blocks.DOUBLE_STONE_SLAB.getDefaultState());
			}
		}
	}

}