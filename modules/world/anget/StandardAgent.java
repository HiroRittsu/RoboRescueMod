package anget;

import commons.Point3D;
import information.Worldinfo;
import map.MinecraftMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class StandardAgent {
	public boolean spawned = false;
	public Entity entity = null;
	public int entityID;
	public MinecraftMap minecraftMap;

	public StandardAgent(Entity entity, int entityID) {
		this.entity = entity;
		this.entityID = entityID;
		this.minecraftMap = Worldinfo.minecraftMap;
	}

	public void Spawn(int positionID, World world) {
		// Spawn
		Point3D position = minecraftMap.getPosition(positionID);
		if (position != null) {
			entity.setPosition(position.x, position.y, position.z);
			world.spawnEntity(entity);
			spawned = true;
			System.out.println("スポーン成功");
		} else {
			System.out.println("スポーン失敗");
		}
	}

	public Point3D getPosition() {
		if (spawned) {
			BlockPos blockPos = entity.getPosition();
			return new Point3D(blockPos.getX(), blockPos.getY(), blockPos.getZ());
		}
		return null;
	}
}
