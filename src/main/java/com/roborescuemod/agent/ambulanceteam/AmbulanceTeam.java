package com.roborescuemod.agent.ambulanceteam;

import com.roborescuemod.commons.AgentData;
import com.roborescuemod.commons.Point3D;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class AmbulanceTeam extends AgentData {

	public int entityID;

	public AmbulanceTeam(World world, Entity agent, int entityID, Point3D point) {
		super(world, agent, point);
		this.entityID = entityID;
	}

}
