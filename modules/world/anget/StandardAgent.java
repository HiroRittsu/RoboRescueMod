package anget;

import commons.Point3D;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;

public class StandardAgent {
	public boolean spawned = false;
	public Entity entity;
	public int entityID;

	public StandardAgent(int entityID) {
		this.entityID = entityID;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	public Entity getEntity() {
		return entity;
	}

	/*
	 * public void Spawn(int positionID, World world) { // Spawn Point3D position =
	 * minecraftMap.getPosition(positionID); if (position != null) {
	 * entity.setPosition(position.x, position.y, position.z);
	 * world.spawnEntity(entity); spawned = true; System.out.println("スポーン成功"); }
	 * else { System.out.println("スポーン失敗"); } }
	 */
	public Point3D getPosition() {
		if (spawned) {
			BlockPos blockPos = entity.getPosition();
			return new Point3D(blockPos.getX(), blockPos.getY(), blockPos.getZ());
		}
		return null;
	}
}
