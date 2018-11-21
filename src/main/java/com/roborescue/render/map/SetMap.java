package com.roborescue.render.map;

import java.util.ArrayList;
import java.util.HashSet;

import com.roborescue.commons.Point3D;
import com.roborescue.world.map.MinecraftMap;
import com.roborescue.world.map.parts.Building;
import com.roborescue.world.map.parts.Road;

import net.minecraft.block.BlockPlanks;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SetMap {

	private static boolean contains(int x, int y, int z, HashSet<Point3D> closed, int[] bounding_box) {

		// [0]:min_x, [1]:min_z, [2]:max_x, [3]:max_z
		boolean p_x = true;
		boolean m_x = true;
		boolean p_z = true;
		boolean m_z = true;
		int i = 0;

		if (closed.contains(new Point3D(x, y, z)))
			return false;

		while (p_x || m_x || p_z || m_z) {

			if (x + i > bounding_box[2] && x - i < bounding_box[0] && z + i > bounding_box[3]
					&& z - i < bounding_box[1])
				return false;

			i++;

			// +x方向
			if (closed.contains(new Point3D(x + i, y, z)))
				p_x = false;

			// -x方向
			if (closed.contains(new Point3D(x - i, y, z)))
				m_x = false;

			// +z方向
			if (closed.contains(new Point3D(x, y, z + i)))
				p_z = false;

			// -z方向
			if (closed.contains(new Point3D(x, y, z - i)))
				m_z = false;

		}
		return true;
	}

	private static Point3D[] serchArea(Point3D point) {

		Point3D[] targets = new Point3D[4];

		targets[0] = new Point3D(point.x, 0, point.z + 1);
		targets[1] = new Point3D(point.x + 1, 0, point.z);
		targets[2] = new Point3D(point.x, 0, point.z - 1);
		targets[3] = new Point3D(point.x - 1, 0, point.z);

		return targets;
	}

	private static HashSet<Point3D> completionArea(ArrayList<Point3D> flame) {

		HashSet<Point3D> open = new HashSet<>();
		HashSet<Point3D> closed = new HashSet<>();
		Point3D[] temp;
		int[] bounding_box = new int[4];

		// 探索済み座標格納
		for (Point3D point3d : flame) {
			closed.add(point3d);

			if (bounding_box[0] > point3d.x)
				bounding_box[0] = point3d.x;

			if (bounding_box[1] > point3d.z)
				bounding_box[1] = point3d.z;

			if (bounding_box[2] < point3d.x)
				bounding_box[2] = point3d.x;

			if (bounding_box[3] < point3d.z)
				bounding_box[3] = point3d.z;
		}

		boolean flag = false;

		// 塗りつぶし
		for (Point3D point3d : flame) {

			if (flag)
				break;

			temp = serchArea(point3d);

			for (Point3D serch : temp) {

				if (closed.contains(serch))
					continue;

				if (contains(serch.x, 0, serch.z, closed, bounding_box)) { // エッジ内の場合

					flag = true;

					/////// 幅優先///////////////
					Point3D pop;

					// 開始地点を格納
					open.add(serch);

					while (!open.isEmpty()) {

						// System.out.println("opensize :" + open.size());
						if (open.size() > 5000) {
							break;
						}

						pop = open.iterator().next(); // pop
						open.remove(pop);

						closed.add(pop); // set

						Point3D[] targets = serchArea(pop);

						for (Point3D target : targets) {
							if (!closed.contains(target))
								open.add(target);
						}
					}
				}
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

	public void resetField(World world, MinecraftMap minecraftMap) {

		for (int i = 0; i < 5; i++) {

			for (int z = (int) (MinecraftMap.min.getZ()) - 5; z < (int) (MinecraftMap.max.getZ()) + 5; z++) {
				for (int x = (int) (MinecraftMap.min.getX()) - 5; x < (int) (MinecraftMap.max.getX()) + 5; x++) {

					BlockPos pos = new BlockPos(x, i + 3, z);

					if (i == 0) {
						world.setBlockState(pos, Blocks.GRASS.getDefaultState());
					} else {
						world.setBlockState(pos, Blocks.AIR.getDefaultState());
					}

				}
			}

		}
	}

	public boolean drawBuildings(int index, MinecraftMap minecraftMap, World world) throws NullPointerException {

		ArrayList<Point3D> edges = new ArrayList<>();
		ArrayList<Point3D> flame = new ArrayList<>();
		HashSet<Point3D> area = new HashSet<>();

		if (index < minecraftMap.getBuildins().size()) {

			Building building = minecraftMap.getBuildins().get(index);

			for (Integer[] ids : building.getEdgeIds()) { // node points すべてのエッジを書き出し

				edges = completionLine(minecraftMap.getNodes().get(ids[0]), minecraftMap.getNodes().get(ids[1])); // completion

				flame.addAll(edges);

			}

			area = completionArea(flame);

			for (int i = 0; i <= building.getFloor() * 4; i++) {

				if (i % 4 == 0) {

					for (Point3D point : area) { // draw

						BlockPos pos = new BlockPos(point.x, 3 + i, -1 * point.z);
						world.setBlockState(pos, Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT,
								BlockPlanks.EnumType.byMetadata(building.getId() % 6)));
					}

				} else {

					for (Point3D point : flame) { // draw

						BlockPos pos = new BlockPos(point.x, 3 + i, -1 * point.z);
						world.setBlockState(pos, Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT,
								BlockPlanks.EnumType.byMetadata(building.getId() % 6)));
					}
				}

			}

		} else {
			return false;
		}

		return true;
	}

	public boolean drawRoad(int index, MinecraftMap minecraftMap, World world) throws NullPointerException {

		ArrayList<Point3D> edges;
		ArrayList<Point3D> flame = new ArrayList<>();
		HashSet<Point3D> area;

		if (index < minecraftMap.getRoads().size()) {

			Road road = minecraftMap.getRoads().get(index);

			for (Integer[] ids : road.getEdgeIds()) { // node points

				edges = completionLine(minecraftMap.getNodes().get(ids[0]), minecraftMap.getNodes().get(ids[1])); // completion

				flame.addAll(edges);

			}

			area = completionArea(flame);

			for (Point3D point : area) { // draw
				BlockPos pos = new BlockPos(point.x, 3, -1 * point.z);
				world.setBlockState(pos, Blocks.DOUBLE_STONE_SLAB.getDefaultState());
			}
		} else {
			return false;
		}
		
		return true;
	}

}