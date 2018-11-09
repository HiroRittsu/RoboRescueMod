package com.roborescuemod.agent.civilian;

import com.roborescuemod.commons.AgentData;
import com.roborescuemod.commons.Point3D;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class Civilian extends AgentData {

	public int entityID;

	public Civilian(World world, Entity agent, int entityID, Point3D point) {
		super(world, agent, point);
		this.entityID = entityID;
	}

}
