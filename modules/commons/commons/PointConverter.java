package commons;

import java.util.HashMap;
import java.util.Map;

import map.GMLMap;
import map.RescueMap;

public class PointConverter {

	public static Point3Df[] calcPrimaryPoint(Map<Integer, Point3D> nodes) {

		// [0]: max, [1]:min, [2]:centroid
		Point3Df[] result = new Point3Df[3];

		for (int i = 0; i < result.length; i++)
			result[i] = new Point3Df(0, 0, 0);

		for (Point3D p : nodes.values()) {

			if (result[1].getX() > p.getX())
				result[1].x = p.getX();

			if (result[1].getY() > p.getY())
				result[1].y = p.getY();

			if (result[1].getZ() > p.getZ())
				result[1].z = p.getZ();

			if (result[0].getX() < p.getX())
				result[0].x = p.getX();

			if (result[0].getY() < p.getY())
				result[0].y = p.getY();

			if (result[0].getZ() < p.getZ())
				result[0].z = p.getZ();

		}

		result[2].x = (result[0].getX() + result[1].getX()) / 2.0;
		result[2].y = (result[0].getY() + result[1].getY()) / 2.0;
		result[2].z = (result[0].getZ() + result[1].getZ()) / 2.0;

		return result;

	}

	public static Map<Integer, Point3D> convertPoint(Map<Integer, Point3D> nodes, Point3Df centroid, Point3D origin) {

		Map<Integer, Point3D> result = new HashMap<>();
		Point3Df distance = new Point3Df(centroid.getX() - origin.getX(), centroid.getY() - origin.getY(),
				centroid.getZ() - origin.getZ());

		for (Map.Entry<Integer, Point3D> entry : nodes.entrySet()) {

			result.put(entry.getKey(), new Point3D(entry.getValue().getX() - (int) distance.getX(),
					(int) entry.getValue().getY(), entry.getValue().getZ() - (int) distance.getZ()));

		}

		return result;

	}

	public static Map<Integer, Point3D> convertMinecraftMap(GMLMap gmlMap) {

		Point3Df origin = new Point3Df(0, 0, 0);

		Map<Integer, Point3D> result = new HashMap<>();
		Point3Df distance = new Point3Df(origin.getX() - gmlMap.getCentroid().getX(),
				origin.getY() - gmlMap.getCentroid().getY(), origin.getZ() - gmlMap.getCentroid().getZ());

		for (Map.Entry<Integer, Point3D> entry : gmlMap.getNodes().entrySet()) {

			result.put(entry.getKey(), new Point3D(entry.getValue().getX() + (int) distance.getX(),
					entry.getValue().getY(), entry.getValue().getZ() + (int) distance.getZ()));

		}

		return result;
	}

	public static Map<Integer, Point3D> convertRescueMap(GMLMap gmlMap) {

		Map<Integer, Point3D> result = new HashMap<>();
		Point3Df distance = new Point3Df(-1 * gmlMap.getMinPoint().getX(), -1 * gmlMap.getMinPoint().getY(),
				-1 * gmlMap.getMinPoint().getZ());

		for (Map.Entry<Integer, Point3D> entry : gmlMap.getNodes().entrySet()) {

			result.put(entry.getKey(), new Point3D((int) ((entry.getValue().getX() + distance.getX()) * 1000),
					(int) entry.getValue().getY(), (int) ((entry.getValue().getZ() + distance.getZ()) * 1000)));

		}

		return result;
	}

	public static Point3Df toMinecraftPoint(Point3Df rescue_point) {

		return new Point3Df((double) (rescue_point.x - RescueMap.centroid.x) / 1000, (double) rescue_point.y / 1000,
				(double) (rescue_point.z - RescueMap.centroid.z) / -1000);
	}

}
