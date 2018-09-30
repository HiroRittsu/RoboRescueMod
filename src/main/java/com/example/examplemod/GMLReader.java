package com.example.examplemod;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class GMLReader {

	public static int MULTI_STEP = 1;

	private ArrayList<Point> completionLine(Point start, Point end) {
		int nextX = (int) start.x;
		int nextY = (int) start.y;
		int deltaX = (int) (end.x - start.x);
		int deltaY = (int) (end.y - start.y);
		int stepX, stepY;
		int step;
		int fraction;

		ArrayList<Point> result = new ArrayList<>();

		step = 0;

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

		result.add(new Point(nextX, nextY));
		step++;

		if (deltaX > deltaY) {
			fraction = deltaY - deltaX / 2;
			while (nextX != end.x) {
				if (fraction >= 0) {
					nextY += stepY;
					fraction -= deltaX;
				}
				nextX += stepX;
				fraction += deltaY;
				result.add(new Point(nextX, nextY));
				step++;
			}
		} else {
			fraction = deltaX - deltaY / 2;
			while (nextY != end.y) {
				if (fraction >= 0) {
					nextX += stepX;
					fraction -= deltaY;
				}
				nextY += stepY;
				fraction += deltaX;
				result.add(new Point(nextX, nextY));
				step++;
			}
		}

		return result;
	}

	private static int readID(Element node) {

		String s = node.attributeValue("id");

		try {
			return Integer.parseInt(s);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static Document openGML(String PATH) {

		SAXReader reader = new SAXReader();
		Document doc = null;

		try {
			doc = reader.read(PATH);
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		return doc;
	}

	public Building readBuildingData(Element building, Map<Integer, ArrayList<Point>> edges) {

		ArrayList<Point> edge_points = new ArrayList<>();
		int id = readID(building);
		int floors = 0;
		int buildingcode = 0;
		int importance = 0;

		for (Object next3 : building.elements("Face")) {
			Element face = (Element) next3;

			floors = Integer.parseInt(face.attributeValue("floors"));
			buildingcode = Integer.parseInt(face.attributeValue("buildingcode"));
			importance = Integer.parseInt(face.attributeValue("importance"));

			for (Object next4 : face.elements("directedEdge")) {
				Element directededge = (Element) next4;

				// get Position
				for (Point point : edges
						.get(Integer.parseInt(directededge.attributeValue("href").replaceAll("#", "")))) {
					edge_points.add(new Point(point.x, point.y));

				}
			}
		}
		return new Building(id, floors, "Wood", edge_points);
	}

	public ArrayList<Building> readBuildings(Document doc, Map<Integer, ArrayList<Point>> edges) {

		ArrayList<Building> result = new ArrayList<>();

		for (Object next : doc.getRootElement().elements("buildinglist")) {
			Element roadList = (Element) next;
			for (Object next2 : roadList.elements("building")) {
				Element building = (Element) next2;
				result.add(readBuildingData(building, edges));
			}
		}
		return result;

	}

	public Road readRoadData(Element road, Map<Integer, ArrayList<Point>> edges) {

		int id = readID(road);
		ArrayList<Point> edge_points = new ArrayList<>();

		for (Object next3 : road.elements("Face")) {
			Element face = (Element) next3;

			for (Object next4 : face.elements("directedEdge")) {
				Element directededge = (Element) next4;

				// get Position
				for (Point point : edges
						.get(Integer.parseInt(directededge.attributeValue("href").replaceAll("#", "")))) {
					edge_points.add(new Point(point.x, point.y));

				}
			}
		}

		return new Road(id, edge_points);
	}

	public ArrayList<Road> readRoads(Document doc, Map<Integer, ArrayList<Point>> edges) {

		ArrayList<Road> result = new ArrayList<>();

		for (Object next : doc.getRootElement().elements("roadlist")) {
			Element roadList = (Element) next;
			for (Object next2 : roadList.elements("road")) {
				Element road = (Element) next2;

				result.add(readRoadData(road, edges));
			}
		}
		return result;
	}

	public Map<Integer, ArrayList<Point>> readEdge(Document doc, Map<Integer, Point> nodes) {

		Map<Integer, ArrayList<Point>> result = new HashMap<>();
		String startID = null;
		String endID = null;
		int id;

		for (Object next : doc.getRootElement().elements("edgelist")) {
			Element edgeList = (Element) next;
			for (Object next2 : edgeList.elements("Edge")) {
				Element edge = (Element) next2;
				id = readID(edge);
				for (Object next3 : edge.elements("directedNode")) {

					Element directedNodeElement = (Element) next3;
					if (directedNodeElement.attributeValue("orientation").equals("-")) {
						startID = directedNodeElement.attributeValue("href").replaceAll("#", "");
					}
					if (directedNodeElement.attributeValue("orientation").equals("+")) {
						endID = directedNodeElement.attributeValue("href").replaceAll("#", "");
					}
				}

				// ライン補完
				result.put(id,
						completionLine(nodes.get(Integer.parseInt(startID)), nodes.get(Integer.parseInt(endID))));

				startID = null;
				endID = null;

			}
		}

		return result;
	}

	public Map<Integer, Point> readNode(Document doc) {

		Map<Integer, Point> result = new HashMap<>();
		String value;
		int x;
		int y;

		for (Object next : doc.getRootElement().elements("nodelist")) {
			Element nodeList = (Element) next;
			for (Object next2 : nodeList.elements("Node")) {
				Element node = (Element) next2;
				// 座標
				value = node.getStringValue().replaceAll("\n", "").replaceAll(" ", "");
				x = (int) Double.parseDouble(value.split(",", 0)[0]);
				y = (int) Double.parseDouble(value.split(",", 0)[1]);

				result.put(readID(node), new Point(x, y));
			}
		}

		return result;
	}

}
