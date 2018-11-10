package com.roborescuemod.agent.civilian;

import com.roborescuemod.commons.AgentData;
import com.roborescuemod.commons.Point3Df;

import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.world.World;

public class Civilian extends AgentData {

	public Civilian(World world, int entityID, Point3Df point) {
		super(world, new EntityCreeper(world), entityID, point);
	}

}
