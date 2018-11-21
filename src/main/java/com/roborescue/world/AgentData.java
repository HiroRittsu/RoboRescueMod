package com.roborescue.world;

import java.util.ArrayList;

import com.roborescue.commons.Point3Df;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class AgentData {

	public boolean spawned = false;
	public Entity entity = null;
	public World world = null;
	public int entityID;
	public Point3Df start_position = null;
	public ArrayList<Double> path = new ArrayList<>();

	private double Pop(ArrayList<Double> path) {

		if (path.isEmpty())
			return 0;

		double temp = path.get(0);
		path.remove(0);
		return temp;
	}

	public AgentData(World world, Entity entity, int entityID, Point3Df point) {
		this.entity = entity;
		this.entityID = entityID;
		this.world = world;
		start_position = new Point3Df(point.x, point.y, point.z);
	}

	public void setPath(ArrayList<Double> history) {
		path.addAll(history);
	}

	public Point3Df getNextPoint() {
		double x = Pop(this.path);
		double z = Pop(this.path);
		Point3Df result;
		if (x == 0 && z == 0) {
			result = new Point3Df(0, 0, 0);
		} else {
			result = new Point3Df(x - entity.getPositionVector().x, 0, z - entity.getPositionVector().z);
		}
		return result;
	}
}
