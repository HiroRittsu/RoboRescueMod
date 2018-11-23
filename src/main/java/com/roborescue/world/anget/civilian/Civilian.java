package com.roborescue.world.anget.civilian;

import com.roborescue.world.anget.StandardAgent;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class Civilian extends StandardAgent {

	public int hp;

	public Civilian(World world, Entity entity, int entityID, int positionID) {
		super(world, entity, entityID, positionID);
	}

}
