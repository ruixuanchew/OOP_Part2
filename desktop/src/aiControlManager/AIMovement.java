package aiControlManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;

import entityManager.Enemy;
import entityManager.Entity;
import entityManager.EntityManager;

public class AIMovement implements AIMovementInterface {
    private EntityManager entityManager;
	private int screenHeight = Gdx.graphics.getHeight();
	private int screenWidth = Gdx.graphics.getWidth();
	
    public AIMovement(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    

    @Override
    public void AIMoveDown() {   
    	//Loop through all entities in entityManager
    	for (Entity e : entityManager.getEntityList()) {
	        if (e instanceof Enemy) {     //Check if the entity is "Enemy"
	        	Enemy entity = (Enemy) e; // Cast the entity to Enemy
	        	float deltaY = entity.getSpeed() * 0.5f; //Calculate the change in Y position based on the entity's speed
	            entity.setPosY(entity.getPosY() + deltaY);   // Update the Y position of the entity
	            entity.setSpeed(-1); // Set the speed of the entity to -1
	            if (entity.getPosY() <= 0) {
	    			entity.setPosY(screenHeight); // Reset PositionY position to the bottom of the screen
	    			entity.setPosX(MathUtils.random(screenHeight)); // Randomize X position
	            } 
	        
	        }
	    }
    }
    
    @Override
	public void AIMoveSide() {
    	for (Entity e : entityManager.getEntityList()) {
    		if (e instanceof Enemy) {
    			Enemy entity = (Enemy) e;
                float deltaX = entity.getSpeed()* 0.5f;
    	        entity.setPosX(entity.getPosX() + deltaX);
    	        entity.setSpeed(-1);  // Set the speed of the entity to -1
    	        if (entity.getPosX() <= 0) { // If the entity goes off the screen, reset its position
    	        	entity.setPosX(screenWidth);// Reset PositionX position to the right of the screen
    	            entity.setPosY(MathUtils.random(screenWidth)); // Randomize Y position
    	        }
    	     }
        }
		
	}
}
