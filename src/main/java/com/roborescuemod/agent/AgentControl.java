package com.roborescuemod.agent;

import java.util.HashMap;
import java.util.Map;

import com.roborescuemod.commons.AgentData;
import com.roborescuemod.communication.SocketClient;

public class AgentControl {

	private HashMap<Integer, AgentData> agent_list = null;
	public static int time = 0;

	public AgentControl(HashMap<Integer, AgentData> agent_list) {
		this.agent_list = agent_list;
	}

	public void update() {

		if (SocketClient.agent_datas.size() != 0) {
			for (String data : SocketClient.agent_datas) {

				time = AgentReader.getTime(data);

				switch (AgentReader.getURN(data)) {
				case "civilian":
					agent_list.get(AgentReader.getID(data)).path.addAll(AgentReader.getHistory(data));
					break;

				case "firebrigade":
					agent_list.get(AgentReader.getID(data)).path.addAll(AgentReader.getHistory(data));
					break;

				case "policeforce":
					agent_list.get(AgentReader.getID(data)).path.addAll(AgentReader.getHistory(data));
					break;

				case "ambulanceteam":
					agent_list.get(AgentReader.getID(data)).path.addAll(AgentReader.getHistory(data));
					break;

				default:
					System.out.println("Error Agent");
					break;
				}
			}
		}

		for (Map.Entry<Integer, AgentData> entry : agent_list.entrySet()) {
			entry.getValue().shift();
		}

	}

}
