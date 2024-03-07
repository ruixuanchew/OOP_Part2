package aiControlManager;

import com.badlogic.gdx.Gdx;


import entityManager.Entity;
import entityManager.EntityManager;

public class AIControlManager {
    private EntityManager entityManager;
	private int screenWidth = Gdx.graphics.getWidth();
	
    public AIControlManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void moveAIControlled() {
        for (Entity entity : entityManager.getEntityList()) {
            if (entity.getType().equals("asteroid")) {

            	float deltaX = entity.getSpeed();
	            
	            entity.setPosX(entity.getPosX() + deltaX);
	            if (entity.getPosX() <= 0 || entity.getPosX() >= screenWidth - entity.getWidth()) {
	    			entity.setSpeed(-(int)entity.getSpeed());
	            }
	        }
        }
    }
}
