package com.roborescuemod.agent;

import java.util.ArrayList;
import java.util.HashMap;

import com.roborescuemod.commons.AgentData;
import com.roborescuemod.communication.SocketClient;

public class AgentControl {

	HashMap<Integer, AgentData> agent_list = null;

	public AgentControl(HashMap<Integer, AgentData> agent_list) {
		this.agent_list = agent_list;
	}

	public void update() {

		if (SocketClient.agent_data.size() != 0) {
			for (String data : SocketClient.agent_data) {
				switch (data.split(",")[0]) {
				case "civilian":
					agent_list.get(data.split(",")[1]).path.addAll(c);
					break;

				default:
					break;
				}
			}
		}

	}

}
