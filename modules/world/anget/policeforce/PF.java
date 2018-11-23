package anget.policeforce;

import anget.StandardAgent;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class PF extends StandardAgent {

	public int hp;

	public PF(World world, Entity entity, int entityID, int positionID) {
		super(world, entity, entityID, positionID);
	}
}
