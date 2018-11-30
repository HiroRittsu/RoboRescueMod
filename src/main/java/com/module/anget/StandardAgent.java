package com.module.anget;

import java.util.ArrayList;

import com.module.commons.Point3D;

import net.minecraft.entity.Entity;

public class StandardAgent {
	public boolean spawned = false;
	public Entity entity;
	public int hp;
	public int entityID;
	public int spawn_locationID;
	public ArrayList<Integer> history_position;

	public StandardAgent(int entityID, int spawn_locationID) {
		this.entityID = entityID;
		this.spawn_locationID = spawn_locationID;
		this.history_position = new ArrayList<>();
	}

	public Point3D getPosition() {
		if (spawned) {
			return new Point3D((int) entity.getPositionVector().x, (int) entity.getPositionVector().y,
					(int) entity.getPositionVector().z);
		}
		return null;
	}

	public Entity getEntity() {
		return entity;
	}

	public ArrayList<Integer> getHistory_position() {
		return history_position;
	}

	public int popHistory() {
		int temp = history_position.get(0);
		history_position.remove(0);
		return temp;
	}

	public boolean isHistory() {
		return history_position.isEmpty();
	}
}
