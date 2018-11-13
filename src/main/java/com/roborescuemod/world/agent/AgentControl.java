package com.roborescuemod.world.agent;

import java.util.HashMap;
import java.util.Map;

import com.roborescuemod.commons.AgentData;
import com.roborescuemod.communication.SocketClient;
import com.roborescuemod.communication.SocketMegReader;

public class AgentControl {

	private HashMap<Integer, AgentData> agent_list = null;
	private int time = 0;

	public void registerAgent() {
		this.agent_list = SocketMegReader.getDefaultInfo(SocketClient.agent_datas);
	}

	public int getTime() {
		return time;
	}

	public void update() {

	

	}

}
