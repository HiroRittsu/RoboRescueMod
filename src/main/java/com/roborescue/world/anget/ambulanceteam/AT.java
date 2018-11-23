package com.roborescue.world.anget.ambulanceteam;

import com.roborescue.world.anget.StandardAgent;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class AT extends StandardAgent {
	
	public int hp;

	public AT(World world, Entity entity, int entityID, int positionID) {
		super(world, entity, entityID, positionID);
	}

}
