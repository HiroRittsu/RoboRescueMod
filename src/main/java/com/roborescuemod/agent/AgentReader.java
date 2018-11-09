package com.roborescuemod.agent;

import java.util.ArrayList;

public class AgentReader {

	public ArrayList<Double> getHistory(String agent_data) {

		ArrayList<Double> history = new ArrayList<>();

		for (int i = 2; i < agent_data.split(",").length; i++) {

			history.add(agent_data.split(",")[i]);
		}

		return null;
	}

}
