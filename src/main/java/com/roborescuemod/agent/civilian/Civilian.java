package com.roborescuemod.agent.civilian;

import com.roborescuemod.commons.AgentData;
import com.roborescuemod.commons.Point3Df;
import com.roborescuemod.kernel.RoboRescueMod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.world.World;

public class Civilian extends AgentData {

	public Civilian(World world, int entityID, Point3Df point) {
		super(world, new EntityVillager(world), entityID, point);
	}

}