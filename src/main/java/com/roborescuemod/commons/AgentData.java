package com.roborescuemod.commons;

import java.util.ArrayList;

import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.world.World;
import scala.reflect.api.Trees.ThisApi;

public class AgentData {

	public Entity agent = null;
	public World world = null;
	public int entityID;
	public Point3Df position = null;
	public ArrayList<Double> path = new ArrayList<>();

	public AgentData(World world, Entity agent, int entityID, Point3Df point) {
		this.agent = agent;
		this.entityID = entityID;
		this.world = world;
		position = new Point3Df(point.x, point.y, point.z);
	}

	public void setPath(ArrayList<Double> history) {
		path.addAll(history);
	}
}
