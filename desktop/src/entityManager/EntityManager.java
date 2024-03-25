package entityManager;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import aiControlManager.AIControlManager;
import playerControllerManager.PlayerControllerManager;

public class EntityManager {
	private List<Entity> entityList;
	private List<Entity> collidableEntityList;
	private Entity player;
	
	public EntityManager() {
		entityList = new ArrayList<>();
		collidableEntityList = new ArrayList<>();
	}

	// add entities into the list 
	public void add(Entity entity) {
		entityList.add(entity);
	}
	
	//remove given entity from list
	public void remove(Entity entity) {
		entity.setVisible(false);
	}

	public void removeFlagEntity(Entity flag) {
		this.entityList.remove(flag);
		this.collidableEntityList.remove(flag);
	}
	
	// to return a list of entities
	public List<Entity> getEntityList() { 
		return entityList;
	}
	
	public void update() {
		for (Entity entity : entityList) {
			entity.update();
		}
	}

	public void resetEntities() {
		for (Entity entity: entityList) {
			entity.resetEntities();
		}
	}

	public void dispose(SpriteBatch batch) {
		for (Entity entity : entityList) {
			entity.dispose(batch);
		}
	}

	// make Player a global variable that all scenes can access
	public void setPlayer(Entity player) {
		this.player = player;
	}

	public Entity getPlayer() {
		return this.player;
	}

	public List<Entity> getCollidableEntityList() {
		return collidableEntityList;
	}


	public void addCollidableEntity(Entity entity) {
		this.collidableEntityList.add(entity);
	}
	
	public void moveAIDiagonal(AIControlManager aiControlManager) {
        aiControlManager.moveEntitiesDiagonal();
    }
	
	public void moveAIDown(AIControlManager aiControlManager) {
        aiControlManager.moveEntitiesDown();
    }
	
	public void movePlayerControlled(PlayerControllerManager pcManager, float deltaTime) {
		pcManager.update(deltaTime);
	}
}


