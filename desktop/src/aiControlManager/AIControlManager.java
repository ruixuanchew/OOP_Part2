package aiControlManager;

import com.badlogic.gdx.Gdx;

import entityManager.Enemy;
import entityManager.Entity;
import entityManager.EntityManager;

public class AIControlManager {
    private EntityManager entityManager;
    private AIMovement AIMovement;

	
    public AIControlManager(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.AIMovement = new AIMovement(entityManager); //instantiate AIMovement
    }

    public void moveEntitiesDiagonal() {
        AIMovement.AIMoveDown(); // Move entities down
        AIMovement.AIMoveSide(); // Move entities side
    }

    public void moveEntitiesDown() {
    	AIMovement.AIMoveDown(); // Move entities down
    }
    
}
