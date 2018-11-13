package com.roborescuemod.world.agent.ambulanceteam;

import com.roborescuemod.commons.AgentData;
import com.roborescuemod.commons.Point3Df;
import com.roborescuemod.kernel.Cycles;

import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.world.World;

public class AmbulanceTeam extends AgentData {

	public AmbulanceTeam(World world, int entityID, Point3Df point3Df) {
		super(world, new EntityVillager(Cycles.world), entityID, point3Df);
	}

}
