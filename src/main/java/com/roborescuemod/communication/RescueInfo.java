package com.roborescuemod.communication;

import java.util.HashMap;

import com.roborescuemod.agent.ambulanceteam.AmbulanceTeam;
import com.roborescuemod.agent.civilian.Civilian;
import com.roborescuemod.agent.firebrigade.FireBrigade;
import com.roborescuemod.agent.policeforce.PoliceForce;
import com.roborescuemod.commons.AgentData;
import com.roborescuemod.kernel.Cycles;

public class RescueInfo {

	public static int time = 0;

	public static HashMap<Integer, AgentData> agent_list = new HashMap<>();

	public static void registerData(String input) {

		time = SocketMegReader.getTime(input); // 時間格納

		if (SocketMegReader.getHistorySize(input) > 0) { // 履歴が存在する場合

			int id = SocketMegReader.getID(input);

			if (!agent_list.containsKey(id)) { // 新規登録

				switch (SocketMegReader.getURN(input)) {
				case "civilian":
					agent_list.put(id, new Civilian(Cycles.world, SocketMegReader.getID(input),
							SocketMegReader.getPosition(input)));
					break;

				case "firebrigade":
					agent_list.put(id, new FireBrigade(Cycles.world, SocketMegReader.getID(input),
							SocketMegReader.getPosition(input)));
					break;

				case "policeforce":
					agent_list.put(id, new PoliceForce(Cycles.world, SocketMegReader.getID(input),
							SocketMegReader.getPosition(input)));
					break;

				case "ambulanceteam":
					agent_list.put(id, new AmbulanceTeam(Cycles.world, SocketMegReader.getID(input),
							SocketMegReader.getPosition(input)));
					break;

				default:
					System.out.println("Error Agent");
					break;
				}

			}

			agent_list.get(id).setPath(SocketMegReader.getHistory(input)); // 経路格納

		}

	}

}
