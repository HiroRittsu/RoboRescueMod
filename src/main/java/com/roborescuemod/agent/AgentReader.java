package com.roborescuemod.agent;

import java.util.ArrayList;
import java.util.HashMap;

import com.roborescuemod.agent.ambulanceteam.AmbulanceTeam;
import com.roborescuemod.agent.civilian.Civilian;
import com.roborescuemod.agent.firebrigade.FireBrigade;
import com.roborescuemod.agent.policeforce.PoliceForce;
import com.roborescuemod.commons.AgentData;
import com.roborescuemod.commons.Point3Df;
import com.roborescuemod.commons.PointConverter;
import com.roborescuemod.kernel.Cycles;

public class AgentReader {

	public static HashMap<Integer, AgentData> getDefaultInfo(String agent_data) {
		HashMap<Integer, AgentData> result = new HashMap<>();
		AgentData agent = null;
		ArrayList<Double> history = new ArrayList<>();

		history = getHistory(agent_data);

		switch (AgentReader.getURN(agent_data)) {
		case "civilian":
			agent = new Civilian(Cycles.world, getID(agent_data), new Point3Df(history.get(0), 0, history.get(1)));
			break;

		case "firebrigade":
			agent = new FireBrigade(Cycles.world, getID(agent_data), new Point3Df(history.get(0), 0, history.get(1)));
			break;

		case "policeforce":
			agent = new PoliceForce(Cycles.world, getID(agent_data), new Point3Df(history.get(0), 0, history.get(1)));
			break;

		case "ambulanceteam":
			agent = new AmbulanceTeam(Cycles.world, getID(agent_data), new Point3Df(history.get(0), 0, history.get(1)));
			break;

		default:
			System.out.println("Error AgentReader");
			break;
		}

		result.put(getID(agent_data), agent);

		return result;
	}

	public static int getTime(String agent_data) {
		return Integer.parseInt(agent_data.split(",")[0]);
	}

	public static String getURN(String agent_data) {
		return agent_data.split(",")[1];
	}

	public static int getID(String agent_data) {
		return Integer.parseInt(agent_data.split(",")[2]);
	}

	public static ArrayList<Double> getHistory(String agent_data) {

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
