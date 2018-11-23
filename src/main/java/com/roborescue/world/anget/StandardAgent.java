package com.roborescue.world.anget;

import com.roborescue.commons.Point3D;
import com.roborescue.information.Worldinfo;
import com.roborescue.world.map.MinecraftMap;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class StandardAgent {
	public boolean spawned = false;
	public Entity entity = null;
	public World world = null;
	public int entityID;
	public int positionID;
	public MinecraftMap minecraftMap;

	public StandardAgent(World world, Entity entity, int entityID, int positionID) {
		this.entity = entity;
		this.entityID = entityID;
		this.world = world;
		this.positionID = positionID;
		this.minecraftMap = Worldinfo.minecraftMap;

		// Spawn
		Point3D position = minecraftMap.getPosition(positionID);
		if (position != null) {
			entity.setPosition(position.x, position.y, position.z);
			world.spawnEntity(entity);
			spawned = true;
		}

	}
}
