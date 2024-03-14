package entityManager;

import com.badlogic.gdx.math.Vector2;

public class EntityFactory {
	
	public Entity createEntity(String tex, float posX, float posY, float speed, boolean isVisible, Vector2 velocity, String type, int health) {
        
		Entity player = new Player(tex, posX, posY, speed, isVisible, velocity, type, health);
		return player;
    }

    public Entity createEntity(String tex, float posX, float posY, boolean isVisible, String type) {
        
    	Entity object = new Object(tex, posX, posY, isVisible, type);
		return object;
    }

    public Entity createEntity(String tex, float posX, float posY, float speed, boolean isVisible, String type) {
    	
    	Entity enemy = new Enemy(tex, posX, posY, speed, isVisible, type);
		return enemy;
    }

}