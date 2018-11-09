package com.roborescuemod.commons;

import java.util.ArrayList;

import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.world.World;

public class AgentData {

	public Entity agent = null;
	public World world = null;
	public Point3Df last_point = null;
	public ArrayList<Double> path = new ArrayList<>();

	public AgentData(World world, Entity agent, Point3Df point) {
		this.agent = agent;
		agent.setPosition(point.x, point.y, point.z);
		world.spawnEntity(agent);
		last_point = new Point3Df(point.x, point.y, point.z);
	}

	private void move(double x, double z) {
		agent.move(MoverType.SELF, x - last_point.x, 0, z - last_point.z);
		last_point.x = x;
		last_point.y = 0;
		last_point.z = z;
	}

	private double pop() {
		if (path.size() != 0) {
			double p = path.get(0);
			path.remove(0);
			return p;
		}
		return 0;
	}

	public void setPath(ArrayList<Double> history) {
		path.addAll(history);
	}

	public void shift() {
		double x = pop();
		double z = pop();
		this.move(x, z);
	}

}
