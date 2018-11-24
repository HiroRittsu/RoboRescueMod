package com.module.anget;

import com.module.commons.Point3D;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;

public class StandardAgent {
	public boolean spawned = false;
	public Entity entity;
	public int entityID;
	public int spawn_locationID;

	public StandardAgent(int entityID) {
		this.entityID = entityID;
	}

	public StandardAgent(int entityID, int spaen_locationID) {
		this.entityID = entityID;
		this.spawn_locationID = spaen_locationID;
	}

	public Point3D getPosition() {
		if (spawned) {
			BlockPos blockPos = entity.getPosition();
			return new Point3D(blockPos.getX(), blockPos.getY(), blockPos.getZ());
		}
		return null;
	}
}
