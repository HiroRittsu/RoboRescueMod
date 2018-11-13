package com.roborescuemod.world.agent;

import java.util.HashMap;
import java.util.Map.Entry;

import com.roborescuemod.commons.AgentData;
import com.roborescuemod.commons.Point3Df;
import com.roborescuemod.communication.RescueInfo;
import com.roborescuemod.kernel.RoboRescueMod;

import net.minecraft.entity.MoverType;

public class ActionAgent {

	public static void update() {
		@SuppressWarnings("unchecked")
		HashMap<Integer, AgentData> agent_list = (HashMap<Integer, AgentData>) RescueInfo.agent_list.clone();
		for (Entry<Integer, AgentData> entry : agent_list.entrySet()) {
			AgentData agentData = entry.getValue();
			if (!agentData.spawned) {

				if (agentData.start_position != null) {
					System.out.println(agentData.start_position.x + " " + 4 + " " + agentData.start_position.z);
					agentData.entity.setPosition(agentData.start_position.x, 4, agentData.start_position.z);
					RoboRescueMod.world.spawnEntity(agentData.entity);
					agentData.spawned = true;
				}

			} else {
				Point3Df point3Df = agentData.getNextPoint();
				System.out.println(point3Df);
				agentData.entity.move(MoverType.SELF, point3Df.x, point3Df.y, point3Df.z);
				// agentData.entity.move(MoverType.SELF, 0, 0, 0);
			}

		}
	}

}
