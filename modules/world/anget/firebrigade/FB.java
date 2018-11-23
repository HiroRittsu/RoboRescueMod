package anget.firebrigade;

import anget.StandardAgent;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class FB extends StandardAgent {

	public int hp;

	public FB(World world, Entity entity, int entityID, int positionID) {
		super(world, entity, entityID, positionID);
	}

}
