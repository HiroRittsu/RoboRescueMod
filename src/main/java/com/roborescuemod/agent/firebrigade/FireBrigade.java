package com.roborescuemod.agent.firebrigade;

import com.roborescuemod.commons.AgentData;
import com.roborescuemod.commons.Point3Df;
import com.roborescuemod.kernel.Cycles;

import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.world.World;

public class FireBrigade extends AgentData {

	public FireBrigade(World world, int entityID, Point3Df point3Df) {
		super(world, new EntityVillager(Cycles.world), entityID, point3Df);
	}

}
