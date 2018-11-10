package com.roborescuemod.communication;

import java.util.ArrayList;
import com.roborescuemod.commons.Point3Df;
import com.roborescuemod.commons.PointConverter;

public class SocketMegReader {

	public static int getTime(String agent_data) {
		return Integer.parseInt(agent_data.split(",")[0]);
	}

	public static String getURN(String agent_data) {
		return agent_data.split(",")[1];
	}

	public static int getID(String agent_data) {
		return Integer.parseInt(agent_data.split(",")[2]);
	}

	public static int getHistorySize(String agent_data) {
		return agent_data.split(",").length - 3;
	}

	public static Point3Df getPosition(String agent_data) {

		if (getHistorySize(agent_data) <= 0)
			return null;

		Point3Df point3Df = PointConverter.toMinecraftPoint(new Point3Df(Integer.parseInt(agent_data.split(",")[3]), 0,
				Integer.parseInt(agent_data.split(",")[4])));

		return point3Df;
	}

	public static ArrayList<Double> getHistory(String agent_data) {

		if (getHistorySize(agent_data) <= 0)
			return null;

		ArrayList<Double> history = new ArrayList<>();
		Point3Df result = null;

		for (int i = 3; i < agent_data.split(",").length; i += 2) {

			result = PointConverter.toMinecraftPoint(new Point3Df(Double.parseDouble(agent_data.split(",")[i]), 0,
					Double.parseDouble(agent_data.split(",")[i + 1])));

			history.add(result.x);
			history.add(result.z);
		}

		return history;
	}

}
